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

package top.charles7c.continew.admin.front.service;

import top.charles7c.continew.admin.front.model.entity.RefundInfoDO;
import top.charles7c.continew.admin.front.model.query.RefundInfoQuery;
import top.charles7c.continew.admin.front.model.req.RefundInfoReq;
import top.charles7c.continew.admin.front.model.resp.RefundInfoDetailResp;
import top.charles7c.continew.admin.front.model.resp.RefundInfoResp;
import top.charles7c.continew.starter.extension.crud.service.BaseService;

/**
 * 退款信息业务接口
 *
 * @author weiran
 * @since 2024/03/25 23:34
 */
public interface RefundInfoService extends BaseService<RefundInfoResp, RefundInfoDetailResp, RefundInfoQuery, RefundInfoReq> {
    /**
     * Create refund by order no refund info.
     * 根据订单号创建退款订单
     *
     * @param orderNo the order no
     * @param reason  the reason
     * @return the refund info
     */
    RefundInfoDO createRefundByOrderNo(String orderNo, String reason);

    /**
     * Update refund.
     * 更新退款信息
     *
     * @param bodyAsString the body as string
     */
    void updateRefund(String bodyAsString);

    /**
     * Update refund for ali pay.
     * 支付宝支付退款
     * 
     * @param refundNo     the refund no
     * @param content      the content
     * @param refundStatus the refund status
     */
    void updateRefundForAliPay(String refundNo, String content, String refundStatus);
}
