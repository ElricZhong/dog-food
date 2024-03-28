package com.dogfood.codegen.processor.creator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Creator 生成器注解
 * @author zhongfupeng
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.SOURCE)
public @interface GenCreator {
    String pkgName();

    String sourcePath() default "src/main/java";

    boolean overrideSource() default false;
}
