package top.charles7c.continew.admin.front.service;

import top.charles7c.continew.admin.front.model.entity.ModelScriptDO;
import top.charles7c.continew.admin.front.model.vo.ModelScriptVo;
import top.charles7c.continew.starter.extension.crud.service.BaseService;
import top.charles7c.continew.admin.front.model.query.ModelScriptQuery;
import top.charles7c.continew.admin.front.model.req.ModelScriptReq;
import top.charles7c.continew.admin.front.model.resp.ModelScriptDetailResp;
import top.charles7c.continew.admin.front.model.resp.ModelScriptResp;

import java.util.List;

/**
 * 模型预设脚本业务接口
 *
 * @author weiran
 * @since 2024/03/21 14:48
 */
public interface ModelScriptService extends BaseService<ModelScriptResp, ModelScriptDetailResp, ModelScriptQuery, ModelScriptReq> {
    List<ModelScriptVo> selectModelScript();
}