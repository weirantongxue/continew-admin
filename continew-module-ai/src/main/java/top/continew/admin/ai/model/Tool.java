package top.continew.admin.ai.model;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by WeiRan on  2024.11.09 01:36
 */
@Data
public class Tool implements Serializable {
    private String type;
    private Object function; // It can be a Function object or null.
    private String knowledge_id;
    private String prompt_template;
    private Boolean enable;
    private String search_query;
    private Boolean search_result;
}
