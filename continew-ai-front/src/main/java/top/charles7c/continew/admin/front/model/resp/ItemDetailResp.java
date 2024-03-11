package top.charles7c.continew.admin.front.model.resp;

import java.io.Serial;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;

import top.charles7c.continew.starter.extension.crud.model.resp.BaseDetailResp;

/**
 * AI会话详情信息
 *
 * @author weiran
 * @since 2024/03/11 18:36
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "AI会话详情信息")
public class ItemDetailResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 会话id
     */
    @Schema(description = "会话id")
    @ExcelProperty(value = "会话id")
    private String itemId;

    /**
     * 会话名称
     */
    @Schema(description = "会话名称")
    @ExcelProperty(value = "会话名称")
    private String name;

    /**
     * 最后一条消息
     */
    @Schema(description = "最后一条消息")
    @ExcelProperty(value = "最后一条消息")
    private String lastMessage;

    /**
     * 消息数
     */
    @Schema(description = "消息数")
    @ExcelProperty(value = "消息数")
    private Integer number;

}