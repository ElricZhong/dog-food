package com.dogfood.springsample.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * @author zhongfupeng
 */
@Aspect
@Component
public class SampleAspect {

    @Before("execution(public void com.dogfood.springsample.service.UserService.test())")
    public void before(JoinPoint joinPoint) {
        System.out.println("sample aop before");
    }
}
