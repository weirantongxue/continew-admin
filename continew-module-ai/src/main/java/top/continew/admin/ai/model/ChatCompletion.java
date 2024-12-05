package top.continew.admin.ai.model;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by WeiRan on  2024.11.09 01:32
 */

@Data
@Builder
public class ChatCompletion implements Serializable {
    private String model;
    private List<Message> messages;
    private String request_id;
    private Boolean do_sample;
    private Boolean stream;
    private Float temperature;
    private Float top_p;
    private Integer max_tokens;
    private List<String> stop;
    private List<Tool> tools;
    private Object tool_choice; // It can be either String or Object, so we use Object type here.
    private String user_id;
}
