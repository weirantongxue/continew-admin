package top.charles7c.continew.admin.front.service;

import top.charles7c.continew.starter.extension.crud.service.BaseService;
import top.charles7c.continew.admin.front.model.query.ItemQuery;
import top.charles7c.continew.admin.front.model.req.ItemReq;
import top.charles7c.continew.admin.front.model.resp.ItemDetailResp;
import top.charles7c.continew.admin.front.model.resp.ItemResp;

/**
 * AI会话业务接口
 *
 * @author weiran
 * @since 2024/03/11 18:36
 */
public interface ItemService extends BaseService<ItemResp, ItemDetailResp, ItemQuery, ItemReq> {}