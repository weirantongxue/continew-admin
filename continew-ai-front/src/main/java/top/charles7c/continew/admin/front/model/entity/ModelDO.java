package top.charles7c.continew.admin.front.model.entity;

import java.io.Serial;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableName;

import top.charles7c.continew.starter.extension.crud.model.entity.BaseDO;

/**
 * AI模型实体
 *
 * @author weiran
 * @since 2024/03/21 14:48
 */
@Data
@TableName("lb_model")
public class ModelDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 模型名称
     */
    private String name;

    /**
     * 模型类型,1:大语言模型,2:文生图
     */
    private Integer modelType;

    /**
     * 模型图标
     */
    private String coverUrl;

    /**
     * 模型地址
     */
    private String url;

    /**
     * 描述
     */
    private String introduction;

    /**
     * 排序值
     */
    private Integer sort;

    /**
     * 状态（1：启用；2：禁用）
     */
    private Integer status;

    /**
     * 是否删除: 0=否, 1=是
     */
    private Integer isDelete;
}