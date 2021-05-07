package com.alipay.demo.config;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @programe: alipay
 * @Description: 监听器—加载配置文件
 * @Author: li shuai ming
 * @Create: 2021/5/7-9:49
 */
@Component
public class PropertiesListener implements ApplicationListener<ApplicationStartedEvent> {

    @Override
    public void onApplicationEvent(ApplicationStartedEvent applicationStartedEvent) {
        AlipayProperties.loadProperties();
    }
}
