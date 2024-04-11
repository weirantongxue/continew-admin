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

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.validator.constraints.Length;

import top.charles7c.continew.starter.extension.crud.model.req.BaseReq;

/**
 * 创建或修改订单信息信息
 *
 * @author weiran
 * @since 2024/03/28 14:25
 */
@Data
@Schema(description = "创建或修改订单信息信息")
public class OrderInfoReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 订单标题
     */
    @Schema(description = "订单标题")
    @Length(max = 256, message = "订单标题长度不能超过 {max} 个字符")
    private String title;

    /**
     * 商户订单编号
     */
    @Schema(description = "商户订单编号")
    @Length(max = 50, message = "商户订单编号长度不能超过 {max} 个字符")
    private String orderNo;

    /**
     * 支付产品id
     */
    @Schema(description = "支付产品id")
    private Long productId;

    /**
     * 订单金额(元)
     */
    @Schema(description = "订单金额(元)")
    private Integer totalFee;

    /**
     * 订单二维码连接
     */
    @Schema(description = "订单二维码连接")
    @Length(max = 50, message = "订单二维码连接长度不能超过 {max} 个字符")
    private String codeUrl;

    /**
     * 订单状态
     */
    @Schema(description = "订单状态")
    @Length(max = 10, message = "订单状态长度不能超过 {max} 个字符")
    private String orderStatus;

    /**
     * 部门id
     */
    @Schema(description = "部门id")
    private Long deptId;
}