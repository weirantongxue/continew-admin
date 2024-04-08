package top.charles7c.continew.admin.front.model.vo;

import lombok.Data;
import top.charles7c.continew.admin.front.model.resp.CategoryResp;

import java.io.Serializable;
import java.util.List;

/**
 * Created by WeiRan on  2024.04.08 17:37
 */
@Data
public class CategoryVo implements Serializable {
    private Long parent_id;
    private Long id;
    private Integer level;
    private String name;
    private Integer video_count;
    private List<CategoryResp> children;
}
