package top.continew.admin.ai.model;

import lombok.Data;
import top.continew.admin.ai.model.resp.MessageResponse;

import java.io.Serializable;

/**
 * Created by WeiRan on  2025.01.02 18:35
 */
@Data
public class ChatChoice implements Serializable {
    private Integer index;
    /**
     * 请求参数stream为true返回是delta
     */
    private MessageResponse delta;
    /**
     * 请求参数stream为false返回是message
     */
    private MessageResponse message;
}
