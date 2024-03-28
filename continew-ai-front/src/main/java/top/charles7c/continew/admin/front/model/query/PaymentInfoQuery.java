package top.charles7c.continew.admin.front.model.query;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.charles7c.continew.starter.data.core.annotation.Query;
import top.charles7c.continew.starter.data.core.enums.QueryType;

/**
 * 支付信息查询条件
 *
 * @author weiran
 * @since 2024/03/28 14:30
 */
@Data
@Schema(description = "支付信息查询条件")
public class PaymentInfoQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商户订单编号
     */
    @Schema(description = "商户订单编号")
    @Query(type = QueryType.EQ)
    private String orderNo;

    /**
     * 支付系统交易编号
     */
    @Schema(description = "支付系统交易编号")
    @Query(type = QueryType.EQ)
    private String transactionId;

    /**
     * 支付类型
     */
    @Schema(description = "支付类型")
    @Query(type = QueryType.EQ)
    private String paymentType;

    /**
     * 交易类型
     */
    @Schema(description = "交易类型")
    @Query(type = QueryType.EQ)
    private String tradeType;

    /**
     * 交易状态
     */
    @Schema(description = "交易状态")
    @Query(type = QueryType.EQ)
    private String tradeState;
}