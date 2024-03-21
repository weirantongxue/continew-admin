package top.charles7c.continew.admin.front.model.entity;

import java.io.Serial;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableName;

import top.charles7c.continew.starter.extension.crud.model.entity.BaseDO;

/**
 * 模型预设脚本实体
 *
 * @author weiran
 * @since 2024/03/21 14:48
 */
@Data
@TableName("lb_model_script")
public class ModelScriptDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 脚本名称
     */
    private String name;

    /**
     * 模型名称
     */
    private Integer modelId;

    /**
     * 预设内容
     */
    private String prompt;

    /**
     * 封面
     */
    private String coverUrl;

    /**
     * 状态（1：启用；2：禁用）
     */
    private Integer status;

    /**
     * 是否删除: 0=否, 1=是
     */
    private Integer isDelete;
}