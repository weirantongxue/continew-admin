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

import java.util.Map;

public interface AliPayService {
    /**
     * Trade create string.
     * 创建支付宝支付订单
     *
     * @param productId the product id
     * @return the string
     */
    String tradeCreate(Long productId);

    /**
     * Process order.
     * 订单处理方法
     *
     * @param params the params
     */
    void processOrder(Map<String, String> params);

    /**
     * Cancel order.
     * 根据订单号取消订单
     *
     * @param orderNo the order no
     */
    void cancelOrder(String orderNo);

    /**
     * Query order string.
     * 商户向支付宝端查询订单结果
     *
     * @param orderNo the order no
     * @return the string
     */
    String queryOrder(String orderNo);

    /**
     * 根据订单号查询支付宝端的订单状态
     * 如果订单已经支付，则更新商户端订单状态，并记录支付日志
     * 如果订单没有支付，则调用关单接口，并更新商户端订单状态
     * 如果订单未创建，则直接更新商户端的订单状态即可
     * 
     * @param orderNo 订单号
     */
    void checkOrderStatus(String orderNo);

    /**
     * Refund.
     * 退款操作
     * 
     * @param orderNo the order no
     * @param reason  the reason
     */
    void refund(String orderNo, String reason);

    /**
     * Query refund string.
     * 查询退款结果
     *
     * @param orderNo the order no
     * @return the string
     */
    String queryRefund(String orderNo);

    /**
     * Query bill string.
     * 查询订单下载地址
     * 
     * @param billDate the bill date
     * @param type     the type
     * @return the string
     */
    String queryBill(String billDate, String type);
}
