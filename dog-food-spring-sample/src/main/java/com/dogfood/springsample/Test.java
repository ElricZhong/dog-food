package com.dogfood.springsample;

import com.dogfood.springsample.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author zhongfupeng
 */
public class Test {

    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = (UserService) applicationContext.getBean("userService");

        System.out.println(applicationContext.getBean("orderService"));
        System.out.println(applicationContext.getBean("orderService1"));
        System.out.println(applicationContext.getBean("orderService2"));

        userService.test();
    }
}
