package top.continew.admin.ai.model.req;

import java.io.Serial;
import java.time.*;

import jakarta.validation.constraints.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.validator.constraints.Length;

import top.continew.starter.extension.crud.model.req.BaseReq;

/**
 * 创建或修改AI模型参数
 *
 * @author weiran
 * @since 2025/01/03 17:29
 */
@Data
@Schema(description = "创建或修改AI模型参数")
public class ModelReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    @Schema(description = "模型名称")
    @NotBlank(message = "模型名称不能为空")
    @Length(max = 255, message = "模型名称长度不能超过 {max} 个字符")
    private String name;

    /**
     * 模型代号
     */
    @Schema(description = "模型代号")
    @NotBlank(message = "模型代号不能为空")
    @Length(max = 255, message = "模型代号长度不能超过 {max} 个字符")
    private String modelCode;

    /**
     * 模型类型,1:大语言模型,2:文生图
     */
    @Schema(description = "模型类型,1:大语言模型,2:文生图")
    @NotNull(message = "模型类型,1:大语言模型,2:文生图不能为空")
    private Integer modelType;

    /**
     * 预设prompt
     */
    @Schema(description = "预设prompt")
    @Length(max = 2048, message = "预设prompt长度不能超过 {max} 个字符")
    private String systemPrompt;

    /**
     * 模型图标
     */
    @Schema(description = "模型图标")
    @Length(max = 255, message = "模型图标长度不能超过 {max} 个字符")
    private String icon;

    /**
     * 模型地址
     */
    @Schema(description = "模型地址")
    @NotBlank(message = "模型地址不能为空")
    @Length(max = 255, message = "模型地址长度不能超过 {max} 个字符")
    private String url;

    /**
     * apikey
     */
    @Schema(description = "apikey")
    @Length(max = 255, message = "apikey长度不能超过 {max} 个字符")
    private String apiKey;

    /**
     * stream:流式,sync:同步,async:异步
     */
    @Schema(description = "stream:流式,sync:同步,async:异步")
    @NotBlank(message = "stream:流式,sync:同步,async:异步不能为空")
    @Length(max = 32, message = "stream:流式,sync:同步,async:异步长度不能超过 {max} 个字符")
    private String resType;

    /**
     * 排序值
     */
    @Schema(description = "排序值")
    @NotNull(message = "排序值不能为空")
    private Integer sort;

    /**
     * 状态（1：启用；2：禁用）
     */
    @Schema(description = "状态（1：启用；2：禁用）")
    @NotNull(message = "状态（1：启用；2：禁用）不能为空")
    private Integer status;
}