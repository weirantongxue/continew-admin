package top.charles7c.continew.admin.front.model.query;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.charles7c.continew.starter.data.core.annotation.Query;
import top.charles7c.continew.starter.data.core.enums.QueryType;

/**
 * 订单信息查询条件
 *
 * @author weiran
 * @since 2024/03/28 14:25
 */
@Data
@Schema(description = "订单信息查询条件")
public class OrderInfoQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 订单标题
     */
    @Schema(description = "订单标题")
    @Query(type = QueryType.LIKE)
    private String title;

    /**
     * 商户订单编号
     */
    @Schema(description = "商户订单编号")
    @Query(type = QueryType.EQ)
    private String orderNo;

    /**
     * 支付产品id
     */
    @Schema(description = "支付产品id")
    @Query(type = QueryType.EQ)
    private Long productId;

    /**
     * 订单状态
     */
    @Schema(description = "订单状态")
    @Query(type = QueryType.EQ)
    private String orderStatus;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    @Query(type = QueryType.EQ)
    private Long createUser;
}