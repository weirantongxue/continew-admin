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
     *订单处理方法
     * @param params the params
     */
    void processOrder(Map<String, String> params);
}
