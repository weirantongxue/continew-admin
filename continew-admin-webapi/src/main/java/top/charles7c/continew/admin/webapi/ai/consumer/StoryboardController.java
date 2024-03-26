package top.charles7c.continew.admin.webapi.ai.consumer;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.charles7c.continew.admin.front.model.entity.StoryboardDO;
import top.charles7c.continew.admin.front.model.resp.StoryboardResp;
import top.charles7c.continew.admin.front.service.StoryboardService;
import top.charles7c.continew.starter.web.model.R;

/**
 * Created by WeiRan on  2024.03.26 20:14
 */
@Tag(name = "分镜Table")
@RestController
@RequiredArgsConstructor
@RequestMapping("/ai/consumer/storyboards")
@Slf4j
public class StoryboardController {
    private final StoryboardService service;

    @Operation(summary = "列表查询", description = "列表查询")
    @GetMapping("/list")
    public R<Object> list(Long projectId) {
        return R.ok(service.list(projectId));
    }


    @Operation(summary = "新建镜头", description = "新建镜头")
    @GetMapping("/add")
    public R<Object> add(Long projectId) {
        return R.ok(service.add(projectId));
    }

    @Operation(summary = "修改内容", description = "修改内容")
    @GetMapping("/updateTable")
    public R<Object> add(StoryboardResp storyboardResp) {
        return R.ok(service.updateTable(storyboardResp));
    }


}
