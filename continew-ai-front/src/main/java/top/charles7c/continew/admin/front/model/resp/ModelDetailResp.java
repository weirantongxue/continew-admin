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
import top.continew.starter.extension.crud.model.resp.BaseDetailResp;

import java.io.Serial;

/**
 * AI模型详情信息
 *
 * @author weiran
 * @since 2024/03/21 14:48
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "AI模型详情信息")
public class ModelDetailResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    @Schema(description = "模型名称")
    @ExcelProperty(value = "模型名称")
    private String name;

    /**
     * 模型类型,1:大语言模型,2:文生图
     */
    @Schema(description = "模型类型,1:大语言模型,2:文生图")
    @ExcelProperty(value = "模型类型,1:大语言模型,2:文生图")
    private Integer modelType;

    /**
     * 模型图标
     */
    @Schema(description = "模型图标")
    @ExcelProperty(value = "模型图标")
    private String coverUrl;

    /**
     * 模型地址
     */
    @Schema(description = "模型地址")
    @ExcelProperty(value = "模型地址")
    private String url;

    /**
     * apikey
     */
    @Schema(description = "apikey")
    @ExcelProperty(value = "apikey")
    private String apiKey;

    /**
     * 回调地址
     */
    @Schema(description = "回调地址")
    @ExcelProperty(value = "回调地址")
    private String callBack;

    /**
     * 返回类型:stream:流式,sync:同步,async:异步
     */
    @ExcelProperty(value = "返回类型:stream:流式,sync:同步,async:异步")
    @Schema(description = "返回类型:stream:流式,sync:同步,async:异步")
    private String resType;

    /**
     * 描述
     */
    @Schema(description = "描述")
    @ExcelProperty(value = "描述")
    private String introduction;

    /**
     * 排序值
     */
    @Schema(description = "排序值")
    @ExcelProperty(value = "排序值")
    private Integer sort;

    /**
     * 状态（1：启用；2：禁用）
     */
    @Schema(description = "状态（1：启用；2：禁用）")
    @ExcelProperty(value = "状态（1：启用；2：禁用）")
    private Integer status;

    /**
     * 是否删除: 0=否, 1=是
     */
    @Schema(description = "是否删除: 0=否, 1=是")
    @ExcelProperty(value = "是否删除: 0=否, 1=是")
    private Integer isDelete;
}