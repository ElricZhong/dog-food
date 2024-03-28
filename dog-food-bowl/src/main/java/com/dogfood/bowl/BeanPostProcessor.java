package com.dogfood.bowl;

/**
 * @author zhongfupeng
 */
public interface BeanPostProcessor {
    Object postProcessBeforeInitialization(String beanName, Object bean);
    Object postProcessAfterInitialization(String beanName, Object bean);
}
