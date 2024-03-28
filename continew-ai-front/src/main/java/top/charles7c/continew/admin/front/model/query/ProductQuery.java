package top.charles7c.continew.admin.front.model.query;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.charles7c.continew.starter.data.core.annotation.Query;
import top.charles7c.continew.starter.data.core.enums.QueryType;

/**
 * 产品查询条件
 *
 * @author weiran
 * @since 2024/03/28 14:22
 */
@Data
@Schema(description = "产品查询条件")
public class ProductQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    @Query(type = QueryType.LIKE)
    private String title;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    @Query(type = QueryType.EQ)
    private Long createUser;
}