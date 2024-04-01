package top.charles7c.continew.admin.front.model.entity;

import java.io.Serial;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableName;

import top.charles7c.continew.starter.extension.crud.model.entity.BaseDO;

/**
 * 部门账户实体
 *
 * @author weiran
 * @since 2024/04/01 18:42
 */
@Data
@TableName("lb_dept_account")
public class DeptAccountDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 代币余额
     */
    private Integer balanceToken;

    /**
     * 赠送代币
     */
    private Integer giveToken;

    /**
     * 充值代币
     */
    private Integer rechargeToken;

    /**
     * 充值金额
     */
    private Integer rechargeAmount;

    /**
     * 部门账户信息
     */
    private Long deptId;
}