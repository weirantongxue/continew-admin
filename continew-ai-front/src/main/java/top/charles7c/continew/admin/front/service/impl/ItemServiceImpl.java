package top.charles7c.continew.admin.front.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import top.charles7c.continew.starter.extension.crud.service.impl.BaseServiceImpl;
import top.charles7c.continew.admin.front.mapper.ItemMapper;
import top.charles7c.continew.admin.front.model.entity.ItemDO;
import top.charles7c.continew.admin.front.model.query.ItemQuery;
import top.charles7c.continew.admin.front.model.req.ItemReq;
import top.charles7c.continew.admin.front.model.resp.ItemDetailResp;
import top.charles7c.continew.admin.front.model.resp.ItemResp;
import top.charles7c.continew.admin.front.service.ItemService;

/**
 * AI会话业务实现
 *
 * @author weiran
 * @since 2024/03/11 18:36
 */
@Service
@RequiredArgsConstructor
public class ItemServiceImpl extends BaseServiceImpl<ItemMapper, ItemDO, ItemResp, ItemDetailResp, ItemQuery, ItemReq> implements ItemService {}