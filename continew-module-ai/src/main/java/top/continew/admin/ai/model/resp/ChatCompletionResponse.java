package top.continew.admin.ai.model.resp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import top.continew.admin.ai.model.ChatChoice;
import top.continew.admin.ai.model.Usage;

import java.io.Serializable;
import java.util.List;

/**
 * Created by WeiRan on  2025.01.02 18:31
 */
@Data
public class ChatCompletionResponse implements Serializable {
    private String id;
    private String object;
    private Long created;
    private String model;
    private String systemFingerprint;
    private List<ChatChoice> choices;
    private Usage usage;
}
