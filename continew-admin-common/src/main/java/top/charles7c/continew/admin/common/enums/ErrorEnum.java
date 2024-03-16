package top.charles7c.continew.admin.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ErrorEnum {
    CONVERT_OBJECT("对象转换错误", 10001);

    // 成员变量
    private final String msg;
    private final Integer code;

    public static ErrorEnum getInstance(Integer code) {
        return Arrays.stream(values()).filter(e -> e.code.equals(code)).findFirst().orElse(null);
    }
}
