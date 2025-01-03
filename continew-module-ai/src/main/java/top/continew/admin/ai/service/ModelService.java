package top.continew.admin.ai.service;

import top.continew.starter.extension.crud.service.BaseService;
import top.continew.admin.ai.model.query.ModelQuery;
import top.continew.admin.ai.model.req.ModelReq;
import top.continew.admin.ai.model.resp.ModelDetailResp;
import top.continew.admin.ai.model.resp.ModelResp;

/**
 * AI模型业务接口
 *
 * @author weiran
 * @since 2025/01/03 14:49
 */
public interface ModelService extends BaseService<ModelResp, ModelDetailResp, ModelQuery, ModelReq> {}