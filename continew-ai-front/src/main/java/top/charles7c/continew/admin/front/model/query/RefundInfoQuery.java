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

package top.charles7c.continew.admin.front.model.query;

import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.charles7c.continew.starter.data.core.annotation.Query;
import top.charles7c.continew.starter.data.core.enums.QueryType;

/**
 * 退款信息查询条件
 *
 * @author weiran
 * @since 2024/03/28 17:25
 */
@Data
@Schema(description = "退款信息查询条件")
public class RefundInfoQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商户订单编号
     */
    @Schema(description = "商户订单编号")
    @Query(type = QueryType.EQ)
    private String orderNo;

    /**
     * 商户退款单编号
     */
    @Schema(description = "商户退款单编号")
    @Query(type = QueryType.EQ)
    private String refundNo;

    /**
     * 退款状态
     */
    @Schema(description = "退款状态")
    @Query(type = QueryType.EQ)
    private String refundStatus;
}