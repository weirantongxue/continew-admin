package top.continew.admin.ai.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by WeiRan on  2024.11.09 01:28
 */
@Data
@Builder
public class Message  {
    private String role;
    private String content;
}
