package top.continew.admin.ai.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by WeiRan on  2025.01.02 18:33
 */
@Data
public class Usage implements Serializable {
    private long promptTokens;
    private long completionTokens;
    private long totalTokens;
}
