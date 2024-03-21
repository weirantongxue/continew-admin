package top.charles7c.continew.admin.front.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import top.charles7c.continew.starter.extension.crud.service.impl.BaseServiceImpl;
import top.charles7c.continew.admin.front.mapper.ModelMapper;
import top.charles7c.continew.admin.front.model.entity.ModelDO;
import top.charles7c.continew.admin.front.model.query.ModelQuery;
import top.charles7c.continew.admin.front.model.req.ModelReq;
import top.charles7c.continew.admin.front.model.resp.ModelDetailResp;
import top.charles7c.continew.admin.front.model.resp.ModelResp;
import top.charles7c.continew.admin.front.service.ModelService;

/**
 * AI模型业务实现
 *
 * @author weiran
 * @since 2024/03/21 14:48
 */
@Service
@RequiredArgsConstructor
public class ModelServiceImpl extends BaseServiceImpl<ModelMapper, ModelDO, ModelResp, ModelDetailResp, ModelQuery, ModelReq> implements ModelService {}