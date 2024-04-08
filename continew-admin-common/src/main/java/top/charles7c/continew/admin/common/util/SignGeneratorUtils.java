package top.charles7c.continew.admin.common.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by WeiRan on  2024.04.08 16:44
 */
public class SignGeneratorUtils {

    /**
     * 生成签名参数
     *
     * @param params     请求的参数
     * @return 生成的签名
     */
    public static String getBaiJaYunSign(Map<String, Object> params) {
        String partnerKey="nemuaKkfBUdJfzTV2kBHKe0/896F5Ijoevl97+3BFIKMKyEQcAvj3w0STXJnonP95N/kKG15+gvddeK7zWYL4NwJelQI";
        // 将参数按key进行排序
        Map<String, Object> sortedParams = new TreeMap<>(params);

        StringBuilder strBuilder = new StringBuilder();
        for (Map.Entry<String, Object> entry : sortedParams.entrySet()) {
            strBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        strBuilder.append("partner_key=").append(partnerKey); // 结尾再拼上 partner_key=partnerKey

        String sign = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(strBuilder.toString().getBytes("UTF-8"));
            StringBuilder result = new StringBuilder();
            for (byte b : bytes) {
                result.append(String.format("%02x", b));
            }
            sign = result.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return sign;
    }

}
