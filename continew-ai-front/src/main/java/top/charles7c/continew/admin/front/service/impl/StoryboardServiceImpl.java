package top.charles7c.continew.admin.front.service.impl;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import top.charles7c.continew.admin.front.mapper.StoryboardFieMapper;
import top.charles7c.continew.admin.front.mapper.StoryboardMapper;
import top.charles7c.continew.admin.front.model.entity.StoryboardDO;
import top.charles7c.continew.admin.front.model.entity.StoryboardFieDO;
import top.charles7c.continew.admin.front.model.resp.StoryboardFieResp;
import top.charles7c.continew.admin.front.model.resp.StoryboardResp;
import top.charles7c.continew.admin.front.model.vo.StoryboardVo;
import top.charles7c.continew.admin.front.service.StoryboardService;

import java.util.List;

/**
 * Created by WeiRan on  2024.03.26 20:12
 */
/**
 * 分镜列业务实现
 *
 * @author weiran
 * @since 2024/03/26 20:11
 */
@Service
@RequiredArgsConstructor
public class StoryboardServiceImpl implements StoryboardService {
    private final StoryboardMapper storyboardMapper;
    private final StoryboardFieMapper storyboardFieMapper;



    @Override
    public StoryboardVo list(Long projectId) {
        StoryboardVo storyboardVo=new StoryboardVo();
        List<StoryboardDO> storyboardDOList = storyboardMapper.lambdaQuery().eq(StoryboardDO::getProjectId, projectId).list();
        List<StoryboardResp> storyboardList= BeanUtil.copyToList(storyboardDOList, StoryboardResp.class);

        List<StoryboardFieDO> storyboardFieDOList= storyboardFieMapper.lambdaQuery().eq(StoryboardFieDO::getProjectId, projectId).list();
        List<StoryboardFieResp> storyboardFieList = BeanUtil.copyToList(storyboardFieDOList, StoryboardFieResp.class);

        storyboardVo.setStoryboardList(storyboardList);
        storyboardVo.setStoryboardFieRespList(storyboardFieList);
        return storyboardVo;
    }


    @Override
    public int add(Long projectId) {
        Integer shot = storyboardMapper.sortMax(projectId);
        StoryboardDO storyboardDO=new StoryboardDO();
        storyboardDO.setShot(shot);
        storyboardDO.setProjectId(projectId);
        return storyboardMapper.insert(storyboardDO);
    }

    @Override
    public int updateTable(StoryboardResp storyboardResp) {
        StoryboardDO storyboardDO = BeanUtil.copyProperties(storyboardResp, StoryboardDO.class);
        return storyboardMapper.updateById(storyboardDO);
    }
}
