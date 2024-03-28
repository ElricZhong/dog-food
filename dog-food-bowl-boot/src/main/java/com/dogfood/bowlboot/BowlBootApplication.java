package com.dogfood.bowlboot;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

/**
 * @author zhongfupeng
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ComponentScan
//@Import(WebServerAutoConfiguration.class)
@Import(BowlAutoConfigurationImportSelector.class)
public @interface BowlBootApplication {
}
