package com.dogfood.demo.apt.example;

import com.dogfood.demo.apt.processor.HelloWorldAnno;

/**
 * @author zhongfupeng
 */
@HelloWorldAnno(info = "类名：com.dogfood.apt.demo.example.DogFood")
public class DogFood {
    @HelloWorldAnno(info = "属性：name")
    private String name;
}
