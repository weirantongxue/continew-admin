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

package top.charles7c.continew.admin.system.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import top.charles7c.continew.admin.common.constant.UiConstants;
import top.charles7c.continew.starter.data.mybatis.plus.base.IBaseEnum;

/**
 * 公告状态枚举
 *
 * @author Charles7c
 * @since 2023/8/20 10:55
 */
@Getter
@RequiredArgsConstructor
public enum AnnouncementStatusEnum implements IBaseEnum<Integer> {

    /** 待发布 */
    PENDING_RELEASE(1, "待发布", UiConstants.COLOR_PRIMARY),

    /** 已发布 */
    PUBLISHED(2, "已发布", UiConstants.COLOR_SUCCESS),

    /** 已过期 */
    EXPIRED(3, "已过期", UiConstants.COLOR_ERROR),;

    private final Integer value;
    private final String description;
    private final String color;
}
