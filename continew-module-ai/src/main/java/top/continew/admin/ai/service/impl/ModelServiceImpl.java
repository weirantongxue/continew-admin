package top.continew.admin.ai.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import top.continew.starter.extension.crud.service.BaseServiceImpl;
import top.continew.admin.ai.mapper.ModelMapper;
import top.continew.admin.ai.model.entity.ModelDO;
import top.continew.admin.ai.model.query.ModelQuery;
import top.continew.admin.ai.model.req.ModelReq;
import top.continew.admin.ai.model.resp.ModelDetailResp;
import top.continew.admin.ai.model.resp.ModelResp;
import top.continew.admin.ai.service.ModelService;

/**
 * AI模型业务实现
 *
 * @author weiran
 * @since 2025/01/03 14:49
 */
@Service
@RequiredArgsConstructor
public class ModelServiceImpl extends BaseServiceImpl<ModelMapper, ModelDO, ModelResp, ModelDetailResp, ModelQuery, ModelReq> implements ModelService {}