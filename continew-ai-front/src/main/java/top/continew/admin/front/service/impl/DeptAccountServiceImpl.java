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

package top.continew.admin.front.service.impl;

import cn.hutool.core.util.ObjectUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.continew.admin.front.mapper.DeptAccountMapper;
import top.continew.admin.front.model.entity.DeptAccountDO;
import top.continew.admin.front.model.entity.OrderInfoDO;
import top.continew.admin.front.model.query.DeptAccountQuery;
import top.continew.admin.front.model.req.DeptAccountReq;
import top.continew.admin.front.model.resp.DeptAccountDetailResp;
import top.continew.admin.front.model.resp.DeptAccountResp;
import top.continew.admin.front.model.resp.ProductDetailResp;
import top.continew.admin.front.model.vo.DeptAccountVo;
import top.continew.admin.front.service.DeptAccountService;
import top.continew.admin.front.service.ProductService;
import top.continew.starter.extension.crud.service.impl.BaseServiceImpl;

/**
 * 部门账户业务实现
 *
 * @author weiran
 * @since 2024/04/01 18:42
 */
@Service
@RequiredArgsConstructor
public class DeptAccountServiceImpl extends BaseServiceImpl<DeptAccountMapper, DeptAccountDO, DeptAccountResp, DeptAccountDetailResp, DeptAccountQuery, DeptAccountReq> implements DeptAccountService {
    private final DeptAccountMapper deptAccountMapper;
    private final ProductService productService;

    /**
     * 扣减余额
     *
     * @param deptId
     */
    @Override
    public void deductBalance(Long deptId, int balanceToken) {
        deptAccountMapper.deductBalance(deptId, balanceToken);
    }

    /**
     * 充值余额
     *
     * @param orderInfo
     */
    @Override
    public void rechargeBalance(OrderInfoDO orderInfo) {
        //获取产品信息
        ProductDetailResp productDetailResp = productService.get(orderInfo.getProductId());
        deptAccountMapper.rechargeBalance(orderInfo.getTotalFee(), productDetailResp.getTokenPrice(), orderInfo
            .getDeptId());
    }

    /**
     * 查询余额
     *
     * @return
     */
    @Override
    public DeptAccountVo selectBalance(Long deptId) {
        DeptAccountVo deptAccountVo = new DeptAccountVo();
        DeptAccountDO deptAccountDO = deptAccountMapper.lambdaQuery().eq(DeptAccountDO::getDeptId, deptId).one();
        if (ObjectUtil.isNotNull(deptAccountDO)) {
            deptAccountVo.setBalanceToken(deptAccountDO.getBalanceToken());
            deptAccountVo.setDeptId(deptAccountDO.getDeptId());
            return deptAccountVo;
        }
        deptAccountVo.setBalanceToken(0);
        deptAccountVo.setDeptId(deptId);
        return deptAccountVo;
    }
}