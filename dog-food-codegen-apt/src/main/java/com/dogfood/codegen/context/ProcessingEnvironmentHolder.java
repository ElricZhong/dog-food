package com.dogfood.codegen.context;

import javax.annotation.processing.ProcessingEnvironment;

/**
 * @author zhongfupeng
 */
public class ProcessingEnvironmentHolder {
    private static final ThreadLocal<ProcessingEnvironment> environment = new ThreadLocal<>();

    public static void setEnvironment(ProcessingEnvironment pe) {
        environment.set(pe);
    }

    public static ProcessingEnvironment getEnvironment() {
        return environment.get();
    }
}
