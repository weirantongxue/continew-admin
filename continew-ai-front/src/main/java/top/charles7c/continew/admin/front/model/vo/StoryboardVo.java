package top.charles7c.continew.admin.front.model.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.charles7c.continew.admin.front.model.entity.StoryboardDO;
import top.charles7c.continew.admin.front.model.entity.StoryboardFieDO;
import top.charles7c.continew.admin.front.model.resp.StoryboardFieResp;
import top.charles7c.continew.admin.front.model.resp.StoryboardResp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by WeiRan on  2024.03.26 20:18
 */
@Data
public class StoryboardVo implements Serializable {
    @Schema(description = "分镜名信息")
    private List<StoryboardFieResp> storyboardFieRespList;
    @Schema(description = "分镜列信息")
    private List<StoryboardResp> storyboardList;

}
