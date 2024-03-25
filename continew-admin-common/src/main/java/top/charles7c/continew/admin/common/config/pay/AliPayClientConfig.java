package top.charles7c.continew.admin.common.config.pay;

import com.alipay.api.*;
import jakarta.annotation.Resource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@ConfigurationProperties(prefix = "alipay")
public class AliPayClientConfig {

    /**
     * 利用Environment对象获取配置文件alipay-sandbox.properties文件中的所有内容
     */
    @Resource
    private Environment config;


    /**
     * 创建一个获取AlipayClient对象的方法，用于封装签名的自动实现
     * @return AlipayClient
     */
    @Bean
    public AlipayClient getAlipayClient() throws AlipayApiException {
        //创建alipay配置对象，并设置相应的参数
        AlipayConfig alipayConfig = new AlipayConfig();
        //设置网关地址
        alipayConfig.setServerUrl(config.getProperty("alipay.gateway-url"));
        //设置应用Id
        alipayConfig.setAppId(config.getProperty("alipay.app-id"));
        //设置应用私钥
        alipayConfig.setPrivateKey(config.getProperty("alipay.merchant-private-key"));
        //设置请求格式，固定值json
        alipayConfig.setFormat(AlipayConstants.FORMAT_JSON);
        //设置字符集
        alipayConfig.setCharset(AlipayConstants.CHARSET_UTF8);
        //设置支付宝公钥
        alipayConfig.setAlipayPublicKey(config.getProperty("alipay.alipay-public-key"));
        //设置签名类型
        alipayConfig.setSignType(AlipayConstants.SIGN_TYPE_RSA2);
        //构造client
        AlipayClient alipayClient = new DefaultAlipayClient(alipayConfig);

        return alipayClient;
    }

}
