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

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.continew.admin.common.util.OrderNoUtils;
import top.continew.admin.front.mapper.RefundInfoMapper;
import top.continew.admin.front.model.entity.OrderInfoDO;
import top.continew.admin.front.model.entity.RefundInfoDO;
import top.continew.admin.front.model.query.RefundInfoQuery;
import top.continew.admin.front.model.req.RefundInfoReq;
import top.continew.admin.front.model.resp.RefundInfoDetailResp;
import top.continew.admin.front.model.resp.RefundInfoResp;
import top.continew.admin.front.service.OrderInfoService;
import top.continew.admin.front.service.RefundInfoService;
import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * 退款信息业务实现
 *
 * @author weiran
 * @since 2024/03/25 23:34
 */
@Service
@RequiredArgsConstructor
public class RefundInfoServiceImpl extends BaseServiceImpl<RefundInfoMapper, RefundInfoDO, RefundInfoResp, RefundInfoDetailResp, RefundInfoQuery, RefundInfoReq> implements RefundInfoService {
    private final OrderInfoService orderInfoService;
    private final RefundInfoMapper refundInfoMapper;

    /**
     * @param orderNo 订单编号
     * @param reason  退款原因
     * @return RefundInfo 退款单信息
     */
    @Override
    public RefundInfoDO createRefundByOrderNo(String orderNo, String reason) {
        //根据订单号处理订单信息
        OrderInfoDO orderInfo = orderInfoService.getOrderByOrderNo(orderNo);

        //根据订单号生成退款单记录
        RefundInfoDO refundInfo = new RefundInfoDO();
        //订单编号
        refundInfo.setOrderNo(orderNo);
        //退款单编号
        refundInfo.setRefundNo(OrderNoUtils.getRefundNo());
        //原来订单金额
        refundInfo.setTotalFee(orderInfo.getTotalFee());
        //退款金额
        refundInfo.setRefund(orderInfo.getTotalFee());
        //退款原因
        refundInfo.setReason(reason);
        //将退款信息插入数据库
        refundInfoMapper.insert(refundInfo);
        return refundInfo;
    }

    @Override
    public void updateRefund(String content) {
        //将退款请求响应的返回对象转成Map类型信息
        Gson gson = new Gson();
        Map<String, String> resultMap = gson.fromJson(content, HashMap.class);
        //根据退款单编号，修改退款单
        QueryWrapper<RefundInfoDO> refundInfoQueryWrapper = new QueryWrapper<>();
        refundInfoQueryWrapper.eq("refund_no", resultMap.get("out_refund_no"));

        //设置要修改的字段
        RefundInfoDO refundInfo = new RefundInfoDO();
        //微信支付退款单号
        refundInfo.setRefundId(resultMap.get("refund_id"));

        //查询申请退款和退款中的返回参数(退款中)
        if (resultMap.get("status") != null) {
            //设置退款状态
            refundInfo.setRefundStatus(resultMap.get("status"));
            //将全部响应结果存入数据库的content字段中
            refundInfo.setContentReturn(content);
        }

        //退款回调中的回调参数(这是退款之后的状态)
        if (resultMap.get("refund_status") != null) {
            refundInfo.setRefundStatus(resultMap.get("refund-status"));
            //将全部响应结果存入数据库的content字段中
            refundInfo.setContentNotify(content);
        }

        //更新退款单
        refundInfoMapper.update(refundInfo, refundInfoQueryWrapper);

    }

    /**
     * @param refundNo     退款单号
     * @param content      退款信息主体
     * @param refundStatus 退款结果类型
     */
    @Override
    public void updateRefundForAliPay(String refundNo, String content, String refundStatus) {
        //根据退款单号修改退款单
        QueryWrapper<RefundInfoDO> refundInfoQueryWrapper = new QueryWrapper<>();
        refundInfoQueryWrapper.eq("refund_no", refundNo);

        //设置要修改的字段(新建一个退款单)
        RefundInfoDO refundInfo = new RefundInfoDO();
        //refundInfo.setRefundNo(refundNo);
        refundInfo.setRefundStatus(refundStatus);
        refundInfo.setContentReturn(content);

        //执行更新操作
        refundInfoMapper.update(refundInfo, refundInfoQueryWrapper);

    }

}