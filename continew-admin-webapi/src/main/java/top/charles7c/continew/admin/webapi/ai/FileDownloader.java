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

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import top.charles7c.continew.admin.common.converter.FileToMultipartFileConverter;

import java.io.IOException;

@Slf4j
public class FileDownloader {

    public static void main(String[] args) throws IOException {
        String fileUrl = "https://inews.gtimg.com/om_bt/OMvPDmiuH_X5Vq1YLNgbFEzD2h_-2dCfWQ7xZFcKFSEsAAA/641";
        MultipartFile multipartFile = FileToMultipartFileConverter.convert(fileUrl);

        System.out.println("File Name: " + multipartFile.getOriginalFilename());
        System.out.println("Content Type: " + multipartFile.getContentType());
        System.out.println("File Size: " + multipartFile.getSize());
    }

}
