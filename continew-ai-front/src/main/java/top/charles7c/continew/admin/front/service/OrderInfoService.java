package top.charles7c.continew.admin.front.service;

import top.charles7c.continew.admin.common.enums.OrderStatus;
import top.charles7c.continew.admin.front.model.entity.OrderInfoDO;
import top.charles7c.continew.starter.extension.crud.service.BaseService;
import top.charles7c.continew.admin.front.model.query.OrderInfoQuery;
import top.charles7c.continew.admin.front.model.req.OrderInfoReq;
import top.charles7c.continew.admin.front.model.resp.OrderInfoDetailResp;
import top.charles7c.continew.admin.front.model.resp.OrderInfoResp;

import java.util.List;

/**
 * 订单信息业务接口
 *
 * @author weiran
 * @since 2024/03/28 14:25
 */
public interface OrderInfoService extends BaseService<OrderInfoResp, OrderInfoDetailResp, OrderInfoQuery, OrderInfoReq> {
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
