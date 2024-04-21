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

import java.io.Serial;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;

import top.continew.starter.extension.crud.model.resp.BaseDetailResp;

/**
 * 产品详情信息
 *
 * @author weiran
 * @since 2024/03/28 14:22
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "产品详情信息")
public class ProductDetailResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    @ExcelProperty(value = "商品名称")
    private String title;

    /**
     * 价格（分）
     */
    @Schema(description = "价格（元）")
    @ExcelProperty(value = "价格（元）")
    private Integer price;

    /**
     * 价格（分）
     */
    @Schema(description = "排序值")
    @ExcelProperty(value = "排序值")
    private Integer sort;

    /**
     * token数量
     */
    @Schema(description = "token数量")
    @ExcelProperty(value = "token数量")
    private Integer tokenPrice;
}