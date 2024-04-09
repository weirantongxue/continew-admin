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

package top.charles7c.continew.admin.webapi.ai;

import cn.hutool.core.date.DateUtil;
import top.charles7c.continew.admin.common.util.SignGeneratorUtils;

import java.util.Map;
import java.util.TreeMap;

public class SignGenerator {

    public static void main(String[] args) {
        Map<String, Object> params = new TreeMap<>();
        params.put("partner_id", "37441272");
        params.put("category_id", "34976");
        //                params.put("page_size", "20");
        //                params.put("page", "1");
        String ti = String.valueOf(DateUtil.current());
        System.out.println(ti);
        params.put("timestamp", ti);

        String sign = SignGeneratorUtils.getBaiJaYunSign(params);

        System.out.println(sign);
    }
}
