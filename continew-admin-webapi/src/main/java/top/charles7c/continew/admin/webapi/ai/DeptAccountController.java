package top.charles7c.continew.admin.webapi.ai;

import top.charles7c.continew.starter.extension.crud.enums.Api;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import top.charles7c.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.charles7c.continew.starter.extension.crud.controller.BaseController;
import top.charles7c.continew.admin.front.model.query.DeptAccountQuery;
import top.charles7c.continew.admin.front.model.req.DeptAccountReq;
import top.charles7c.continew.admin.front.model.resp.DeptAccountDetailResp;
import top.charles7c.continew.admin.front.model.resp.DeptAccountResp;
import top.charles7c.continew.admin.front.service.DeptAccountService;

/**
 * 部门账户管理 API
 *
 * @author weiran
 * @since 2024/04/01 18:42
 */
@Tag(name = "部门账户管理 API")
@RestController
@CrudRequestMapping(value = "/ai/deptAccount", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class DeptAccountController extends BaseController<DeptAccountService, DeptAccountResp, DeptAccountDetailResp, DeptAccountQuery, DeptAccountReq> {}