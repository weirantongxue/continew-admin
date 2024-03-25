package top.charles7c.continew.admin.front.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.alipay.api.response.AlipayTradePagePayResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.charles7c.continew.admin.common.enums.OrderStatus;
import top.charles7c.continew.admin.front.model.entity.OrderInfoDO;
import top.charles7c.continew.admin.front.service.AliPayService;
import top.charles7c.continew.admin.front.service.OrderInfoService;
import top.charles7c.continew.admin.front.service.PaymentInfoService;

import java.math.BigDecimal;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class AliPayServiceImpl implements AliPayService {
    private final OrderInfoService orderInfoService;
    private final AlipayClient alipayClient;
    private final Environment config;
    private final PaymentInfoService paymentInfoService;


    /**
     * 根据订单号创建订单并发起支付请求获取平台响应返回到前端
     *
     * @param productId the product id
     * @return 返回支付请求调用的响应主体信息，返回到controller层
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String tradeCreate(Long productId) {
        try {

            log.info("生成订单....");
            //调用orderInfoService对象在数据库中创建订单
            OrderInfoDO orderInfo = orderInfoService.createOrderByProductId(productId);

            //调用支付宝接口
            //创建支付宝请求对象
            AlipayTradePagePayRequest request = new AlipayTradePagePayRequest();
            //设置请求处理完成后的跳转的地址
            request.setReturnUrl(config.getProperty("alipay.return-url"));
            //创建具体请求参数对象，用于组装请求信息
            JSONObject bizContent = new JSONObject();
            //设置商户订单号
            bizContent.put("out_trade_no", orderInfo.getOrderNo());
            //设置订单总金额，由于订单金额单位为分，而参数中需要的是元，因此需要bigDecimal进行转换
            BigDecimal total = new BigDecimal(orderInfo.getTotalFee().toString()).divide(new BigDecimal("100"));
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
     * @param params 支付宝平台异步通知传递的参数
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void processOrder(Map<String, String> params) {
        log.info("处理订单.......");
        //获取传递信息中的订单号
        String orderNo = params.get("out_trade_no");
        //更新订单状态
        orderInfoService.updateStatusByOrderNo(orderNo, OrderStatus.SUCCESS);
        //记录支付日志
        paymentInfoService.createPaymentInfoForAliPay(params);
    }
}
