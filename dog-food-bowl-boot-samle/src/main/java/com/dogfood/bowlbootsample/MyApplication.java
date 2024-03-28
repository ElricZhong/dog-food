package com.dogfood.bowlbootsample;

import com.dogfood.bowlboot.BowlApplication;
import com.dogfood.bowlboot.BowlBootApplication;

/**
 * @author zhongfupeng
 */
@BowlBootApplication
public class MyApplication {

    public static void main(String[] args) {
        BowlApplication.run(MyApplication.class, args);
    }
}
