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
 * 退款信息详情信息
 *
 * @author weiran
 * @since 2024/03/28 17:25
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "退款信息详情信息")
public class RefundInfoDetailResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商户订单编号
     */
    @Schema(description = "商户订单编号")
    @ExcelProperty(value = "商户订单编号")
    private String orderNo;

    /**
     * 商户退款单编号
     */
    @Schema(description = "商户退款单编号")
    @ExcelProperty(value = "商户退款单编号")
    private String refundNo;

    /**
     * 支付系统退款单号
     */
    @Schema(description = "支付系统退款单号")
    @ExcelProperty(value = "支付系统退款单号")
    private String refundId;

    /**
     * 原订单金额(元)
     */
    @Schema(description = "原订单金额(元)")
    @ExcelProperty(value = "原订单金额(元)")
    private Integer totalFee;

    /**
     * 退款金额(元)
     */
    @Schema(description = "退款金额(元)")
    @ExcelProperty(value = "退款金额(元)")
    private Integer refund;

    /**
     * 退款原因
     */
    @Schema(description = "退款原因")
    @ExcelProperty(value = "退款原因")
    private String reason;

    /**
     * 退款状态
     */
    @Schema(description = "退款状态")
    @ExcelProperty(value = "退款状态")
    private String refundStatus;

    /**
     * 申请退款返回参数
     */
    @Schema(description = "申请退款返回参数")
    @ExcelProperty(value = "申请退款返回参数")
    private String contentReturn;

    /**
     * 退款结果通知参数
     */
    @Schema(description = "退款结果通知参数")
    @ExcelProperty(value = "退款结果通知参数")
    private String contentNotify;
}