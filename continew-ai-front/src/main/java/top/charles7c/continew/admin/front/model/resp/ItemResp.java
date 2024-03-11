package top.charles7c.continew.admin.front.model.resp;

import java.io.Serial;
import java.time.LocalDateTime;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.charles7c.continew.starter.extension.crud.model.resp.BaseResp;

/**
 * AI会话信息
 *
 * @author weiran
 * @since 2024/03/11 18:36
 */
@Data
@Schema(description = "AI会话信息")
public class ItemResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 会话id
     */
    @Schema(description = "会话id")
    private String itemId;

    /**
     * 会话名称
     */
    @Schema(description = "会话名称")
    private String name;

    /**
     * 最后一条消息
     */
    @Schema(description = "最后一条消息")
    private String lastMessage;

    /**
     * 消息数
     */
    @Schema(description = "消息数")
    private Integer number;

}