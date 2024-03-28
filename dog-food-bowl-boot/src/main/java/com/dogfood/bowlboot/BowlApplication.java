package com.dogfood.bowlboot;

import java.util.Map;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

/**
 * @author zhongfupeng
 */
public class BowlApplication {
    public static void run(Class<?> clazz, String[] args) {
        // 创建Spring容器
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(clazz);

        applicationContext.refresh();

        // 启动Web服务器
        WebServer webServer = getWebServer(applicationContext);
        webServer.start();
    }

    private static WebServer getWebServer(WebApplicationContext applicationContext) {
        Map<String, WebServer> beansOfType = applicationContext.getBeansOfType(WebServer.class);
        if (beansOfType.isEmpty()) {
            throw new NullPointerException();
        }

        if (beansOfType.size() > 1) {
            throw new IllegalStateException();
        }

        return beansOfType.values().stream().findFirst().get();
    }
}
