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

import cn.hutool.core.date.DateUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WeiRan on 2024.04.08 20:04
 */
public class ParameterUtils {
    public static Map<String, Object> categoryListAssembly() {
        Map<String, Object> map = new HashMap<>();
        String timestamp = String.valueOf(DateUtil.current());
        map.put("partner_id", 37441272);
        map.put("timestamp", timestamp);
        map.put("sign", SignGeneratorUtils.getBaiJaYunSign(map));
        return map;
    }

    public static Map<String, Object> categoryVideoAssembly(String fileId, int page) {
        Map<String, Object> map = new HashMap<>();
        String timestamp = String.valueOf(DateUtil.current());
        map.put("partner_id", 37441272);
        map.put("category_id", fileId);
        map.put("page", page);
        map.put("page_size", 100);
        map.put("timestamp", timestamp);
        //获取签名
        map.put("sign", SignGeneratorUtils.getBaiJaYunSign(map));
        return map;
    }

    public static Map<String, Object> playerTokenAssembly(int videoId) {
        Map<String, Object> map = new HashMap<>();
        String timestamp = String.valueOf(DateUtil.current());
        map.put("partner_id", 37441272);
        map.put("video_id", videoId);
        map.put("timestamp", timestamp);
        //获取签名
        map.put("sign", SignGeneratorUtils.getBaiJaYunSign(map));
        return map;

    }
}
