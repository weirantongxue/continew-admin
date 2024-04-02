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

package top.charles7c.continew.admin.front.model.entity;

import java.io.Serial;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableName;

import top.charles7c.continew.starter.extension.crud.model.entity.BaseDO;

/**
 * 部门账户实体
 *
 * @author weiran
 * @since 2024/04/01 18:42
 */
@Data
@TableName("lb_dept_account")
public class DeptAccountDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 代币余额
     */
    private Integer balanceToken;

    /**
     * 赠送代币
     */
    private Integer giveToken;

    /**
     * 充值代币
     */
    private Integer rechargeToken;

    /**
     * 充值金额
     */
    private Integer rechargeAmount;

    /**
     * 部门账户信息
     */
    private Long deptId;
}