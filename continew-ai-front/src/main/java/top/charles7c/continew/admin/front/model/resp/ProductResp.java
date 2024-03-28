package top.charles7c.continew.admin.front.model.resp;

import java.io.Serial;
import java.time.LocalDateTime;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.charles7c.continew.starter.extension.crud.model.resp.BaseResp;

/**
 * 产品信息
 *
 * @author weiran
 * @since 2024/03/28 14:22
 */
@Data
@Schema(description = "产品信息")
public class ProductResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商品名称
     */
    @Schema(description = "商品名称")
    private String title;

    /**
     * 价格（分）
     */
    @Schema(description = "价格（分）")
    private Integer price;

    /**
     * token数量
     */
    @Schema(description = "token数量")
    private Integer tokenPrice;

    /**
     * 修改人
     */
    @Schema(description = "修改人")
    private Long updateUser;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}