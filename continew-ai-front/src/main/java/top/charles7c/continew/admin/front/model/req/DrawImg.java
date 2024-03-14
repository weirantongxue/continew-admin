package top.charles7c.continew.admin.front.model.req;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by WeiRan on  2024.03.14 18:58
 */
@Data
public class DrawImg implements Serializable {
    private Long id;
    private Integer audit;
    private String imageUrl;
}
