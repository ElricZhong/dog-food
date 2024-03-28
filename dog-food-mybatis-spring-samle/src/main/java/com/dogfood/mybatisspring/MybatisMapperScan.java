package com.dogfood.mybatisspring;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.Import;

/**
 * @author zhongfupeng
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(MybatisImportBeanDefinitionRegistrar.class)
public @interface MybatisMapperScan {
    String value() default "";
}
