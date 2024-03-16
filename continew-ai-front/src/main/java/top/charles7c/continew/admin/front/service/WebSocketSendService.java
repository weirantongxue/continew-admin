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

package top.charles7c.continew.admin.front.service;

import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.util.List;

/**
 * Created by WeiRan on 2024.03.13 17:23
 */
public interface WebSocketSendService {
    String getSessionId(WebSocketSession session);

    void sendMessage(String sessionId, String message) throws IOException;

    void sendMessage(String sessionId, Object data) throws IOException;

    List<String> getSessionIds();

    void close(String sessionId, String message) throws IOException;

}
