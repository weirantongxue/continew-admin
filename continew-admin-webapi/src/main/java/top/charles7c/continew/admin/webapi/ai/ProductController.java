package top.charles7c.continew.admin.webapi.ai;

import top.charles7c.continew.starter.extension.crud.enums.Api;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import top.charles7c.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.charles7c.continew.starter.extension.crud.controller.BaseController;
import top.charles7c.continew.admin.front.model.query.ProductQuery;
import top.charles7c.continew.admin.front.model.req.ProductReq;
import top.charles7c.continew.admin.front.model.resp.ProductDetailResp;
import top.charles7c.continew.admin.front.model.resp.ProductResp;
import top.charles7c.continew.admin.front.service.ProductService;

/**
 * 产品管理 API
 *
 * @author weiran
 * @since 2024/03/28 14:29
 */
@Tag(name = "产品管理 API")
@RestController
@CrudRequestMapping(value = "/ai/product", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class ProductController extends BaseController<ProductService, ProductResp, ProductDetailResp, ProductQuery, ProductReq> {




}