package com.dogfood.bowlboot;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author zhongfupeng
 */
public class TomcatCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        try {
            context.getClassLoader().loadClass("org.apache.catalina.startup.Tomcat");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
