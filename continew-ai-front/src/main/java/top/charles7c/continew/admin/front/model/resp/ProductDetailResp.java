package top.charles7c.continew.admin.front.model.resp;

import java.io.Serial;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;

import top.charles7c.continew.starter.extension.crud.model.resp.BaseDetailResp;

/**
 * 产品详情信息
 *
 * @author weiran
 * @since 2024/03/28 14:22
 */
@Data
@ExcelIgnoreUnannotated
@Schema(description = "产品详情信息")
public class ProductDetailResp extends BaseDetailResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    @ExcelProperty(value = "商品名称")
    private String title;

    /**
     * 价格（分）
     */
    @Schema(description = "价格（分）")
    @ExcelProperty(value = "价格（分）")
    private Integer price;

    /**
     * token数量
     */
    @Schema(description = "token数量")
    @ExcelProperty(value = "token数量")
    private Integer tokenPrice;
}