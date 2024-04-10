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
 * 支付信息详情信息
 *
 * @author weiran
 * @since 2024/03/28 14:30
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "支付信息详情信息")
public class PaymentInfoDetailResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商户订单编号
     */
    @Schema(description = "商户订单编号")
    @ExcelProperty(value = "商户订单编号")
    private String orderNo;

    /**
     * 支付系统交易编号
     */
    @Schema(description = "支付系统交易编号")
    @ExcelProperty(value = "支付系统交易编号")
    private String transactionId;

    /**
     * 支付类型
     */
    @Schema(description = "支付类型")
    @ExcelProperty(value = "支付类型")
    private String paymentType;

    /**
     * 交易类型
     */
    @Schema(description = "交易类型")
    @ExcelProperty(value = "交易类型")
    private String tradeType;

    /**
     * 交易状态
     */
    @Schema(description = "交易状态")
    @ExcelProperty(value = "交易状态")
    private String tradeState;

    /**
     * 支付金额(元)
     */
    @Schema(description = "支付金额(元)")
    @ExcelProperty(value = "支付金额(元)")
    private Integer payerTotal;

    /**
     * 通知参数
     */
    @Schema(description = "通知参数")
    @ExcelProperty(value = "通知参数")
    private String content;
}