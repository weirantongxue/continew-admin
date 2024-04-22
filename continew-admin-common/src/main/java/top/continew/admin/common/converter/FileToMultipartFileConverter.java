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

package top.continew.admin.common.converter;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by WeiRan on 2024.03.15 16:38
 */
public class FileToMultipartFileConverter {
    public static MultipartFile convert(String fileUrl) throws IOException {
        URL url = new URL(fileUrl);
        URLConnection connection = url.openConnection();

        String fileName = getFileName(url);
        String contentType = connection.getContentType();

        try (InputStream inputStream = connection.getInputStream()) {
            byte[] bytes = inputStream.readAllBytes();
            return new MockMultipartFile(fileName, fileName, contentType, bytes);
        }
    }

    private static String getFileName(URL url) {
        String[] pathSegments = url.getPath().split("/");
        return pathSegments[pathSegments.length - 1];
    }
}
