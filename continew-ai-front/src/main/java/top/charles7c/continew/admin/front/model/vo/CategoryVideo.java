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

package top.charles7c.continew.admin.front.model.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by WeiRan on 2024.04.09 10:49
 */
@Data
public class CategoryVideo implements Serializable {
    private int video_id;
    private String name;
    private Integer status;
    private int total_size;
    private int total_transcode_size;
    private String origin_definition;
    private int create_time;
    private String preface_url;
    private String length;
    private Integer publish_status;
    private Integer audit_status;
    private String file_md5;
    private int category_level1_id;
    private int category_level2_id;
    private int category_level3_id;
    private int category_level4_id;
    private int category_level5_id;
}
