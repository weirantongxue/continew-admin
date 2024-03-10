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

package top.charles7c.continew.admin.front.util;

import com.alibaba.fastjson2.JSON;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by WeiRan on 2023.06.13 17:06
 */
public class ApiTokenUtils {

    public static String generateClientToken(String apikey) {
        String[] apiKeyParts = apikey.split("\\.");
        String api_key = apiKeyParts[0];
        String secret = apiKeyParts[1];

        Map<String, Object> header = new HashMap<>();
        header.put("alg", SignatureAlgorithm.HS256);
        header.put("sign_type", "SIGN");
        Map<String, Object> payload = new HashMap<>();
        payload.put("api_key", api_key);
        payload.put("exp", System.currentTimeMillis() + 5 * 60 * 1000);
        payload.put("timestamp", System.currentTimeMillis());
        String token = Jwts.builder()
            .setHeader(header)
            .setPayload(JSON.toJSONString(payload))
            .signWith(SignatureAlgorithm.HS256, secret.getBytes(StandardCharsets.UTF_8))
            .compact();

        return token;
    }

}
