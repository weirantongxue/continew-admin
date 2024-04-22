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

package top.continew.admin.front.model.resp;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.starter.extension.crud.model.resp.BaseDetailResp;

import java.io.Serial;

/**
 * 支付配置详情信息
 *
 * @author weiran
 * @since 2024/03/30 16:11
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "支付配置详情信息")
public class PayConfigDetailResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 支付名称
     */
    @Schema(description = "支付名称")
    @ExcelProperty(value = "支付名称")
    private String name;

    /**
     * 渠道图标
     */
    @Schema(description = "渠道图标")
    @ExcelProperty(value = "渠道图标")
    private String icon;

    /**
     * 支付方式: [1=余额支付, 2=微信支付, 3=支付宝支付]
     */
    @Schema(description = "支付方式: [1=余额支付, 2=微信支付, 3=支付宝支付]")
    @ExcelProperty(value = "支付方式: [1=余额支付, 2=微信支付, 3=支付宝支付]")
    private Boolean way;

    /**
     * 排序编号
     */
    @Schema(description = "排序编号")
    @ExcelProperty(value = "排序编号")
    private Integer sort;

    /**
     * 备注信息
     */
    @Schema(description = "备注信息")
    @ExcelProperty(value = "备注信息")
    private String remark;

    /**
     * 配置参数
     */
    @Schema(description = "配置参数")
    @ExcelProperty(value = "配置参数")
    private String params;

    /**
     * 默认支付: [0=否的, 1=是的]
     */
    @Schema(description = "默认支付: [0=否的, 1=是的]")
    @ExcelProperty(value = "默认支付: [0=否的, 1=是的]")
    private Integer isDefault;

    /**
     * 方式状态: [0=关闭, 1=开启]
     */
    @Schema(description = "方式状态: [0=关闭, 1=开启]")
    @ExcelProperty(value = "方式状态: [0=关闭, 1=开启]")
    private Integer status;

    /**
     * 是否删除: [0=否, 1=是]
     */
    @Schema(description = "是否删除: [0=否, 1=是]")
    @ExcelProperty(value = "是否删除: [0=否, 1=是]")
    private Integer isDeleted;
}