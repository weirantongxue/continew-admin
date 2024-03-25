package top.charles7c.continew.admin.front.model.entity;

import java.io.Serial;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableName;

import top.charles7c.continew.starter.extension.crud.model.entity.BaseDO;

/**
 * 支付信息实体
 *
 * @author weiran
 * @since 2024/03/25 23:37
 */
@Data
@TableName("lb_payment_info")
public class PaymentInfoDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商户订单编号
     */
    private String orderNo;

    /**
     * 支付系统交易编号
     */
    private String transactionId;

    /**
     * 支付类型
     */
    private String paymentType;

    /**
     * 交易类型
     */
    private String tradeType;

    /**
     * 交易状态
     */
    private String tradeState;

    /**
     * 支付金额(分)
     */
    private Integer payerTotal;

    /**
     * 通知参数
     */
    private String content;
}