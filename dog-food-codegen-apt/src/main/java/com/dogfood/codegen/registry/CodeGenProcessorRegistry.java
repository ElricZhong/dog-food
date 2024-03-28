package com.dogfood.codegen.registry;

import java.util.HashMap;
import java.util.Map;
import java.util.ServiceLoader;
import java.util.Set;

import com.dogfood.codegen.spi.CodeGenProcessor;

/**
 * 通过SPI加载所有的CodeGenProcessor，并根据标记的annotation来查找对应的生成器
 * @author zhongfupeng
 */
public class CodeGenProcessorRegistry {
    private static Map<String, ? extends CodeGenProcessor> PROCESSORS;

    /**
     * 获取所有处理器支持的注解集合
     */
    public static Set<String> getSupportAnnotations() {
        return PROCESSORS.keySet();
    }

    /**
     * 查找处理器
     * @param annotationClassName
     * @return
     */
    public static CodeGenProcessor find(String annotationClassName) {
        return PROCESSORS.get(annotationClassName);
    }

    /**
     * 通过SPI加载所有的CodeGenProcessor
     */
    public static void initProcessors() {
        Map<String, CodeGenProcessor> map = new HashMap<>();

        //这里直接使用ServiceLoader.load(CodeGenProcessor.class)会找不到SPI实现，需使用以下方法加载
        ServiceLoader<CodeGenProcessor> serviceLoader = ServiceLoader.load(CodeGenProcessor.class, CodeGenProcessor.class.getClassLoader());
        for (CodeGenProcessor codeGenProcessor : serviceLoader) {
            map.put(codeGenProcessor.getAnnotation().getName(), codeGenProcessor);
        }

        PROCESSORS = map;
    }
}
