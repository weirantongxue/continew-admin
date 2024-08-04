package top.continew.admin.ai.utils;

import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Slf4j
public class SseSendUtils {

    public static boolean sendSseEvent(SseEmitter sseEmitter, String messageId, String eventName, String data) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("text", data);
        try {
            sseEmitter.send(SseEmitter.event()
                    .id(messageId)
                    .name(eventName)
                    .data(jsonObject)
                    .reconnectTime(3000));
            return true;
        } catch (IOException e) {
            log.error("SSE消息发送失败,用户连接已断开");
            return false;
        }
    }
}
