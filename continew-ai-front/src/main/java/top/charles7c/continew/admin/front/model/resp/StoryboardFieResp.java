package top.charles7c.continew.admin.front.model.resp;

import java.io.Serial;
import java.time.LocalDateTime;

import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import top.charles7c.continew.starter.extension.crud.model.resp.BaseResp;

/**
 * 分镜名信息
 *
 * @author weiran
 * @since 2024/03/26 20:25
 */
@Data
@Schema(description = "分镜名信息")
public class StoryboardFieResp extends BaseResp {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 字段
     */
    @Schema(description = "字段")
    private String fieId;

    /**
     * 名称
     */
    @Schema(description = "名称")
    private String name;

    /**
     * 项目id
     */
    @Schema(description = "项目id")
    private Long projectId;

    /**
     * 状态（1：启用；2：禁用）
     */
    @Schema(description = "状态（1：启用；2：禁用）")
    private Integer status;

}