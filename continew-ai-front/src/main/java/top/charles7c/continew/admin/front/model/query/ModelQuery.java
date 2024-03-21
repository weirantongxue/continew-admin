package top.charles7c.continew.admin.front.model.query;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.charles7c.continew.starter.data.core.annotation.Query;
import top.charles7c.continew.starter.data.core.enums.QueryType;

/**
 * AI模型查询条件
 *
 * @author weiran
 * @since 2024/03/21 14:48
 */
@Data
@Schema(description = "AI模型查询条件")
public class ModelQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    @Schema(description = "模型名称")
    @Query(type = QueryType.EQ)
    private String name;

    /**
     * 模型类型,1:大语言模型,2:文生图
     */
    @Schema(description = "模型类型,1:大语言模型,2:文生图")
    @Query(type = QueryType.EQ)
    private Integer modelType;

    /**
     * 状态（1：启用；2：禁用）
     */
    @Schema(description = "状态（1：启用；2：禁用）")
    @Query(type = QueryType.EQ)
    private Integer status;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    @Query(type = QueryType.EQ)
    private Long createUser;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @Query(type = QueryType.BETWEEN)
    private List<LocalDateTime> createTime;
}