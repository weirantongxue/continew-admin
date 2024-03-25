package top.charles7c.continew.admin.front.service;

import java.util.Map;

/**
 * 支付信息业务接口
 *
 * @author weiran
 * @since 2024/03/25 23:37
 */
public interface PaymentInfoService {
    /**
     * Create payment info for ali pay.
     *为支付创建日志记录
     * @param params the params
     */
    void createPaymentInfoForAliPay(Map<String, String> params);
}
