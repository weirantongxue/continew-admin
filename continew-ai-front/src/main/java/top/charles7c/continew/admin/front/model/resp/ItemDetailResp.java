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

package top.charles7c.continew.admin.front.model.resp;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.charles7c.continew.starter.extension.crud.model.resp.BaseDetailResp;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 * AI会话详情信息
 *
 * @author weiran
 * @since 2024/03/11 18:36
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "AI会话详情信息")
public class ItemDetailResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 会话名称
     */
    @Schema(description = "会话名称")
    @ExcelProperty(value = "会话名称")
    private String name;

    /**
     * 最后一条消息
     */
    @Schema(description = "最后一条消息")
    @ExcelProperty(value = "最后一条消息")
    private String lastMessage;

    /**
     * 消息数
     */
    @Schema(description = "消息数")
    @ExcelProperty(value = "消息数")
    private Integer number;

    @Schema(description = "更新时间")
    @ExcelProperty(value = "更新时间")
    private LocalDateTime createTime;

}