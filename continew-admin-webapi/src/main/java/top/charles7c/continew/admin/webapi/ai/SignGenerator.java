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

import java.util.Map;
import java.util.TreeMap;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;

public class SignGenerator {

    /**
     * 生成签名参数
     *
     * @param params     请求的参数
     * @param partnerKey
     * @return 生成的签名
     */
    public static String getSign(Map<String, String> params, String partnerKey) {
        // 将参数按key进行排序
        Map<String, String> sortedParams = new TreeMap<>(params);

        StringBuilder strBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : sortedParams.entrySet()) {
            strBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        strBuilder.append("partner_key=").append(partnerKey); // 结尾再拼上 partner_key=partnerKey

        String sign = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(strBuilder.toString().getBytes("UTF-8"));
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                result.append(String.format("%02x", b));
            }
            sign = result.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sign;
    }

    public static void main(String[] args) {
        Map<String, String> params = new TreeMap<>();
        params.put("partner_id", "37441272");
        String ti = String.valueOf(DateUtil.current());
        System.out.println(ti);
        params.put("timestamp", ti);

        String partnerKey = "nemuaKkfBUdJfzTV2kBHKe0/896F5Ijoevl97+3BFIKMKyEQcAvj3w0STXJnonP95N/kKG15+gvddeK7zWYL4NwJelQI";
        String sign = getSign(params, partnerKey);
        System.out.println(sign);
    }
}
