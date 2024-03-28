package com.dogfood.codegen.processor.updater;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;

import com.dogfood.codegen.processor.BaseCodeGenProcessor;
import com.dogfood.codegen.spi.CodeGenProcessor;
import com.google.auto.service.AutoService;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import com.squareup.javapoet.TypeSpec.Builder;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * Updater 代码生成器
 * @author zhongfupeng
 */
@AutoService(CodeGenProcessor.class)
public class UpdaterCodeGenProcessor extends BaseCodeGenProcessor {

    public static final String SUFFIX = "Updater";

    @Override
    public Class<? extends Annotation> getAnnotation() {
        return GenUpdater.class;
    }

    @Override
    public String generatePackage(TypeElement typeElement) {
        return typeElement.getAnnotation(GenUpdater.class).pkgName();
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
            ve -> Objects.isNull(ve.getAnnotation(IgnoreUpdater.class)));

        // 添加getter/setter
        this.addSetterAndGetterMethod(
            baseTypeBuilder,
            fields);

        // 添加get/setId()
        baseTypeBuilder.addField(FieldSpec.builder(Long.class, "id", Modifier.PRIVATE).build());
        baseTypeBuilder.addMethod(
            MethodSpec.methodBuilder("getId").returns(Long.class).addModifiers(Modifier.PUBLIC)
                .addStatement("return this.id")
                .build());
        baseTypeBuilder.addMethod(
            MethodSpec.methodBuilder("setId").returns(void.class).addModifiers(Modifier.PUBLIC)
                .addParameter(Long.class, "id")
                .addStatement("this.id = id")
                .build());

        // 添加update方法
        CodeBlock.Builder codeBlockBuilder = CodeBlock.builder();
        for (VariableElement field : fields) {
            codeBlockBuilder.addStatement("$T.ofNullable($L()).ifPresent(v -> param.$L(v))",
                Optional.class,
                getGetterName(field),
                getSetterName(field));
        }

        MethodSpec updateMethod = MethodSpec.methodBuilder("update" + typeElement.getSimpleName())
            .returns(void.class)
            .addModifiers(Modifier.PUBLIC)
            .addParameter(TypeName.get(typeElement.asType()), "param")
            .addCode(codeBlockBuilder.build())
            .build();
        baseTypeBuilder.addMethod(updateMethod);

        String packageName = this.generatePackage(typeElement);
        this.genJavaFile(packageName, baseTypeBuilder);
        this.genJavaFile(packageName, getSourceTypeFromSupperClass(sourceClassName, packageName, baseClassName));
    }
}
