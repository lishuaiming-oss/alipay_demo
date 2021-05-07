package com.alipay.demo.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 *  读取yml配置文件
 * @programe: alipay
 * @Description:
 * @Author: li shuai ming
 * @Create: 2021/4/29-15:27
 */

@Component
public class AlipayProperties {

    public static final String APP_ID = "app_id";
    public static final String PRIVARY_KEY = "merchant_private_key";
    public static final String PUBLIC_KEY = "alipay_public_key";
    public static final String NOTIFY_URL = "notify_url";
    public static final String RETURN_URL = "return_url";
    public static final String SIGN_TYPE = "sign_type";
    public static final String CHARSET = "charset";
    public static final String GATEWAY_URL = "gatewayUrl";
    public static final String LOG_PATH = "log_path";

    /**
     * 保存加载配置参数
     */
    private static Map<String, String> propertiesMap = new HashMap<String, String>();

    /**
     * 加载属性
     */
    public static void loadProperties() {
        // 获得PathMatchingResourcePatternResolver对象
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            // 加载resource文件(也可以加载resources)
            Resource resources = resolver.getResource("classpath:application.yml");
            PropertiesFactoryBean config = new PropertiesFactoryBean();
            config.setLocation(resources);
            config.afterPropertiesSet();
            Properties prop = config.getObject();
            // 循环遍历所有得键值对并且存入集合
            for (String key : prop.stringPropertyNames()) {
                propertiesMap.put(key, (String) prop.get(key));
            }
        } catch (Exception e) {
            new Exception("配置文件加载失败");
        }
    }

    /**
     * 获取配置参数值
     * @param key
     * @return
     */
    public static String getKey(String key) {
        return propertiesMap.get(key);
    }

    public static String getAppId() {
        return propertiesMap.get(APP_ID);
    }

    public static String getPrivateKey() {
        return propertiesMap.get(PRIVARY_KEY);
    }

    public static String getPublicKey() {
        return propertiesMap.get(PUBLIC_KEY);
    }

    public static String getNotifyUrl() {
        return propertiesMap.get(NOTIFY_URL);
    }

    public static String getReturnUrl() {
        return propertiesMap.get(RETURN_URL);
    }

    public static String getSignType() {
        return propertiesMap.get(SIGN_TYPE);
    }

    public static String getCharset() {
        return propertiesMap.get(CHARSET);
    }

    public static String getGatewayUrl() {
        return propertiesMap.get(GATEWAY_URL);
    }

    public static String getLogPath() {
        return propertiesMap.get(LOG_PATH);
    }
}


