package top.charles7c.continew.admin.front.model.resp;

import java.io.Serial;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;

import top.charles7c.continew.starter.extension.crud.model.resp.BaseDetailResp;

/**
 * 部门账户详情信息
 *
 * @author weiran
 * @since 2024/04/01 18:42
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "部门账户详情信息")
public class DeptAccountDetailResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 代币余额
     */
    @Schema(description = "代币余额")
    @ExcelProperty(value = "代币余额")
    private Integer balanceToken;

    /**
     * 赠送代币
     */
    @Schema(description = "赠送代币")
    @ExcelProperty(value = "赠送代币")
    private Integer giveToken;

    /**
     * 充值代币
     */
    @Schema(description = "充值代币")
    @ExcelProperty(value = "充值代币")
    private Integer rechargeToken;

    /**
     * 充值金额
     */
    @Schema(description = "充值金额")
    @ExcelProperty(value = "充值金额")
    private Integer rechargeAmount;

    /**
     * 部门账户信息
     */
    @Schema(description = "部门账户信息")
    @ExcelProperty(value = "部门账户信息")
    private Long deptId;
}