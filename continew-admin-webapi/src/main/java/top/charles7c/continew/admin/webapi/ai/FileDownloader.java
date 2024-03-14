package top.charles7c.continew.admin.webapi.ai;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Slf4j
public class FileDownloader {
    /**
     * 根据地址获得数据的输入流
     *
     * @param strUrl 网络连接地址
     * @return url的输入流
     */
//    public static InputStream getInputStreamByUrl(String strUrl) {
//        HttpURLConnection conn = null;
//        try {
//            URL url = new URL(strUrl);
//            conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.setConnectTimeout(20 * 1000);
//            final ByteArrayOutputStream output = new ByteArrayOutputStream();
//            IOUtils.copy(conn.getInputStream(), output);
//            return new ByteArrayInputStream(output.toByteArray());
//        } catch (Exception e) {
//            log.error("根据地址获得数据的输入流异常 Exception，", e);
//        } finally {
//            try {
//                if (conn != null) {
//                    conn.disconnect();
//                }
//            } catch (Exception e) {
//                log.error("断开输入流异常 Exception，", e);
//            }
//        }
//        return null;
//    }
//
//    public static void main(String[] args) {
//        InputStream stream =getInputStreamByUrl("");
//        if (!ObjectUtils.isEmpty(stream)) {
//            MultipartFile file = new MockMultipartFile(req.getFileName(), req.getFileName(),"", stream);
//        }
//    }
}

