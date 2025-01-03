package top.continew.admin.controller.ai;

import top.continew.starter.extension.crud.enums.Api;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;

import top.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.continew.admin.common.base.BaseController;
import top.continew.admin.ai.model.query.ModelQuery;
import top.continew.admin.ai.model.req.ModelReq;
import top.continew.admin.ai.model.resp.ModelDetailResp;
import top.continew.admin.ai.model.resp.ModelResp;
import top.continew.admin.ai.service.ModelService;

/**
 * AI模型管理 API
 *
 * @author weiran
 * @since 2025/01/03 14:49
 */
@Tag(name = "AI模型管理 API")
@RestController
@CrudRequestMapping(value = "/ai/model", api = {Api.PAGE, Api.DETAIL, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class ModelController extends BaseController<ModelService, ModelResp, ModelDetailResp, ModelQuery, ModelReq> {}