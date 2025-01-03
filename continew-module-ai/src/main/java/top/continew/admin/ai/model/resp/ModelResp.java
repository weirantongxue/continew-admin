package top.continew.admin.ai.model.resp;

import java.io.Serial;
import java.time.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.continew.admin.common.base.BaseResp;

/**
 * AI模型信息
 *
 * @author weiran
 * @since 2025/01/03 14:49
 */
@Data
@Schema(description = "AI模型信息")
public class ModelResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    @Schema(description = "模型名称")
    private String name;

    /**
     * 模型代号
     */
    @Schema(description = "模型代号")
    private String modelCode;

    /**
     * 模型类型,1:大语言模型,2:文生图
     */
    @Schema(description = "模型类型,1:大语言模型,2:文生图")
    private Integer modelType;

    /**
     * 预设prompt
     */
    @Schema(description = "预设prompt")
    private String systemPrompt;

    /**
     * 模型图标
     */
    @Schema(description = "模型图标")
    private String icon;

    /**
     * 模型地址
     */
    @Schema(description = "模型地址")
    private String url;

    /**
     * apikey
     */
    @Schema(description = "apikey")
    private String apiKey;

    /**
     * 回调地址
     */
    @Schema(description = "回调地址")
    private String callBack;

    /**
     * stream:流式,sync:同步,async:异步
     */
    @Schema(description = "stream:流式,sync:同步,async:异步")
    private String resType;

    /**
     * 描述
     */
    @Schema(description = "描述")
    private String introduction;

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

    /**
     * 修改人
     */
    @Schema(description = "修改人")
    private Long updateUser;

    /**
     * 修改时间
     */
    @Schema(description = "修改时间")
    private LocalDateTime updateTime;
}