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

package top.charles7c.continew.admin.common.util;

import okhttp3.OkHttpClient;
import top.charles7c.continew.admin.common.enums.DataScopeEnum;

import java.net.Proxy;
import java.util.concurrent.TimeUnit;

public class OkHttpUtils {
    private static OkHttpClient okHttpClient;

    public static OkHttpClient getInstance() {
        if (okHttpClient == null) { //加同步安全
            synchronized (OkHttpClient.class) {
                if (okHttpClient == null) { //okhttp可以缓存数据....指定缓存路径
                    okHttpClient = new OkHttpClient.Builder()//构建器
                        .proxy(Proxy.NO_PROXY) //来屏蔽系统代理
                        .connectTimeout(10, TimeUnit.SECONDS)
                        .writeTimeout(50, TimeUnit.SECONDS)
                        .readTimeout(50, TimeUnit.SECONDS)
                        .build();
                }
            }
        }
        return okHttpClient;
    }
}
