package top.charles7c.continew.admin.front.service;

import top.charles7c.continew.starter.extension.crud.service.BaseService;
import top.charles7c.continew.admin.front.model.query.DeptAccountQuery;
import top.charles7c.continew.admin.front.model.req.DeptAccountReq;
import top.charles7c.continew.admin.front.model.resp.DeptAccountDetailResp;
import top.charles7c.continew.admin.front.model.resp.DeptAccountResp;

/**
 * 部门账户业务接口
 *
 * @author weiran
 * @since 2024/04/01 18:42
 */
public interface DeptAccountService extends BaseService<DeptAccountResp, DeptAccountDetailResp, DeptAccountQuery, DeptAccountReq> {}