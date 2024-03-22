package top.charles7c.continew.admin.front.model.resp;

import java.io.Serial;
import java.time.LocalDateTime;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.charles7c.continew.starter.extension.crud.model.resp.BaseResp;

/**
 * 模型预设脚本信息
 *
 * @author weiran
 * @since 2024/03/21 14:48
 */
@Data
@Schema(description = "模型预设脚本信息")
public class ModelScriptResp extends BaseResp {

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
     * 预设内容
     */
    @Schema(description = "预设内容")
    private String prompt;

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

    /**
     * 状态（1：启用；2：禁用）
     */
    @Schema(description = "状态（1：启用；2：禁用）")
    private Integer status;
}