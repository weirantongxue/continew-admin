package top.charles7c.continew.admin.front.model.req;

import java.io.Serial;
import java.time.LocalDateTime;

import jakarta.validation.constraints.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.validator.constraints.Length;

import top.charles7c.continew.starter.extension.crud.model.req.BaseReq;

/**
 * 创建或修改AI会话信息
 *
 * @author weiran
 * @since 2024/03/11 18:36
 */
@Data
@Schema(description = "创建或修改AI会话信息")
public class ItemReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 会话id
     */
    @Schema(description = "会话id")
    @Length(max = 255, message = "会话id长度不能超过 {max} 个字符")
    private String itemId;

    /**
     * 会话名称
     */
    @Schema(description = "会话名称")
    @NotBlank(message = "会话名称不能为空")
    @Length(max = 255, message = "会话名称长度不能超过 {max} 个字符")
    private String name;

    /**
     * 最后一条消息
     */
    @Schema(description = "最后一条消息")
    @Length(max = 255, message = "最后一条消息长度不能超过 {max} 个字符")
    private String lastMessage;

    /**
     * 消息数
     */
    @Schema(description = "消息数")
    private Integer number;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    private Long createUser;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private LocalDateTime createTime;
}