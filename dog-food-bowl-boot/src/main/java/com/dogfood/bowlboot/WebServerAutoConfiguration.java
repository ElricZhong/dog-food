package com.dogfood.bowlboot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhongfupeng
 */
@Configuration
public class WebServerAutoConfiguration {

    @Bean
    //@Conditional(JettyCondition.class)
    @BowlConditionalOnClass("org.eclipse.jetty.server.Server")
    public JettyWebServer jettyWebServer() {
        return new JettyWebServer();
    }

    @Bean
    //@Conditional(TomcatCondition.class)
    @BowlConditionalOnClass("org.apache.catalina.startup.Tomcat")
    public TomcatWebServer tomcatWebServer() {
        return new TomcatWebServer();
    }
}
