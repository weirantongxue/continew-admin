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
 * 产品实体
 *
 * @author weiran
 * @since 2024/03/25 23:36
 */
@Data
@TableName("lb_product")
public class ProductDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商品名称
     */
    private String title;

    /**
     * 价格（元）
     */
    private Integer price;

    /**
     * token数量
     */
    private Integer tokenPrice;

    /**
     * 排序值
     */
    private Integer sort;

}