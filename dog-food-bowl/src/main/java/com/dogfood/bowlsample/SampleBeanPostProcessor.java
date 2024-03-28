package com.dogfood.bowlsample;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import com.dogfood.bowl.BeanPostProcessor;
import com.dogfood.bowl.Component;

/**
 * @author zhongfupeng
 */
@Component
public class SampleBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(String beanName, Object bean) {
        if (beanName.equals("userService")) {
            System.out.println("===对userService执行SampleBeanPostProcessor初始化前的后置处理");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(String beanName, Object bean) {
        if (beanName.equals("userService")) {
            System.out.println("===对userService执行SampleBeanPostProcessor初始化后的后置处理");

            Object proxyInstance = Proxy.newProxyInstance(
                SampleBeanPostProcessor.class.getClassLoader(),
                bean.getClass().getInterfaces(),
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        System.out.println("===执行切面逻辑===");
                        return method.invoke(bean, args);
                    }
                });

            return proxyInstance;
        }

        return bean;
    }
}
