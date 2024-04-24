/*
 * Copyright (c) 2022-present Charles7c Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package top.continew.admin.common.util;

import org.springframework.web.socket.WebSocketSession;

import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by WeiRan on 2024.04.24 18:26
 */
public class WsUtils {
    public static final ConcurrentHashMap<String, WebSocketSession> WEB_SOCKET_SESSION_MAP = new ConcurrentHashMap<>();

    /**
     * 关闭连接
     *
     * @param sessionId
     */
    public static void close(String sessionId) throws Exception {
        getWebSocketSession(sessionId).close();
    }

    /**
     * 新增连接
     *
     * @param sessionId
     * @param session
     */
    public static void add(String sessionId, WebSocketSession session) {
        WEB_SOCKET_SESSION_MAP.put(sessionId, session);
    }

    /**
     * 删除连接
     *
     * @param sessionId
     */
    public static void remove(String sessionId) {
        WEB_SOCKET_SESSION_MAP.remove(sessionId);
    }

    /**
     * 通过sessionId获取WebSocketSession
     *
     * @param sessionId
     * @return
     */
    public static WebSocketSession getWebSocketSession(String sessionId) {
        return WEB_SOCKET_SESSION_MAP.get(sessionId);
    }

    /**
     * 判断sessionId是否存在
     *
     * @param sessionId
     * @return
     */
    public static boolean contains(String sessionId) {
        return WsUtils.WEB_SOCKET_SESSION_MAP.containsKey(sessionId);
    }

    /**
     * 获取所有的sessionId
     *
     * @return
     */
    public static Enumeration<String> getSessionIdList() {
        return WEB_SOCKET_SESSION_MAP.keys();
    }

}
