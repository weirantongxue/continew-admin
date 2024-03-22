package top.charles7c.continew.admin.front.model.vo;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.charles7c.continew.starter.extension.crud.model.entity.BaseDO;

import java.io.Serial;
import java.io.Serializable;

/**
 * 模型预设脚本实体
 *
 * @author weiran
 * @since 2024/03/21 14:48
 */
@Data
public class ModelScriptVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 脚本名称
     */
    @Schema(description = "脚本名称")
    private String name;

    /**
     * 模型名称
     */
    @Schema(description = "模型名称")
    private Long modelId;

    /**
     * 封面
     */
    @Schema(description = "封面")
    private String coverUrl;

    /**
     * 简介
     */
    @Schema(description = ",简介")
    private String description;

    /**
     * 排序值
     */
    @Schema(description = "排序值")
    private Integer sort;

}