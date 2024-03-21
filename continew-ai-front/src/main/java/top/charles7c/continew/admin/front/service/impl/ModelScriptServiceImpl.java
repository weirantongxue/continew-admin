package top.charles7c.continew.admin.front.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import top.charles7c.continew.starter.extension.crud.service.impl.BaseServiceImpl;
import top.charles7c.continew.admin.front.mapper.ModelScriptMapper;
import top.charles7c.continew.admin.front.model.entity.ModelScriptDO;
import top.charles7c.continew.admin.front.model.query.ModelScriptQuery;
import top.charles7c.continew.admin.front.model.req.ModelScriptReq;
import top.charles7c.continew.admin.front.model.resp.ModelScriptDetailResp;
import top.charles7c.continew.admin.front.model.resp.ModelScriptResp;
import top.charles7c.continew.admin.front.service.ModelScriptService;

import java.util.List;

/**
 * 模型预设脚本业务实现
 *
 * @author weiran
 * @since 2024/03/21 14:48
 */
@Service
@RequiredArgsConstructor
public class ModelScriptServiceImpl extends BaseServiceImpl<ModelScriptMapper, ModelScriptDO, ModelScriptResp, ModelScriptDetailResp, ModelScriptQuery, ModelScriptReq> implements ModelScriptService {
    @Override
    public void selectModelScript() {
        List<ModelScriptDO> modelScriptRespList= this.baseMapper.selectList(null);
        System.out.println("121121212121121");
    }
}