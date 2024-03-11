package top.charles7c.continew.admin.webapi.ai;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import top.charles7c.continew.admin.front.context.ChatContext;
import top.charles7c.continew.admin.front.model.validate.ChatMessageRequestValidate;

/**
 * Created by WeiRan on  2023.09.08 11:36
 */

@Tag(name = "chat对话")
@RestController
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatController {
    private final ChatContext chatContext;


    @Operation(summary = "chat对话", description = "chat对话")
    @PostMapping(value = "/v1/completions")
    public SseEmitter completions(@RequestBody ChatMessageRequestValidate createValidate) {
        return chatContext.handlerInstance(createValidate.getModel()).aiApi(createValidate);
    }


}
