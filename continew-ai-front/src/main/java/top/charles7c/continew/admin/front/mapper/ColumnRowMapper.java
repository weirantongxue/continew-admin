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

import org.apache.ibatis.annotations.Select;
import top.charles7c.continew.admin.front.model.entity.ColumnRowDO;
import top.charles7c.continew.starter.data.mybatis.plus.base.BaseMapper;

/**
 * 分镜列行 Mapper
 *
 * @author weiran
 * @since 2024/03/20 18:23
 */
public interface ColumnRowMapper extends BaseMapper<ColumnRowDO> {
    @Select("SELECT COALESCE(MAX(sort), 0) FROM lb_column_row WHERE project_id = #{projectId}")
    Integer sortMax(long projectId);
}