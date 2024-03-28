package top.charles7c.continew.admin.front.model.req;

import java.io.Serial;
import java.time.LocalDateTime;

import jakarta.validation.constraints.*;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.validator.constraints.Length;

import top.charles7c.continew.starter.extension.crud.model.req.BaseReq;

/**
 * 创建或修改产品信息
 *
 * @author weiran
 * @since 2024/03/28 14:22
 */
@Data
@Schema(description = "创建或修改产品信息")
public class ProductReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    @NotBlank(message = "商品名称不能为空")
    @Length(max = 20, message = "商品名称长度不能超过 {max} 个字符")
    private String title;

    /**
     * 价格（分）
     */
    @Schema(description = "价格（分）")
    @NotNull(message = "价格（分）不能为空")
    private Integer price;

    /**
     * token数量
     */
    @Schema(description = "token数量")
    @NotNull(message = "token数量不能为空")
    private Integer tokenPrice;
}