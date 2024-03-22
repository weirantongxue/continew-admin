package top.charles7c.continew.admin.front.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import top.charles7c.continew.admin.front.model.vo.ModelScriptVo;
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
    public List<ModelScriptVo> selectModelScript() {
        List <ModelScriptDO> modelScriptDO= this.baseMapper.selectList(new LambdaQueryWrapper<ModelScriptDO>().eq(ModelScriptDO::getStatus, 1));
        List<ModelScriptVo> modelScriptVoList= BeanUtil.copyToList(modelScriptDO, ModelScriptVo.class);
        return modelScriptVoList;
    }
}