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

import top.charles7c.continew.admin.common.enums.OrderStatus;
import top.charles7c.continew.admin.front.model.entity.OrderInfoDO;

import java.util.List;

public interface OrderInfoService {
    /**
     * Create order by product id order info.
     * 根据产品的id生成对应的订单信息
     *
     * @param productId the product id
     * @return the order info
     */
    OrderInfoDO createOrderByProductId(Long productId);

    /**
     * Update status by order no.
     * 根据订单号更新数据库中的订单状态
     *
     * @param orderNo     the order no
     * @param orderStatus the order status
     */
    void updateStatusByOrderNo(String orderNo, OrderStatus orderStatus);

    OrderInfoDO getOrderByOrderNo(String outTradeNo);

    /**
     * Gets order status.
     * 获取订单状态
     *
     * @param orderNo the order no
     * @return the order status
     */
    String getOrderStatus(String orderNo);

    /**
     * Gets no pay order by duration.
     * 查询超过指定时间未支付的订单
     *
     * @param minutes     the
     * @param paymentType the payment type
     * @return the no pay order by duration
     */
    List<OrderInfoDO> getNoPayOrderByDuration(int minutes, String paymentType);

}
