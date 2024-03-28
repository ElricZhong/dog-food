package com.dogfood.codegen.spi;

import java.lang.annotation.Annotation;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.TypeElement;

/**
 * 代码生成SPI
 * @author zhongfupeng
 */
public interface CodeGenProcessor {
    /**
     * 代码生成实现上对应的注解
     * @return
     */
    Class<? extends Annotation> getAnnotation();

    /**
     * 为类生成包路径
     * @param typeElement
     * @return
     */
    String generatePackage(TypeElement typeElement);

    /**
     * 代码生成逻辑
     * @param typeElement
     * @param roundEnv
     */
    void generate(TypeElement typeElement, RoundEnvironment roundEnv) throws Exception;
}
