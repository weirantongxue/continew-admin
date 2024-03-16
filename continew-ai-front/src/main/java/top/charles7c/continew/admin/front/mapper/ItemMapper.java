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

package top.charles7c.continew.admin.front.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import top.charles7c.continew.admin.common.config.mybatis.DataPermissionMapper;
import top.charles7c.continew.admin.front.model.entity.ItemDO;

/**
 * AI会话 Mapper
 *
 * @author weiran
 * @since 2024/03/11 18:36
 */
public interface ItemMapper extends DataPermissionMapper<ItemDO> {

    @Select("UPDATE lb_item set number=number+1,update_time = CURRENT_TIMESTAMP,update_user = #{userId} WHERE id=#{id}")
    void numberAdd(@Param("id") String id, @Param("userId") long userId);
}