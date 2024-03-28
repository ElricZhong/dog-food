package com.dogfood.codegen.processor;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.util.ElementFilter;
import javax.tools.Diagnostic.Kind;

import com.dogfood.codegen.context.ProcessingEnvironmentHolder;
import com.dogfood.codegen.registry.CodeGenProcessorRegistry;
import com.dogfood.codegen.spi.CodeGenProcessor;
import com.google.auto.service.AutoService;

/**
 * @author zhongfupeng
 */
@AutoService(Processor.class)
public class DogFoodCodeGenProcessor extends AbstractProcessor {

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        ProcessingEnvironmentHolder.setEnvironment(processingEnv);
        CodeGenProcessorRegistry.initProcessors();
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        for (TypeElement annotation : annotations) {
            Set<? extends Element> elements = roundEnv.getElementsAnnotatedWith(annotation);
            Set<TypeElement> typeElements = ElementFilter.typesIn(elements);

            for (TypeElement typeElement : typeElements) {
                try {
                    CodeGenProcessor codeGenProcessor = CodeGenProcessorRegistry.find(annotation.getQualifiedName().toString());
                    codeGenProcessor.generate(typeElement, roundEnv);
                } catch (Exception e) {
                    ProcessingEnvironmentHolder.getEnvironment().getMessager()
                        .printMessage(Kind.ERROR,"代码生成异常: " + e.getMessage());
                }
            }
        }

        return false;
    }

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return CodeGenProcessorRegistry.getSupportAnnotations();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }
}
