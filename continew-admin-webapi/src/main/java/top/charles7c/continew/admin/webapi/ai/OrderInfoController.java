package top.charles7c.continew.admin.webapi.ai;

import top.charles7c.continew.starter.extension.crud.enums.Api;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import top.charles7c.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.charles7c.continew.starter.extension.crud.controller.BaseController;
import top.charles7c.continew.admin.front.model.query.OrderInfoQuery;
import top.charles7c.continew.admin.front.model.req.OrderInfoReq;
import top.charles7c.continew.admin.front.model.resp.OrderInfoDetailResp;
import top.charles7c.continew.admin.front.model.resp.OrderInfoResp;
import top.charles7c.continew.admin.front.service.OrderInfoService;

/**
 * 订单信息管理 API
 *
 * @author weiran
 * @since 2024/03/28 14:25
 */
@Tag(name = "订单信息管理 API")
@RestController
@CrudRequestMapping(value = "/ai/orderInfo", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class OrderInfoController extends BaseController<OrderInfoService, OrderInfoResp, OrderInfoDetailResp, OrderInfoQuery, OrderInfoReq> {



}