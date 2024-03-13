package top.charles7c.continew.admin.common.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by WeiRan on  2024.03.13 18:41
 */
@Data
public class ChatModelMsg implements Serializable {
    @Schema(description = "消息id")
    private String msgId;

    @Schema(description = "会话id")
    private String itemId;

    @Schema(description = "内容")
    private String content;

    @Schema(description = "add:发送消息,done:结束发送,error:服务异常")
    private String eventType;


}
