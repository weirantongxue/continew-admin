package top.charles7c.continew.admin.front.listener;

import cn.hutool.core.date.TimeInterval;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unfbx.chatgpt.entity.chat.ChatCompletionResponse;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import top.charles7c.continew.admin.front.constant.TimerConstant;
import top.charles7c.continew.admin.front.enums.EventNameType;
import top.charles7c.continew.admin.front.model.entity.ChatMessageDO;
import top.charles7c.continew.admin.front.service.ChatMessageService;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by WeiRan on 2023.09.15 12:08
 */
@Slf4j
@Data
public class GPTEventSourceListener extends EventSourceListener {

    //private CountDownLatch countDownLatch = new CountDownLatch(1);

    private final SseEmitter sseEmitter;
    private final String messageId;
    private final ChatMessageService chatMessageService;

    private final ChatMessageDO message;

    private final TimeInterval timer;

    private String last = "";

    public GPTEventSourceListener(SseEmitter sseEmitter, String messageId, ChatMessageService chatMessageService, ChatMessageDO message, TimeInterval timer) {
        this.sseEmitter = sseEmitter;
        this.messageId = messageId;
        this.chatMessageService = chatMessageService;
        this.message = message;
        this.timer = timer;
    }

    /**
     * 建立连接
     *
     * @param eventSource
     * @param response
     */

    @Override
    public void onOpen(EventSource eventSource, Response response) {
        timer.start(TimerConstant.CHAT_RESPONSE_TIME);
        log.info("建立sse连接...");
    }

    /**
     * 接收事件
     *
     * @param eventSource
     * @param id
     * @param type
     * @param data
     */
    @SneakyThrows
    @Override
    public void onEvent(EventSource eventSource, String id, String type, String data) {
        log.info("收到消息:" + data);
        if (data.equals("[DONE]")) {
//            chatMessageService.insertMessage(ChatMessageUtils.setMessageDO(message, last, timer
//                .intervalMs(TimerConstant.RESPONSE_TIME), timer.intervalMs(TimerConstant.CHAT_RESPONSE_TIME)));
            sseEmitter.send(SseEmitter.event()
                    .id(messageId)
                    .name(EventNameType.FINISH.getCode())
                    .data(EventNameType.DONE.getCode())
                    .reconnectTime(3000));
            sseEmitter.complete();
            return;
        }
        ObjectMapper mapper = new ObjectMapper();
        ChatCompletionResponse completionResponse = mapper.readValue(data, ChatCompletionResponse.class); // 读取Json
        String content = completionResponse.getChoices().get(0).getDelta().getContent();
        if (content != null) {
            if (null != completionResponse.getUsage()) {
                message.setTotalTokens(completionResponse.getUsage().getTotalTokens());
                message.setPromptTokens(completionResponse.getUsage().getPromptTokens());
                message.setCompletionTokens(completionResponse.getUsage().getCompletionTokens());
                message.setTaskId(completionResponse.getId());
            }

            last = last + content;
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("content", content);
            sseEmitter.send(SseEmitter.event()
                .id(messageId)
                .name(EventNameType.ADD.getCode())
                .data(jsonObject)
                .reconnectTime(3000));
        }
        System.out.println("------开始发送消息到前端------{}" + messageId);
        System.out.println("------开始发送消息到前端------{}" + completionResponse.getChoices().get(0).getDelta());
    }

    /**
     * 关闭连接
     *
     * @param eventSource
     */
    @Override
    public void onClosed(EventSource eventSource) {
        log.info("sse连接关闭messageId:{}", messageId);
        //countDownLatch.countDown();
        sseEmitter.complete();
    }

    @SneakyThrows
    @Override
    public void onFailure(EventSource eventSource, Throwable t, Response response) {
        if (Objects.isNull(response)) {
            return;
        }
        ResponseBody body = response.body();
        if (Objects.nonNull(body)) {
            log.error("sse连接异常data：{}，异常：{}", body.string(), t);
        } else {
            log.error("sse连接异常data：{}，异常：{}", response, t);
        }
        //countDownLatch.countDown();
        eventSource.cancel();
        sseEmitter.complete();
    }
}
