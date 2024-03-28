package top.charles7c.continew.admin.webapi.ai;

import top.charles7c.continew.starter.extension.crud.enums.Api;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import top.charles7c.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.charles7c.continew.starter.extension.crud.controller.BaseController;
import top.charles7c.continew.admin.front.model.query.RefundInfoQuery;
import top.charles7c.continew.admin.front.model.req.RefundInfoReq;
import top.charles7c.continew.admin.front.model.resp.RefundInfoDetailResp;
import top.charles7c.continew.admin.front.model.resp.RefundInfoResp;
import top.charles7c.continew.admin.front.service.RefundInfoService;

/**
 * 退款信息管理 API
 *
 * @author weiran
 * @since 2024/03/28 18:46
 */
@Tag(name = "退款信息管理 API")
@RestController
@CrudRequestMapping(value = "/front/refundInfo", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class RefundInfoController extends BaseController<RefundInfoService, RefundInfoResp, RefundInfoDetailResp, RefundInfoQuery, RefundInfoReq> {}