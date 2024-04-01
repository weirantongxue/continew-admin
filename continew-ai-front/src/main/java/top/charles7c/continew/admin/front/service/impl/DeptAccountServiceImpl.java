package top.charles7c.continew.admin.front.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import top.charles7c.continew.starter.extension.crud.service.impl.BaseServiceImpl;
import top.charles7c.continew.admin.front.mapper.DeptAccountMapper;
import top.charles7c.continew.admin.front.model.entity.DeptAccountDO;
import top.charles7c.continew.admin.front.model.query.DeptAccountQuery;
import top.charles7c.continew.admin.front.model.req.DeptAccountReq;
import top.charles7c.continew.admin.front.model.resp.DeptAccountDetailResp;
import top.charles7c.continew.admin.front.model.resp.DeptAccountResp;
import top.charles7c.continew.admin.front.service.DeptAccountService;

/**
 * 部门账户业务实现
 *
 * @author weiran
 * @since 2024/04/01 18:42
 */
@Service
@RequiredArgsConstructor
public class DeptAccountServiceImpl extends BaseServiceImpl<DeptAccountMapper, DeptAccountDO, DeptAccountResp, DeptAccountDetailResp, DeptAccountQuery, DeptAccountReq> implements DeptAccountService {}