package top.charles7c.continew.admin.front.service;

import top.charles7c.continew.admin.front.model.validate.ChatMessageRequestValidate;

/**
 * Created by WeiRan on  2024.03.13 17:11
 */
public interface ChatGlmService {
    void aiApi(ChatMessageRequestValidate messageCreateValidate,String sessionId);
}
