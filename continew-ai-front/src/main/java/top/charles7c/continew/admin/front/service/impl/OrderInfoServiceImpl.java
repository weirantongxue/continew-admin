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

package top.charles7c.continew.admin.front.service.impl;

import com.alipay.api.AlipayClient;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.charles7c.continew.admin.common.enums.OrderStatus;
import top.charles7c.continew.admin.common.util.OrderNoUtils;
import top.charles7c.continew.admin.common.util.helper.LoginHelper;
import top.charles7c.continew.admin.front.mapper.OrderInfoMapper;
import top.charles7c.continew.admin.front.mapper.ProductMapper;
import top.charles7c.continew.admin.front.model.entity.OrderInfoDO;
import top.charles7c.continew.admin.front.model.entity.ProductDO;
import top.charles7c.continew.admin.front.model.query.OrderInfoQuery;
import top.charles7c.continew.admin.front.model.req.OrderInfoReq;
import top.charles7c.continew.admin.front.model.resp.OrderInfoDetailResp;
import top.charles7c.continew.admin.front.model.resp.OrderInfoResp;
import top.charles7c.continew.admin.front.service.OrderInfoService;
import top.charles7c.continew.starter.extension.crud.service.impl.BaseServiceImpl;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

/**
 * 订单信息业务实现
 *
 * @author weiran
 * @since 2024/03/25 23:38
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OrderInfoServiceImpl extends BaseServiceImpl<OrderInfoMapper, OrderInfoDO, OrderInfoResp, OrderInfoDetailResp, OrderInfoQuery, OrderInfoReq> implements OrderInfoService {
    private final OrderInfoMapper orderInfoMapper;
    private final ProductMapper productMapper;
    private final AlipayClient alipayClient;

    /**
     * Create order by product id order info.
     * 根据产品的id生成对应的订单信息
     *
     * @param productId the product id
     * @return the order info
     */
    @Override
    public OrderInfoDO createOrderByProductId(Long productId) {
        //查找已存在，但是并未支付的订单信息
        OrderInfoDO orderInfoNoPay = getNoPayOrderByProductId(productId);
        if (orderInfoNoPay != null) {
            return orderInfoNoPay;
        }
        //获取商品的对象
        ProductDO product = productMapper.selectById(productId);

        //生成订单
        OrderInfoDO orderInfo = new OrderInfoDO();
        orderInfo.setTitle(product.getTitle());
        //订单号
        orderInfo.setOrderNo(OrderNoUtils.getOrderNo());
        orderInfo.setTotalFee(product.getPrice());
        orderInfo.setProductId(productId);
        orderInfo.setOrderStatus(OrderStatus.NOTPAY.getType());

        //将订单信息存入数据库
        orderInfoMapper.insert(orderInfo);

        return orderInfo;
    }

    /**
     * 此方法用于根据订单编号来更新数据库中的订单状态
     *
     * @param orderNo     订单编号
     * @param orderStatus 成功响应码
     */
    @Override
    public void updateStatusByOrderNo(String orderNo, OrderStatus orderStatus) {
        log.info("更新数据库中的订单状态=======>" + orderStatus.getType());
        //创建一个查询条件，主要针对OrderInfo订单信息
        QueryWrapper<OrderInfoDO> orderInfoQueryWrapper = new QueryWrapper<>();
        //编写查询条件
        orderInfoQueryWrapper.eq("order_no", orderNo);
        //创建一个订单信息对象
        OrderInfoDO orderInfo = new OrderInfoDO();
        //设置要更新的订单状态
        orderInfo.setOrderStatus(orderStatus.getType());
        //执行更新操作
        orderInfoMapper.update(orderInfo, orderInfoQueryWrapper);

    }

    @Override
    public OrderInfoDO getOrderByOrderNo(String outTradeNo) {
        return orderInfoMapper.selectOne(new QueryWrapper<OrderInfoDO>().eq("order_no", outTradeNo));
    }

    /**
     * 根据订单号获取订单状态
     *
     * @param orderNo the order no
     * @return
     */
    @Override
    public String getOrderStatus(String orderNo) {
        //进行查询订单的操作
        QueryWrapper<OrderInfoDO> orderInfoQueryWrapper = new QueryWrapper<>();
        //构造查询条件
        orderInfoQueryWrapper.eq("order_no", orderNo);
        //根据订单号查询的订单信息必须是唯一的，因此使用selectOne
        OrderInfoDO orderInfo = orderInfoMapper.selectOne(orderInfoQueryWrapper);
        //判断订单信息是否为空，如果为空，直接将订单状态设置为null
        if (orderInfo == null) {
            return null;
        }
        return orderInfo.getOrderStatus();
    }

    /**
     * 查询超过指定时间未支付的订单集合
     * 
     * @param minutes the
     * @return
     */
    @Override
    public List<OrderInfoDO> getNoPayOrderByDuration(int minutes, String paymentType) {
        //创建一个时间实例，减去超时时间的时间实例，与订单的创建时间相比
        Instant minus = Instant.now().minus(Duration.ofMinutes(minutes));

        //创建一个查询订单对象
        QueryWrapper<OrderInfoDO> orderInfoQueryWrapper = new QueryWrapper<>();
        //组装订单的查询信息，首先是未支付
        orderInfoQueryWrapper.eq("order_status", OrderStatus.NOTPAY.getType());
        //如果当前时间减去超时时间的时间值比创建时间晚，则说明已经超时了
        orderInfoQueryWrapper.le("create_time", minus);
        //        orderInfoQueryWrapper.eq("payment_type", paymentType);
        //最后将查询的结果返回
        return orderInfoMapper.selectList(orderInfoQueryWrapper);
    }

    /**
     * 该方法用于获取用户未支付的订单(由于只在该类中使用，所以定义为私有方法)
     *
     * @param productId
     * @return
     */
    private OrderInfoDO getNoPayOrderByProductId(Long productId) {
        //使用MyBatis-plus的查询器
        QueryWrapper<OrderInfoDO> orderInfoQueryWrapper = new QueryWrapper<>();
        //设置判断条件，id和类型信息
        orderInfoQueryWrapper.eq("product_id", productId);
        orderInfoQueryWrapper.eq("order_status", OrderStatus.NOTPAY.getType());
        orderInfoQueryWrapper.eq("create_user", LoginHelper.getUserId());
        //使用自带的selectOne方法判断是否同时满足条件
        OrderInfoDO orderInfo = orderInfoMapper.selectOne(orderInfoQueryWrapper);
        return orderInfo;

    }
}
