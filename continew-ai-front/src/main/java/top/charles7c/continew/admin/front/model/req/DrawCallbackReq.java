package top.charles7c.continew.admin.front.model.req;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by WeiRan on  2024.03.14 18:57
 */

@Data
public class DrawCallbackReq implements Serializable {
    private Integer audit;
    private Integer progress;
    private String state;
    private String nonce;
    private String taskId;
    private List<DrawImg> images;
}
