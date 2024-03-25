package top.charles7c.continew.admin.front.model.entity;

import java.io.Serial;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableName;

import top.charles7c.continew.starter.extension.crud.model.entity.BaseDO;

/**
 * 订单信息实体
 *
 * @author weiran
 * @since 2024/03/25 23:38
 */
@Data
@TableName("lb_order_info")
public class OrderInfoDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 订单标题
     */
    private String title;

    /**
     * 商户订单编号
     */
    private String orderNo;


    /**
     * 支付产品id
     */
    private Long productId;

    /**
     * 订单金额(分)
     */
    private Integer totalFee;

    /**
     * 订单二维码连接
     */
    private String codeUrl;

    /**
     * 订单状态
     */
    private String orderStatus;
}