package com.dogfood.bowlboot;

/**
 * @author zhongfupeng
 */
public class JettyWebServer implements WebServer {
    @Override
    public void start() {
        System.out.println("启动Jetty服务器");
    }
}
