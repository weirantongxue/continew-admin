package top.charles7c.continew.admin.front.model.entity;

import java.io.Serial;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableName;

import top.charles7c.continew.starter.extension.crud.model.entity.BaseDO;

/**
 * AI会话实体
 *
 * @author weiran
 * @since 2024/03/11 18:36
 */
@Data
@TableName("lb_item")
public class ItemDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 会话id
     */
    private String itemId;

    /**
     * 会话名称
     */
    private String name;

    /**
     * 最后一条消息
     */
    private String lastMessage;

    /**
     * 消息数
     */
    private Integer number;

}