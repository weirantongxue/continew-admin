package top.charles7c.continew.admin.front.model.resp;

import java.io.Serial;
import java.time.LocalDateTime;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.charles7c.continew.starter.extension.crud.model.resp.BaseResp;

/**
 * 退款信息信息
 *
 * @author weiran
 * @since 2024/03/28 17:25
 */
@Data
@Schema(description = "退款信息信息")
public class RefundInfoResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商户订单编号
     */
    @Schema(description = "商户订单编号")
    private String orderNo;

    /**
     * 商户退款单编号
     */
    @Schema(description = "商户退款单编号")
    private String refundNo;

    /**
     * 支付系统退款单号
     */
    @Schema(description = "支付系统退款单号")
    private String refundId;

    /**
     * 原订单金额(分)
     */
    @Schema(description = "原订单金额(分)")
    private Integer totalFee;

    /**
     * 退款金额(分)
     */
    @Schema(description = "退款金额(分)")
    private Integer refund;

    /**
     * 退款原因
     */
    @Schema(description = "退款原因")
    private String reason;

    /**
     * 退款状态
     */
    @Schema(description = "退款状态")
    private String refundStatus;

    /**
     * 申请退款返回参数
     */
    @Schema(description = "申请退款返回参数")
    private String contentReturn;

    /**
     * 退款结果通知参数
     */
    @Schema(description = "退款结果通知参数")
    private String contentNotify;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}