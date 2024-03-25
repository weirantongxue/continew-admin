package top.charles7c.continew.admin.front.model.entity;

import java.io.Serial;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableName;

import top.charles7c.continew.starter.extension.crud.model.entity.BaseDO;

/**
 * 产品实体
 *
 * @author weiran
 * @since 2024/03/25 23:36
 */
@Data
@TableName("lb_product")
public class ProductDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商品名称
     */
    private String title;

    /**
     * 价格（分）
     */
    private Integer price;
}