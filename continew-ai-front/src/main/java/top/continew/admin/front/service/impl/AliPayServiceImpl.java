/*
 * Copyright (c) 2022-present Charles7c Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package top.continew.admin.front.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.continew.admin.common.enums.AliPayTradeState;
import top.continew.admin.common.enums.OrderStatus;
import top.continew.admin.front.model.entity.OrderInfoDO;
import top.continew.admin.front.model.entity.RefundInfoDO;
import top.continew.admin.front.service.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

@Service
@Slf4j
@RequiredArgsConstructor
public class AliPayServiceImpl implements AliPayService {
    private final OrderInfoService orderInfoService;
    private final AlipayClient alipayClient;
    private final Environment config;
    private final PaymentInfoService paymentInfoService;
    private final RefundInfoService refundInfoService;
    private final DeptAccountService deptAccountService;
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * 根据订单号创建订单并发起支付请求获取平台响应返回到前端
     *
     * @param productId the product id
     * @return 返回支付请求调用的响应主体信息，返回到controller层
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String tradeCreate(String orderNo) {
        try {
            log.info("生成订单....");
            //调用orderInfoService对象在数据库中创建订单
            //OrderInfoDO orderInfo = orderInfoService.createOrderByProductId(productId);
            OrderInfoDO orderInfo = orderInfoService.getOrderByOrderNo(orderNo);

            //调用支付宝接口
            //创建支付宝请求对象
            AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
            //设置请求处理完成后的跳转的地址
            request.setReturnUrl(config.getProperty("alipay.return-url"));
            request.setNotifyUrl(config.getProperty("alipay.notify-url"));
            //创建具体请求参数对象，用于组装请求信息
            JSONObject bizContent = new JSONObject();
            //设置商户订单号
            bizContent.put("out_trade_no", orderInfo.getOrderNo());
            //设置订单总金额，由于订单金额单位为分，而参数中需要的是元，因此需要bigDecimal进行转换
            BigDecimal total = new BigDecimal(orderInfo.getTotalFee().toString());
            bizContent.put("total_amount", total);
            //设置订单标题
            bizContent.put("subject", orderInfo.getTitle());
            //设置支付产品码，比较固定（电脑支付场景下只支持一种类型）
            bizContent.put("product_code", "FAST_INSTANT_TRADE_PAY");

            //设置完成后，将bizContent具体请求对象转换成json并放置在请求中
            request.setBizContent(bizContent.toString());

            //利用alipay客户端执行请求
            AlipayTradePagePayResponse response = alipayClient.pageExecute(request);
            //判断请求是否成功
            if (response.isSuccess()) {
                //打印响应信息主体
                log.info("调用成功====》{}", response.getBody());
            } else {
                log.info("调用失败====》{}，返回码" + response.getCode() + ",返回描述为：" + response.getMsg());
                throw new RuntimeException("创建支付交易失败.....");
            }
            return response.getBody();
        } catch (AlipayApiException e) {
            throw new RuntimeException("创建支付交易失败.....");
        }
    }

    /**
     * 商户系统订单处理
     *
     * @param params 支付宝平台异步通知传递的参数
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void processOrder(Map<String, String> params) {
        log.info("处理订单.......");
        //获取传递信息中的订单号
        String orderNo = params.get("out_trade_no");
        /**
         * 在对业务数据进行状态检查之前需要利用数据锁进行处理，进行并发控制
         * 避免数据重入造成混乱，
         * 此处使用尝试获取锁的判断，如果没有获取锁，此时则返回false，直接进行下面的操作
         * 不会等待锁释放，造成阻塞。
         */
        if (lock.tryLock()) {
            try {
                //接口调用幂等性问题：在更新订单状态，记录支付日志之前过滤重复通知（无论接口被调用多少次，以下只执行一次）
                //首先获取订单状态
                OrderInfoDO orderInfo = orderInfoService.getOrderByOrderNo(orderNo);
                if (ObjectUtil.isNull(orderInfo)) {
                    return;
                }
                String orderStatus = orderInfo.getOrderStatus();
                if (!OrderStatus.NOTPAY.getType().equals(orderStatus)) {
                    //如果订单状态不是未支付，则直接返回，不需要任何处理
                    return;
                }
                //更新订单状态
                orderInfoService.updateStatusByOrderNo(orderNo, OrderStatus.SUCCESS);
                //记录支付日志
                paymentInfoService.createPaymentInfoForAliPay(params);
                //充值到账户
                deptAccountService.rechargeBalance(orderInfo);

            } finally {
                //必须要主动释放锁
                lock.unlock();
            }
        }
    }

    /**
     * 用户取消订单方法编写
     *
     * @param orderNo 订单号
     */
    @Override
    public void cancelOrder(String orderNo) {

        //调用支付统一收单交易关闭接口
        this.closeOrder(orderNo);

        //更新用户的订单状态
        orderInfoService.updateStatusByOrderNo(orderNo, OrderStatus.CANCEL);

    }

    /**
     * 关单接口调用
     *
     * @param orderNo 订单号
     */
    private void closeOrder(String orderNo) {
        try {
            log.info("关单接口调用，订单号---》{}", orderNo);
            //创建关单请求
            AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();
            //创建请求参数对象
            JSONObject bizContent = new JSONObject();
            bizContent.put("out_trade_no", orderNo);
            //将对应的参数设置到请求对象中
            request.setBizContent(bizContent.toString());
            //使用支付客户端对象执行请求
            AlipayTradeCloseResponse response = alipayClient.execute(request);
            //判断请求是否成功
            if (response.isSuccess()) {
                //打印响应信息主体
                log.info("调用成功====》{}", response.getBody());
            } else {
                log.info("调用失败====》{}，返回码" + response.getCode() + ",返回描述为：" + response.getMsg());
                // throw new RuntimeException("关单接口调用失败....."); 让其正常结束
            }

        } catch (AlipayApiException e) {
            throw new RuntimeException("关单接口调用出现异常");
        }
    }

    /**
     * 商户查询订单信息
     *
     * @param orderNo 订单号
     * @return 返回订单查询结果，如果返回为null，说明支付宝端没有创建订单
     */
    @Override
    public String queryOrder(String orderNo) {

        try {
            log.info("查单接口调用----》{}", orderNo);
            //首先创建交易查询对象
            AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
            //组装请求参数对象（向支付宝端查单需要提供哪些参数）
            JSONObject bizContent = new JSONObject();
            //组装订单号
            bizContent.put("out_trade_no", orderNo);
            request.setBizContent(bizContent.toString());
            //执行查询请求
            AlipayTradeQueryResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                log.info("调用成功，返回结果---》{}", response.getBody());
                return response.getBody();
            } else {
                log.info("调用失败，返回响应码" + response.getCode() + ",响应结果为" + response.getBody());
                // throw new RuntimeException("响应失败....");
                //调用失败直接返回为null
                return null;
            }
        } catch (AlipayApiException e) {
            throw new RuntimeException("查询订单接口调用失败.....");
        }
    }

    /**
     * 根据订单号查询支付宝端的订单状态
     * 如果订单已经支付，则更新商户端订单状态，并记录支付日志
     * 如果订单没有支付，则调用关单接口，并更新商户端订单状态
     * 如果订单未创建，则直接更新商户端的订单状态即可
     *
     * @param orderNo 订单号
     */
    @Override
    public void checkOrderStatus(String orderNo) {
        log.warn("根据订单号核实订单状态---》{}", orderNo);
        //商户端向支付宝端查询订单信息
        String result = this.queryOrder(orderNo);
        //1.订单未创建状态
        if (result == null) {
            log.warn("核实订单未创建---》{}", orderNo);
            //更新本地订单状态(设置关闭)
            orderInfoService.updateStatusByOrderNo(orderNo, OrderStatus.CLOSED);
        }

        //2.如果订单未支付，则调用关单接口并更新商户端订单状态
        Gson gson = new Gson();
        //由于result的值中也是属于键值对，String-{xxx：xxx，xxxx：xxxx，xxx：xxxx}
        Map<String, LinkedTreeMap> resultMap = gson.fromJson(result, HashMap.class);
        //参见统一收单线下交易查询中的响应示例
        LinkedTreeMap alipayTradeQueryResponse = resultMap.get("alipay_trade_query_response");
        //从map中获取订单状态(trade_status)
        String tradeStatus = (String)alipayTradeQueryResponse.get("trade_status");
        if (AliPayTradeState.NOTPAY.getType().equals(tradeStatus)) {
            //判断如果订单未支付
            log.warn("核实订单未支付---》{}", orderNo);
            //订单未支付，则调用关单接口
            this.closeOrder(orderNo);
            //更新商户端状态
            orderInfoService.updateStatusByOrderNo(orderNo, OrderStatus.CLOSED);
        }

        //3.如果订单已经支付，则更新商户端的订单状态，并记录支付日志
        if (AliPayTradeState.SUCCESS.getType().equals(tradeStatus)) {
            //判断订单已经支付
            log.warn("核实订单已支付---》{}", orderNo);
            orderInfoService.updateStatusByOrderNo(orderNo, OrderStatus.SUCCESS);
            paymentInfoService.createPaymentInfoForAliPay(alipayTradeQueryResponse);
        }
    }

    /**
     * 商户发起退款请求
     *
     * @param orderNo 退款单号
     * @param reason  原因
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void refund(String orderNo, String reason) {
        try {
            log.info("调用退款API");
            //调用退款信息方法创建退款信息
            RefundInfoDO refundInfo = refundInfoService.createRefundByOrderNo(orderNo, reason);
            //创建统一交易退款请求
            AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
            //组装当前业务交易的请求参数
            JSONObject bizContent = new JSONObject();
            bizContent.put("out_trade_no", orderNo);
            //设置退款单金额(需要除以100),分转化成元
            BigDecimal refund = new BigDecimal(refundInfo.getRefund().toString()).divide(new BigDecimal("1"));
            bizContent.put("refund_amount", refund);
            bizContent.put("refund_reason", reason);
            //将参数设置到请求中
            request.setBizContent(bizContent.toString());
            AlipayTradeRefundResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                log.info("退款交易成功，对应信息为：" + response.getBody());
                //更新订单状态
                orderInfoService.updateStatusByOrderNo(orderNo, OrderStatus.REFUND_SUCCESS);
                //更新退款单
                refundInfoService.updateRefundForAliPay( //表示退款成功
                    refundInfo.getRefundNo(), response.getBody(), AliPayTradeState.REFUND_SUCCESS.getType());
            } else {
                log.warn("退款交易失败，对应状态码为：" + response.getCode() + ",返回体为：" + response.getBody());
                //更新订单状态
                orderInfoService.updateStatusByOrderNo(orderNo, OrderStatus.REFUND_ABNORMAL);
                //更新退款单
                refundInfoService.updateRefundForAliPay(refundInfo.getRefundNo(), response
                    .getBody(), AliPayTradeState.REFUND_ERROR.getType());
            }

        } catch (AlipayApiException e) {
            throw new RuntimeException("退款交易失败.....");
        }
    }

    /**
     * 根据订单号查询退款
     *
     * @param orderNo the order no 订单号
     * @return 返回退款查询的结果
     */
    @Override
    public String queryRefund(String orderNo) {

        try {
            log.info("查询退款接口调用---》{}", orderNo);
            //定义一个查询退款的请求对象
            AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();
            //组装请求参数
            JSONObject bizContent = new JSONObject();
            bizContent.put("out_trade_no", orderNo);
            //out_request_no表示退款请求号，如果退款的时候没有传入，则以订单号作为退款请求号。
            bizContent.put("out_request_no", orderNo);
            //组装到请求中
            request.setBizContent(bizContent.toString());
            //执行请求
            AlipayTradeFastpayRefundQueryResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                log.info("调用成功，返回结果---》{}", response.getBody());
                return response.getBody();
            } else {
                log.info("调用失败，对应的响应码为：" + response.getCode() + ",对应的响应内容为：" + response.getBody());
                //如果调用失败，返回空
                return null;
            }
        } catch (AlipayApiException e) {
            throw new RuntimeException("退款查询请求执行失败");
        }
    }

    /**
     * 获取账单地址实现
     * 
     * @param billDate the bill date 账单日期
     * @param type     the type 账单类型
     * @return
     */
    @Override
    public String queryBill(String billDate, String type) {
        try {
            //设置查询账单请求对象
            AlipayDataDataserviceBillDownloadurlQueryRequest request = new AlipayDataDataserviceBillDownloadurlQueryRequest();
            //组装请求参数
            JSONObject bizContent = new JSONObject();
            bizContent.put("bill_type", type);
            bizContent.put("bill_date", billDate);
            //将请求参数设置到请求中
            request.setBizContent(bizContent.toString());
            //执行请求
            AlipayDataDataserviceBillDownloadurlQueryResponse response = alipayClient.execute(request);
            if (response.isSuccess()) {
                log.info("查询账单url地址请求成功---》{}", response.getBody());
                //获取账单的下载地址
                Gson gson = new Gson();
                Map<String, LinkedTreeMap> resultMap = gson.fromJson(response.getBody(), HashMap.class);
                //获取交易账单地址
                LinkedTreeMap billDownLoadUrl = resultMap
                    .get("alipay_data_dataservice_bill_downloadurl_query_response");
                String billDownloadUrl = (String)billDownLoadUrl.get("bill_download_url");
                //返回url地址
                return billDownloadUrl;

            } else {
                log.info("查询账单地址失败。对应的响应码为：" + response.getCode() + ",对应的响应体为：" + response.getBody());
                throw new RuntimeException("查询账单地址失败....");
            }

        } catch (AlipayApiException e) {
            throw new RuntimeException("查询账单请求执行失败.......");
        }
    }
}
