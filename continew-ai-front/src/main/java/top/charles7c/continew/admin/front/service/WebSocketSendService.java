package top.charles7c.continew.admin.front.service;

import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;

/**
 * Created by WeiRan on  2024.03.13 17:23
 */
public interface WebSocketSendService {
    String getSessionId(WebSocketSession session);

    void sendMessage(String sessionId, String message) throws IOException;
    void sendMessage(String sessionId, Object data) throws IOException;

    List<String> getSessionIds();

}
