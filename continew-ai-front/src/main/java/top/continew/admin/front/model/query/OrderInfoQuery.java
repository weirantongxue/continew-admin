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

package top.continew.admin.front.model.query;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.continew.starter.data.core.annotation.Query;
import top.continew.starter.data.core.enums.QueryType;

import java.io.Serial;
import java.io.Serializable;

/**
 * 订单信息查询条件
 *
 * @author weiran
 * @since 2024/03/28 14:25
 */
@Data
@Schema(description = "订单信息查询条件")
public class OrderInfoQuery implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 订单标题
     */
    @Schema(description = "订单标题")
    @Query(type = QueryType.LIKE)
    private String title;

    /**
     * 商户订单编号
     */
    @Schema(description = "商户订单编号")
    @Query(type = QueryType.EQ)
    private String orderNo;

    /**
     * 支付产品id
     */
    @Schema(description = "支付产品id")
    @Query(type = QueryType.EQ)
    private Long productId;

    /**
     * 订单状态
     */
    @Schema(description = "订单状态")
    @Query(type = QueryType.EQ)
    private String orderStatus;

    /**
     * 部门id
     */
    @Schema(description = "部门id")
    @Query(type = QueryType.EQ)
    private Long deptId;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    @Query(type = QueryType.EQ)
    private Long createUser;
}