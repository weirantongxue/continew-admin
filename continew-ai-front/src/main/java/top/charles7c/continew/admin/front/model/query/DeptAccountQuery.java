package top.charles7c.continew.admin.front.model.query;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.charles7c.continew.starter.data.core.annotation.Query;
import top.charles7c.continew.starter.data.core.enums.QueryType;

/**
 * 部门账户查询条件
 *
 * @author weiran
 * @since 2024/04/01 18:42
 */
@Data
@Schema(description = "部门账户查询条件")
public class DeptAccountQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 部门账户信息
     */
    @Schema(description = "部门账户信息")
    @Query(type = QueryType.EQ)
    private Long deptId;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    @Query(type = QueryType.EQ)
    private Long createUser;
}