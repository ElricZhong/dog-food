package com.dogfood.bowlsample;

import com.dogfood.bowl.BowlApplicationContext;

/**
 * @author zhongfupeng
 */
public class Test {

    public static void main(String[] args) {
        BowlApplicationContext applicationContext = new BowlApplicationContext(AppConfig.class);

        UserInterface userService = (UserInterface) applicationContext.getBean("userService");

        userService.test();
    }
}
