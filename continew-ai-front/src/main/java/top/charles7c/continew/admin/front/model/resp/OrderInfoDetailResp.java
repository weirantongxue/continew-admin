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

import top.charles7c.continew.starter.extension.crud.model.resp.BaseDetailResp;

/**
 * 订单信息详情信息
 *
 * @author weiran
 * @since 2024/03/28 14:25
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "订单信息详情信息")
public class OrderInfoDetailResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 订单标题
     */
    @Schema(description = "订单标题")
    @ExcelProperty(value = "订单标题")
    private String title;

    /**
     * 商户订单编号
     */
    @Schema(description = "商户订单编号")
    @ExcelProperty(value = "商户订单编号")
    private String orderNo;

    /**
     * 支付产品id
     */
    @Schema(description = "支付产品id")
    @ExcelProperty(value = "支付产品id")
    private Long productId;

    /**
     * 订单金额(分)
     */
    @Schema(description = "订单金额(分)")
    @ExcelProperty(value = "订单金额(分)")
    private Integer totalFee;

    /**
     * 订单二维码连接
     */
    @Schema(description = "订单二维码连接")
    @ExcelProperty(value = "订单二维码连接")
    private String codeUrl;

    /**
     * 订单状态
     */
    @Schema(description = "订单状态")
    @ExcelProperty(value = "订单状态")
    private String orderStatus;
}