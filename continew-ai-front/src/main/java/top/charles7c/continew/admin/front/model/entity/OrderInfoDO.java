/*
 * Copyright (c) 2022-present Charles7c Authors. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package top.charles7c.continew.admin.front.model.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import top.charles7c.continew.starter.extension.crud.model.entity.BaseDO;

import java.io.Serial;

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
     * 订单金额(元)
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