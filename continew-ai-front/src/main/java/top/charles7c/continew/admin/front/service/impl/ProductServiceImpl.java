package top.charles7c.continew.admin.front.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import top.charles7c.continew.starter.extension.crud.service.impl.BaseServiceImpl;
import top.charles7c.continew.admin.front.mapper.ProductMapper;
import top.charles7c.continew.admin.front.model.entity.ProductDO;
import top.charles7c.continew.admin.front.model.query.ProductQuery;
import top.charles7c.continew.admin.front.model.req.ProductReq;
import top.charles7c.continew.admin.front.model.resp.ProductDetailResp;
import top.charles7c.continew.admin.front.model.resp.ProductResp;
import top.charles7c.continew.admin.front.service.ProductService;

/**
 * 产品业务实现
 *
 * @author weiran
 * @since 2024/03/28 14:25
 */
@Service
@RequiredArgsConstructor
public class ProductServiceImpl extends BaseServiceImpl<ProductMapper, ProductDO, ProductResp, ProductDetailResp, ProductQuery, ProductReq> implements ProductService {}