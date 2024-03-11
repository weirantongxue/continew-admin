package top.charles7c.continew.admin.front.model.query;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.charles7c.continew.starter.data.core.annotation.Query;
import top.charles7c.continew.starter.data.core.enums.QueryType;

/**
 * AI会话查询条件
 *
 * @author weiran
 * @since 2024/03/11 18:36
 */
@Data
@Schema(description = "AI会话查询条件")
public class ItemQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 会话id
     */
    @Schema(description = "会话id")
    @Query(type = QueryType.EQ)
    private String itemId;

    /**
     * 会话名称
     */
    @Schema(description = "会话名称")
    @Query(type = QueryType.LIKE)
    private String name;
}