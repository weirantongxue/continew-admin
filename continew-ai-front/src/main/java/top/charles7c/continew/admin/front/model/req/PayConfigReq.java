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

package top.charles7c.continew.admin.front.model.req;

import java.io.Serial;

import jakarta.validation.constraints.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.validator.constraints.Length;

import top.charles7c.continew.starter.extension.crud.model.req.BaseReq;

/**
 * 创建或修改支付配置信息
 *
 * @author weiran
 * @since 2024/03/30 16:11
 */
@Data
@Schema(description = "创建或修改支付配置信息")
public class PayConfigReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 支付名称
     */
    @Schema(description = "支付名称")
    @NotBlank(message = "支付名称不能为空")
    @Length(max = 32, message = "支付名称长度不能超过 {max} 个字符")
    private String name;

    /**
     * 渠道图标
     */
    @Schema(description = "渠道图标")
    @NotBlank(message = "渠道图标不能为空")
    @Length(max = 255, message = "渠道图标长度不能超过 {max} 个字符")
    private String icon;

    /**
     * 支付方式: [1=余额支付, 2=微信支付, 3=支付宝支付]
     */
    @Schema(description = "支付方式: [1=余额支付, 2=微信支付, 3=支付宝支付]")
    @NotNull(message = "支付方式: [1=余额支付, 2=微信支付, 3=支付宝支付]不能为空")
    private Boolean way;

    /**
     * 排序编号
     */
    @Schema(description = "排序编号")
    @NotNull(message = "排序编号不能为空")
    private Integer sort;

    /**
     * 备注信息
     */
    @Schema(description = "备注信息")
    @NotBlank(message = "备注信息不能为空")
    @Length(max = 255, message = "备注信息长度不能超过 {max} 个字符")
    private String remark;

    /**
     * 配置参数
     */
    @Schema(description = "配置参数")
    @NotBlank(message = "配置参数不能为空")
    @Length(max = 65535, message = "配置参数长度不能超过 {max} 个字符")
    private String params;

    /**
     * 默认支付: [0=否的, 1=是的]
     */
    @Schema(description = "默认支付: [0=否的, 1=是的]")
    @NotNull(message = "默认支付: [0=否的, 1=是的]不能为空")
    private Integer isDefault;

    /**
     * 方式状态: [0=关闭, 1=开启]
     */
    @Schema(description = "方式状态: [0=关闭, 1=开启]")
    @NotNull(message = "方式状态: [0=关闭, 1=开启]不能为空")
    private Integer status;
}