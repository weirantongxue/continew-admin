package top.charles7c.continew.admin.front.config;

/**
 * Created by WeiRan on  2024.03.13 16:43
 */

import cn.dev33.satoken.stp.StpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.support.HttpSessionHandshakeInterceptor;
import top.charles7c.continew.admin.common.util.helper.LoginHelper;

import java.util.Map;

/**
 * @author zhong
 * 用来处理webscocket拦截器
 */
@Component
@Slf4j
public class CustomWebsocketInterceptor extends HttpSessionHandshakeInterceptor {

    /**
     * 建立连接时
     *
     * @param request    the current request
     * @param response   the current response
     * @param wsHandler  the target WebSocket handler
     * @param attributes the attributes from the HTTP handshake to associate with the WebSocket
     *                   session; the provided attributes are copied, the original map is not used.
     * @return
     * @throws Exception
     */
    @Override
    public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
        ServletServerHttpRequest req = (ServletServerHttpRequest) request;
        ServletServerHttpResponse res = (ServletServerHttpResponse) response;
        String token = req.getServletRequest().getParameter("token");
        String username = req.getServletRequest().getParameter("username");
        log.info("建立连接....token:{} username:{}", token, username);
        log.info("attributes:{}", attributes);

        /**
         * 鉴权: return false 不通过
         *  response.setStatusCode(HttpStatus.UNAUTHORIZED);
         *  return false;
         */
        //LoginHelper.getLoginUser(token);
        attributes.put("token", token);
        attributes.put("username", username);

        super.setCreateSession(true);
        return super.beforeHandshake(request, response, wsHandler, attributes);
    }

    /**
     * 成功建立连接后
     *
     * @param request   the current request
     * @param response  the current response
     * @param wsHandler the target WebSocket handler
     * @param exception an exception raised during the handshake, or {@code null} if none
     */
    @Override
    public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler, Exception exception) {
        log.info("连接成功....");
        //其他业务代码
        super.afterHandshake(request, response, wsHandler, exception);
    }
}

