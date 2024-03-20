package top.charles7c.continew.admin.front.model.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.charles7c.continew.admin.front.model.entity.ColumnContentDO;
import top.charles7c.continew.admin.front.model.entity.ColumnRowDO;

import java.io.Serializable;
import java.util.List;

@Data
public class ColumnsRowResp implements Serializable {
    @Schema(description = "行信息")
    private ColumnRowDO columnRow;
    @Schema(description = "行内容")
    private List<ColumnContentDO> columnContentList;
}
