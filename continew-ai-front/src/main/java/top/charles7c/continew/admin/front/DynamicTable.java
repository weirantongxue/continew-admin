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

package top.charles7c.continew.admin.front;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DynamicTable {
    private List<String> columnNames;
    private List<Map<String, Object>> rows;

    public DynamicTable() {
        columnNames = new ArrayList<>();
        rows = new ArrayList<>();
    }

    public void addColumn(String columnName) {
        columnNames.add(columnName);
    }

    public void addRow(Map<String, Object> rowData) {
        rows.add(rowData);
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

    public List<Map<String, Object>> getRows() {
        return rows;
    }

    public static void main(String[] args) {
        DynamicTable table = new DynamicTable();

        // 添加列
        table.addColumn("Name");
        table.addColumn("Age");
        table.addColumn("City");
        table.addColumn("City01");

        // 添加行数据
        Map<String, Object> row1 = new HashMap<>();
        row1.put("Name", "Alice");
        row1.put("Age", 30);
        row1.put("City", "New York");
        table.addRow(row1);

        Map<String, Object> row2 = new HashMap<>();
        row2.put("Name", "Bob");
        row2.put("Age", 25);
        row2.put("City", "Los Angeles");
        table.addRow(row2);

        Map<String, Object> row3 = new HashMap<>();
        row3.put("Name", "Bob");
        row3.put("Age", 25);
        row3.put("City", "Los Angeles");
        table.addRow(row3);

        // 打印表格
        System.out.println(table.getColumnNames());
        for (Map<String, Object> row : table.getRows()) {
            for (String columnName : table.getColumnNames()) {
                System.out.print(row.get(columnName) + "\t");
            }
            System.out.println();
        }
    }
}
