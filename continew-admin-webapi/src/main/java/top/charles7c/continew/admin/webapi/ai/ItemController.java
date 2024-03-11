package top.charles7c.continew.admin.webapi.ai;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;
import top.charles7c.continew.admin.front.model.query.ItemQuery;
import top.charles7c.continew.admin.front.model.req.ItemReq;
import top.charles7c.continew.admin.front.model.resp.ItemDetailResp;
import top.charles7c.continew.admin.front.model.resp.ItemResp;
import top.charles7c.continew.admin.front.service.ItemService;
import top.charles7c.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.charles7c.continew.starter.extension.crud.controller.BaseController;
import top.charles7c.continew.starter.extension.crud.enums.Api;

/**
 * AI会话管理 API
 *
 * @author weiran
 * @since 2024/03/11 18:36
 */
@Tag(name = "AI会话管理 API")
@RestController
@CrudRequestMapping(value = "/front/item", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class ItemController extends BaseController<ItemService, ItemResp, ItemDetailResp, ItemQuery, ItemReq> {


}