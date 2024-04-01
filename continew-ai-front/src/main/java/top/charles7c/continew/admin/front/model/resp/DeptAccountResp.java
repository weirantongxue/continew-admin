package top.charles7c.continew.admin.front.model.resp;

import java.io.Serial;
import java.time.LocalDateTime;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.charles7c.continew.starter.extension.crud.model.resp.BaseResp;

/**
 * 部门账户信息
 *
 * @author weiran
 * @since 2024/04/01 18:42
 */
@Data
@Schema(description = "部门账户信息")
public class DeptAccountResp extends BaseResp {

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
    private Long deptId;

    /**
     * 修改人
     */
    @Schema(description = "修改人")
    private Long updateUser;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}