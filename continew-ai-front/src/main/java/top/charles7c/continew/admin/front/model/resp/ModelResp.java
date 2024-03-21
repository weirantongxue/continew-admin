package top.charles7c.continew.admin.front.model.resp;

import java.io.Serial;
import java.time.LocalDateTime;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.charles7c.continew.starter.extension.crud.model.resp.BaseResp;

/**
 * AI模型信息
 *
 * @author weiran
 * @since 2024/03/21 14:48
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
     * 模型类型,1:大语言模型,2:文生图
     */
    @Schema(description = "模型类型,1:大语言模型,2:文生图")
    private Integer modelType;

    /**
     * 模型图标
     */
    @Schema(description = "模型图标")
    private String coverUrl;

    /**
     * 模型地址
     */
    @Schema(description = "模型地址")
    private String url;

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
}