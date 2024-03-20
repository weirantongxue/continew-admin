package top.charles7c.continew.admin.front.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.charles7c.continew.admin.front.model.entity.ColumnsDO;

import java.io.Serializable;
import java.util.List;

@Data
public class ColumnsTableResp implements Serializable {
    @Schema(description = "标题集合")
    private List<ColumnsDO> columnsList;
    @Schema(description = "行集合")
    private List<ColumnsRowResp> columnsRowRespList;


}
