package top.charles7c.continew.admin.front.model.resp;

import java.io.Serial;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;

import top.charles7c.continew.starter.extension.crud.model.resp.BaseDetailResp;

/**
 * 模型预设脚本详情信息
 *
 * @author weiran
 * @since 2024/03/21 14:48
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "模型预设脚本详情信息")
public class ModelScriptDetailResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 脚本名称
     */
    @Schema(description = "脚本名称")
    @ExcelProperty(value = "脚本名称")
    private String name;

    /**
     * 模型名称
     */
    @Schema(description = "模型名称")
    @ExcelProperty(value = "模型名称")
    private Integer modelId;

    /**
     * 预设内容
     */
    @Schema(description = "预设内容")
    @ExcelProperty(value = "预设内容")
    private String prompt;

    /**
     * 封面
     */
    @Schema(description = "封面")
    @ExcelProperty(value = "封面")
    private String coverUrl;

    /**
     * 状态（1：启用；2：禁用）
     */
    @Schema(description = "状态（1：启用；2：禁用）")
    @ExcelProperty(value = "状态（1：启用；2：禁用）")
    private Integer status;

    /**
     * 是否删除: 0=否, 1=是
     */
    @Schema(description = "是否删除: 0=否, 1=是")
    @ExcelProperty(value = "是否删除: 0=否, 1=是")
    private Integer isDelete;
}