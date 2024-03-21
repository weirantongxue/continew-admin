package top.charles7c.continew.admin.front.service;

import top.charles7c.continew.starter.extension.crud.service.BaseService;
import top.charles7c.continew.admin.front.model.query.ModelQuery;
import top.charles7c.continew.admin.front.model.req.ModelReq;
import top.charles7c.continew.admin.front.model.resp.ModelDetailResp;
import top.charles7c.continew.admin.front.model.resp.ModelResp;

/**
 * AI模型业务接口
 *
 * @author weiran
 * @since 2024/03/21 14:48
 */
public interface ModelService extends BaseService<ModelResp, ModelDetailResp, ModelQuery, ModelReq> {}