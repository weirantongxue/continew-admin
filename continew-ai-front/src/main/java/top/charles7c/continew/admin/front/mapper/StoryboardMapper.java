package top.charles7c.continew.admin.front.mapper;

import org.apache.ibatis.annotations.Select;
import top.charles7c.continew.starter.data.mybatis.plus.base.BaseMapper;
import top.charles7c.continew.admin.front.model.entity.StoryboardDO;

/**
 * 分镜列 Mapper
 *
 * @author weiran
 * @since 2024/03/26 20:11
 */
public interface StoryboardMapper extends BaseMapper<StoryboardDO> {
    @Select("SELECT COALESCE(MAX(shot), 0)+1 from lb_storyboard where project_id = #{projectId}")
    Integer sortMax(Long projectId);
}