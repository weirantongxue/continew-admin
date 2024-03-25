package top.charles7c.continew.admin.front.service;
import top.charles7c.continew.admin.common.enums.OrderStatus;
import top.charles7c.continew.admin.front.model.entity.OrderInfoDO;

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
}
