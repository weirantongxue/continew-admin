package top.continew.admin.ai.model.resp;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by WeiRan on  2025.01.02 18:36
 */
@Data
public class MessageResponse implements Serializable {
    private String role;

    public String content;

    private String name;
}
