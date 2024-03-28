package com.dogfood.demo.apt.processor;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

import com.google.auto.service.AutoService;

/**
 * @author zhongfupeng
 */
@AutoService(Processor.class)
public class HelloWorldProcessor extends AbstractProcessor {

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        roundEnv.getElementsAnnotatedWith(HelloWorldAnno.class).forEach(element -> {
            HelloWorldAnno annotation = element.getAnnotation(HelloWorldAnno.class);
            System.out.println(element.getSimpleName() + ": " + annotation.info());
        });

        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        HashSet<String> types = new HashSet<>();
        types.add(HelloWorldAnno.class.getCanonicalName());
        return types;
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
