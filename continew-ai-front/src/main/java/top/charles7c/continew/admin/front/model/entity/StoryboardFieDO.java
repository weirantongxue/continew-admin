package top.charles7c.continew.admin.front.model.entity;

import java.io.Serial;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableName;

import top.charles7c.continew.starter.extension.crud.model.entity.BaseDO;

/**
 * 分镜名实体
 *
 * @author weiran
 * @since 2024/03/26 20:09
 */
@Data
@TableName("lb_storyboard_fie")
public class StoryboardFieDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 字段
     */
    private String fieId;

    /**
     * 名称
     */
    private String name;

    /**
     * 项目id
     */
    private Long projectId;

    /**
     * 状态（1：启用；2：禁用）
     */
    private Integer status;

    /**
     * 是否删除: [0=否, 1=是]
     */
    private Integer isDeleted;
}