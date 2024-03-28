package com.dogfood.codegen.processor.creator;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Objects;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

import com.dogfood.codegen.processor.BaseCodeGenProcessor;
import com.dogfood.codegen.spi.CodeGenProcessor;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Creator 代码生成器
 * @author zhongfupeng
 */
@AutoService(CodeGenProcessor.class)
public class CreatorCodeGenProcessor extends BaseCodeGenProcessor {

    public static final String SUFFIX = "Creator";

    @Override
    public Class<? extends Annotation> getAnnotation() {
        return GenCreator.class;
    }

    @Override
    public String generatePackage(TypeElement typeElement) {
        return typeElement.getAnnotation(GenCreator.class).pkgName();
    }

    @Override
    protected void generateClass(TypeElement typeElement, RoundEnvironment roundEnv) throws Exception {
        String sourceClassName = typeElement.getSimpleName() + SUFFIX;
        String baseClassName = PREFIX + sourceClassName;

        Builder baseTypeBuilder = TypeSpec.classBuilder(baseClassName)
            .addModifiers(Modifier.PUBLIC)
            .addAnnotation(Schema.class)
            .addAnnotation(Data.class);

        List<VariableElement> fields = this.findFields(
            typeElement,
            ve -> Objects.isNull(ve.getAnnotation(IgnoreCreator.class)));

        // 添加getter/setter
        this.addSetterAndGetterMethod(
            baseTypeBuilder,
            fields);

        String packageName = this.generatePackage(typeElement);
        this.genJavaFile(packageName, baseTypeBuilder);
        this.genJavaFile(packageName, getSourceTypeFromSupperClass(sourceClassName, packageName, baseClassName));
    }
}
