package top.charles7c.continew.admin.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

/**
 * Created by WeiRan on 2020/12/30 15:09
 */
@Getter
@AllArgsConstructor
public enum MsgType {
    TEXT("文本消息", 0), IMAGE("图片消息", 1),
    VOICE("语音文件", 2), VIDEO("视频文件", 3),
    ;
    // 成员变量
    private final String name;
    private final Integer code;

    public static MsgType getInstance(Integer code) {
        return Arrays.stream(values()).filter(e -> e.code.equals(code)).findFirst().orElse(null);
    }

}
