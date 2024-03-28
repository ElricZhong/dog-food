package com.dogfood.demo.apt.processor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author zhongfupeng
 */
@Retention(RetentionPolicy.SOURCE)
public @interface HelloWorldAnno {
    String info();
}
