/*
 * Copyright (c) 2022-present Charles7c Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
        List<ModelScriptDO> modelScriptDO = this.baseMapper.selectList(new LambdaQueryWrapper<ModelScriptDO>()
            .eq(ModelScriptDO::getStatus, 1));
        List<ModelScriptVo> modelScriptVoList = BeanUtil.copyToList(modelScriptDO, ModelScriptVo.class);
        return modelScriptVoList;
    }
}