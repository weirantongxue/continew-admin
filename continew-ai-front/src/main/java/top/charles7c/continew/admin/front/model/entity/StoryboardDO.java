package top.charles7c.continew.admin.front.model.entity;

import java.io.Serial;

import lombok.Data;

import com.baomidou.mybatisplus.annotation.TableName;

import top.charles7c.continew.starter.extension.crud.model.entity.BaseDO;

/**
 * 分镜列实体
 *
 * @author weiran
 * @since 2024/03/26 20:11
 */
@Data
@TableName("lb_storyboard")
public class StoryboardDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 镜号
     */
    private Integer shot;

    /**
     * 场号
     */
    private Integer sceneNumber;

    /**
     * 镜号
     */
    private Integer shotNumber;

    /**
     * 画面
     */
    private String picture;

    /**
     * 参考
     */
    private String referencePicture;

    /**
     * 景别
     */
    private String shotSize;

    /**
     * 时长（秒）
     */
    private Integer duration;

    /**
     * 内容
     */
    private String content;

    /**
     * 备注
     */
    private String remark;

    /**
     * 场景
     */
    private String sceneAdvice;

    /**
     * 声音
     */
    private String voice;

    /**
     * 摄影机角度
     */
    private String cameraAngle;

    /**
     * 运镜
     */
    private String cameraMove;

    /**
     * 摄影机装备
     */
    private String cameraEquipment;

    /**
     * 镜头焦段
     */
    private String lens;

    /**
     * 项目id
     */
    private Long projectId;

    /**
     * 是否删除: [0=否, 1=是]
     */
    private Integer isDeleted;
}