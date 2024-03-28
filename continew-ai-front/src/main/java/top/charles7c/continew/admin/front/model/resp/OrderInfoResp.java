package top.charles7c.continew.admin.front.model.resp;

import java.io.Serial;
import java.time.LocalDateTime;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.charles7c.continew.starter.extension.crud.model.resp.BaseResp;

/**
 * 订单信息信息
 *
 * @author weiran
 * @since 2024/03/28 14:25
 */
@Data
@Schema(description = "订单信息信息")
public class OrderInfoResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 订单标题
     */
    @Schema(description = "订单标题")
    private String title;

    /**
     * 商户订单编号
     */
    @Schema(description = "商户订单编号")
    private String orderNo;

    /**
     * 支付产品id
     */
    @Schema(description = "支付产品id")
    private Long productId;

    /**
     * 订单金额(分)
     */
    @Schema(description = "订单金额(分)")
    private Integer totalFee;

    /**
     * 订单二维码连接
     */
    @Schema(description = "订单二维码连接")
    private String codeUrl;

    /**
     * 订单状态
     */
    @Schema(description = "订单状态")
    private String orderStatus;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}