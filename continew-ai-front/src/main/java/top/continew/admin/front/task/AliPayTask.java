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

package top.continew.admin.front.task;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.continew.admin.common.enums.PayType;
import top.continew.admin.front.model.entity.OrderInfoDO;
import top.continew.admin.front.service.AliPayService;
import top.continew.admin.front.service.OrderInfoService;

import java.util.List;

/**
 * Created by WeiRan on 2024.03.26 10:44
 */
@Slf4j
@Component
public class AliPayTask {
    @Resource
    private OrderInfoService orderInfoService;
    @Resource
    private AliPayService aliPayService;

    /**
     * 每30秒查询一次订单信息，查询创建1分钟并且未支付的订单
     */
    @Scheduled(cron = "0 0/5 * * * ?")
    public void orderConfirm() {
        log.info("定时查询订单任务启动");
        //调用查询未支付订单的方法获取所有的订单信息
        List<OrderInfoDO> noPayOrderList = orderInfoService.getNoPayOrderByDuration(5, PayType.ALIPAY.getType());

        //遍历超时订单
        for (OrderInfoDO orderInfo : noPayOrderList) {
            String orderNo = orderInfo.getOrderNo();
            log.info("超时5分钟未支付的订单---》{}", orderNo);
            //核实订单状态，调用支付宝端查单接口
            aliPayService.checkOrderStatus(orderNo);
        }
    }
}
