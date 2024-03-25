package top.charles7c.continew.admin.front.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.charles7c.continew.admin.common.enums.OrderStatus;
import top.charles7c.continew.admin.common.util.OrderNoUtils;
import top.charles7c.continew.admin.front.mapper.OrderInfoMapper;
import top.charles7c.continew.admin.front.mapper.ProductMapper;
import top.charles7c.continew.admin.front.model.entity.OrderInfoDO;
import top.charles7c.continew.admin.front.model.entity.ProductDO;
import top.charles7c.continew.admin.front.service.OrderInfoService;

/**
 * 订单信息业务实现
 *
 * @author weiran
 * @since 2024/03/25 23:38
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class OrderInfoServiceImpl implements OrderInfoService {
    private final OrderInfoMapper orderInfoMapper;
    private final ProductMapper productMapper;



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
        if (orderInfoNoPay!=null){
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
     * @param orderNo 订单编号
     * @param orderStatus 成功响应码
     */
    @Override
    public void updateStatusByOrderNo(String orderNo, OrderStatus orderStatus) {
        log.info("更新数据库中的订单状态=======>"+orderStatus.getType());
        //创建一个查询条件，主要针对OrderInfo订单信息
        QueryWrapper<OrderInfoDO> orderInfoQueryWrapper = new QueryWrapper<>();
        //编写查询条件
        orderInfoQueryWrapper.eq("order_no",orderNo);
        //创建一个订单信息对象
        OrderInfoDO orderInfo = new OrderInfoDO();
        //设置要更新的订单状态
        orderInfo.setOrderStatus(orderStatus.getType());
        //执行更新操作
        orderInfoMapper.update(orderInfo,orderInfoQueryWrapper);

    }

    @Override
    public OrderInfoDO getOrderByOrderNo(String outTradeNo) {
        return orderInfoMapper.selectOne(new QueryWrapper<OrderInfoDO>().eq("order_no", outTradeNo));
    }


    /**
     * 该方法用于获取用户未支付的订单(由于只在该类中使用，所以定义为私有方法)
     * @param productId
     * @return
     */
    private  OrderInfoDO getNoPayOrderByProductId(Long productId){
        //使用MyBatis-plus的查询器
        QueryWrapper<OrderInfoDO> orderInfoQueryWrapper = new QueryWrapper<>();
        //设置判断条件，id和类型信息
        orderInfoQueryWrapper.eq("product_id", productId);
        orderInfoQueryWrapper.eq("order_status", OrderStatus.NOTPAY.getType());

        //使用自带的selectOne方法判断是否同时满足条件
        OrderInfoDO orderInfo = orderInfoMapper.selectOne(orderInfoQueryWrapper);
        return orderInfo;

    }
}
