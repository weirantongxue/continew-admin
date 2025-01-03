package top.continew.admin.ai.model.entity;

import java.io.Serial;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableName;

import top.continew.starter.extension.crud.model.entity.BaseDO;

/**
 * AI模型实体
 *
 * @author weiran
 * @since 2025/01/03 14:49
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
     * 模型代号
     */
    private String modelCode;

    /**
     * 模型类型,1:大语言模型,2:文生图
     */
    private Integer modelType;

    /**
     * 预设prompt
     */
    private String systemPrompt;

    /**
     * 模型图标
     */
    private String icon;

    /**
     * 模型地址
     */
    private String url;

    /**
     * apikey
     */
    private String apiKey;

    /**
     * 回调地址
     */
    private String callBack;

    /**
     * stream:流式,sync:同步,async:异步
     */
    private String resType;

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
}