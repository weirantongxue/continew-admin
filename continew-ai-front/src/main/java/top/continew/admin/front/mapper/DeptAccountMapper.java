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

package top.continew.admin.front.mapper;

import org.apache.ibatis.annotations.Update;
import top.continew.admin.front.model.entity.DeptAccountDO;
import top.continew.starter.data.mybatis.plus.base.BaseMapper;

/**
 * 部门账户 Mapper
 *
 * @author weiran
 * @since 2024/04/01 18:42
 */
public interface DeptAccountMapper extends BaseMapper<DeptAccountDO> {
    @Update("UPDATE lb_dept_account set balance_token = balance_token - #{balanceToken} where dept_id = #{deptId}")
    int deductBalance(Long deptId, int balanceToken);

    @Update("UPDATE lb_dept_account SET balance_token = balance_token + #{tokenPrice}, recharge_amount = recharge_amount + #{totalFee}, recharge_token = recharge_token + #{tokenPrice} WHERE dept_id = #{deptId}")
    int rechargeBalance(Integer totalFee, Integer tokenPrice, Long deptId);
}