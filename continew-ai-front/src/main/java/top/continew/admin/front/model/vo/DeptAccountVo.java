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

package top.continew.admin.front.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;

/**
 * 部门账户信息
 *
 * @author weiran
 * @since 2024/04/01 18:42
 */
@Data
public class DeptAccountVo implements Serializable {
    /**
     * 代币余额
     */
    @Schema(description = "代币余额")
    private Integer balanceToken;

    /**
     * 部门账户信息
     */
    @Schema(description = "部门账户信息")
    private Long deptId;

}