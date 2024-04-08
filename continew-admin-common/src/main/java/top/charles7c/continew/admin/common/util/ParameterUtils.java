package top.charles7c.continew.admin.common.util;

import cn.hutool.core.date.DateUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by WeiRan on  2024.04.08 20:04
 */
public class ParameterUtils {
    public static  Map<String, Object> categoryListAssembly() {
        Map<String, Object> map = new HashMap<>();
        String timestamp = String.valueOf(DateUtil.current());
        map.put("partner_id", 37441272);
        map.put("timestamp", timestamp);
        map.put("sign", SignGeneratorUtils.getBaiJaYunSign(map));
        return map;
    }


    public static Map<String, Object> categoryVideoAssembly(String fileId, int page) {
        Map<String, Object> map = new HashMap<>();
        String timestamp = String.valueOf(DateUtil.current());
        map.put("partner_id", 37441272);
        map.put("category_id",fileId);
        map.put("page", page);
        map.put("page_size", 20);
        map.put("timestamp", timestamp);
        //获取签名
        map.put("sign",  SignGeneratorUtils.getBaiJaYunSign(map));
        return map;
    }
}
