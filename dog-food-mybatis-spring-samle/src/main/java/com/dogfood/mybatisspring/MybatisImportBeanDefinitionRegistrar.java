package com.dogfood.mybatisspring;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.classreading.MetadataReader;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.core.type.filter.TypeFilter;

/**
 * @author zhongfupeng
 */
public class MybatisImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // TODO 硬编码方式
        //AbstractBeanDefinition bd1 = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        //bd1.setBeanClass(MybatisMapperFactoryBean.class);
        //bd1.getConstructorArgumentValues().addGenericArgumentValue(UserMapper.class);
        //registry.registerBeanDefinition("userMapper", bd1);
        //
        //AbstractBeanDefinition bd2 = BeanDefinitionBuilder.genericBeanDefinition().getBeanDefinition();
        //bd2.setBeanClass(MybatisMapperFactoryBean.class);
        //bd2.getConstructorArgumentValues().addGenericArgumentValue(OrderMapper.class);
        //registry.registerBeanDefinition("orderMapper", bd2);

        Map<String, Object> annotationAttributes = importingClassMetadata.getAnnotationAttributes(MybatisMapperScan.class.getName());
        String path = (String)annotationAttributes.get("value");

        MybatisMapperScanner mybatisMapperScanner = new MybatisMapperScanner(registry);

        mybatisMapperScanner.addIncludeFilter(new TypeFilter() {
            @Override
            public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory)
                throws IOException {
                return true;
            }
        });

        mybatisMapperScanner.scan(path);
    }
}
