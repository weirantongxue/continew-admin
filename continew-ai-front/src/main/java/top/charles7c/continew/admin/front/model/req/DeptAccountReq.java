package top.charles7c.continew.admin.front.model.req;

import java.io.Serial;
import java.time.LocalDateTime;

import jakarta.validation.constraints.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.validator.constraints.Length;

import top.charles7c.continew.starter.extension.crud.model.req.BaseReq;

/**
 * 创建或修改部门账户信息
 *
 * @author weiran
 * @since 2024/04/01 18:42
 */
@Data
@Schema(description = "创建或修改部门账户信息")
public class DeptAccountReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 代币余额
     */
    @Schema(description = "代币余额")
    private Integer balanceToken;

    /**
     * 赠送代币
     */
    @Schema(description = "赠送代币")
    private Integer giveToken;

    /**
     * 充值代币
     */
    @Schema(description = "充值代币")
    private Integer rechargeToken;

    /**
     * 充值金额
     */
    @Schema(description = "充值金额")
    private Integer rechargeAmount;

    /**
     * 部门账户信息
     */
    @Schema(description = "部门账户信息")
    @NotNull(message = "部门账户信息不能为空")
    private Long deptId;
}