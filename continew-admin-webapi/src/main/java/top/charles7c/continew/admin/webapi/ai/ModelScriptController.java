package top.charles7c.continew.admin.webapi.ai;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.text.CharSequenceUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import net.dreamlu.mica.core.result.R;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.charles7c.continew.admin.front.model.query.ModelScriptQuery;
import top.charles7c.continew.admin.front.model.req.ModelScriptReq;
import top.charles7c.continew.admin.front.model.resp.ColumnsTableResp;
import top.charles7c.continew.admin.front.model.resp.ModelScriptDetailResp;
import top.charles7c.continew.admin.front.model.resp.ModelScriptResp;
import top.charles7c.continew.admin.front.service.ModelScriptService;
import top.charles7c.continew.starter.extension.crud.annotation.CrudRequestMapping;
import top.charles7c.continew.starter.extension.crud.controller.BaseController;
import top.charles7c.continew.starter.extension.crud.enums.Api;

/**
 * 模型预设脚本管理 API
 *
 * @author weiran
 * @since 2024/03/21 14:48
 */
@Tag(name = "模型预设脚本管理 API")
@RestController
@CrudRequestMapping(value = "/ai/modelScript", api = {Api.PAGE, Api.GET, Api.ADD, Api.UPDATE, Api.DELETE, Api.EXPORT})
public class ModelScriptController extends BaseController<ModelScriptService, ModelScriptResp, ModelScriptDetailResp, ModelScriptQuery, ModelScriptReq> {


    @Operation(summary = "查询脚本列表", description = "查询脚本列表")
    @GetMapping("/selectModelScript")
    public R<Object> selectTable() {
         this.baseService.selectModelScript();
        return R.success();
    }

}