package top.charles7c.continew.admin.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * Created by WeiRan on 2020/12/30 15:49
 */
@Getter
@AllArgsConstructor
public enum ChatType {
    UNKNOWN("未知", 0), PUBLIC_CHAT("公聊", 1),
    PRIVATE_CHAT("私聊", 2), SYSTEM("系统消息", 3),
    MODEL("模型消息", 5),
    ;
    // 成员变量
    private final String name;
    private final Integer code;

    public static ChatType getInstance(Integer code) {
        return Arrays.stream(values()).filter(e -> e.code.equals(code)).findFirst().orElse(null);
    }
}
