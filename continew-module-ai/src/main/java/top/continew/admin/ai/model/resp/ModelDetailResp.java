package top.continew.admin.ai.model.resp;

import java.io.Serial;
import java.time.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;

import top.continew.admin.common.base.BaseDetailResp;

/**
 * AI模型详情信息
 *
 * @author weiran
 * @since 2025/01/03 14:49
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "AI模型详情信息")
public class ModelDetailResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    @Schema(description = "模型名称")
    @ExcelProperty(value = "模型名称")
    private String name;

    /**
     * 模型代号
     */
    @Schema(description = "模型代号")
    @ExcelProperty(value = "模型代号")
    private String modelCode;

    /**
     * 模型类型,1:大语言模型,2:文生图
     */
    @Schema(description = "模型类型,1:大语言模型,2:文生图")
    @ExcelProperty(value = "模型类型,1:大语言模型,2:文生图")
    private Integer modelType;

    /**
     * 预设prompt
     */
    @Schema(description = "预设prompt")
    @ExcelProperty(value = "预设prompt")
    private String systemPrompt;

    /**
     * 模型图标
     */
    @Schema(description = "模型图标")
    @ExcelProperty(value = "模型图标")
    private String icon;

    /**
     * 模型地址
     */
    @Schema(description = "模型地址")
    @ExcelProperty(value = "模型地址")
    private String url;

    /**
     * apikey
     */
    @Schema(description = "apikey")
    @ExcelProperty(value = "apikey")
    private String apiKey;

    /**
     * 回调地址
     */
    @Schema(description = "回调地址")
    @ExcelProperty(value = "回调地址")
    private String callBack;

    /**
     * stream:流式,sync:同步,async:异步
     */
    @Schema(description = "stream:流式,sync:同步,async:异步")
    @ExcelProperty(value = "stream:流式,sync:同步,async:异步")
    private String resType;

    /**
     * 描述
     */
    @Schema(description = "描述")
    @ExcelProperty(value = "描述")
    private String introduction;

    /**
     * 排序值
     */
    @Schema(description = "排序值")
    @ExcelProperty(value = "排序值")
    private Integer sort;

    /**
     * 状态（1：启用；2：禁用）
     */
    @Schema(description = "状态（1：启用；2：禁用）")
    @ExcelProperty(value = "状态（1：启用；2：禁用）")
    private Integer status;
}