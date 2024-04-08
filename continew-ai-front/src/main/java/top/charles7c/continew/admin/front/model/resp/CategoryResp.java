package top.charles7c.continew.admin.front.model.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by WeiRan on  2024.04.08 17:36
 */
@Data
public class CategoryResp implements Serializable {
    private Long parent_id ;
    private Long id ;
    private Integer level;
    private String name ;
    private Integer video_count;
}
