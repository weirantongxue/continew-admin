package top.charles7c.continew.admin.front.service;

import top.charles7c.continew.admin.front.model.entity.StoryboardDO;
import top.charles7c.continew.admin.front.model.resp.StoryboardResp;
import top.charles7c.continew.admin.front.model.vo.StoryboardVo;

/**
 * Created by WeiRan on  2024.03.26 20:12
 */
public interface StoryboardService {
    StoryboardVo list(Long projectId);

    int add(Long projectId);

    int updateTable(StoryboardResp storyboardResp);
}
