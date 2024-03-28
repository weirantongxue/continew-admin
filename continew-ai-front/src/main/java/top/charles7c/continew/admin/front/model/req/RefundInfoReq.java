package top.charles7c.continew.admin.front.model.req;

import java.io.Serial;
import java.time.LocalDateTime;


import lombok.Data;

import io.swagger.v3.oas.annotations.media.Schema;

import org.hibernate.validator.constraints.Length;

import top.charles7c.continew.starter.extension.crud.model.req.BaseReq;

/**
 * 创建或修改退款信息信息
 *
 * @author weiran
 * @since 2024/03/28 17:25
 */
@Data
@Schema(description = "创建或修改退款信息信息")
public class RefundInfoReq extends BaseReq {

    @Serial
    private static final long serialVersionUID = 1L;
}