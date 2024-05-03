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

package top.continew.admin.front.model.req;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import top.continew.starter.extension.crud.model.req.BaseReq;

import java.io.Serial;

/**
 * 创建或修改产品信息
 *
 * @author weiran
 * @since 2024/03/28 14:22
 */
@Data
@Schema(description = "创建或修改产品信息")
public class ProductReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    @NotBlank(message = "商品名称不能为空")
    @Length(max = 20, message = "商品名称长度不能超过 {max} 个字符")
    private String title;

    /**
     * 价格（分）
     */
    @Schema(description = "价格（分）")
    @NotNull(message = "价格（分）不能为空")
    private Integer price;

    /**
     * 价格（分）
     */
    @Schema(description = "排序值")
    private Integer sort;

    /**
     * token数量
     */
    @Schema(description = "token数量")
    @NotNull(message = "token数量不能为空")
    private Integer tokenPrice;
}