package com.dogfood.mybatissample;

import com.dogfood.mybatissample.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhongfupeng
 */
public class Test {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService userService = (UserService) applicationContext.getBean("userService");
        userService.test();
    }
}
