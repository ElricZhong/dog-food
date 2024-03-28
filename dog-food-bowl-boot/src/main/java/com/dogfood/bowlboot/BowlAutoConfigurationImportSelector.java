package com.dogfood.bowlboot;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

/**
 * @author zhongfupeng
 */
public class BowlAutoConfigurationImportSelector implements DeferredImportSelector {

    public static final String FACTORIES_RESOURCE_LOCATION = "META-INF/spring.factories";

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        List<String> list = new ArrayList<>();

        try {
            // 扫描META-INF/spring.factories下定义的自动配置类
            Enumeration<URL> urls = BowlAutoConfigurationImportSelector.class.getClassLoader().getResources(FACTORIES_RESOURCE_LOCATION);
            while (urls.hasMoreElements()) {
                URL url = urls.nextElement();
                UrlResource resource = new UrlResource(url);
                Properties properties = PropertiesLoaderUtils.loadProperties(resource);
                for (Map.Entry<?, ?> entry : properties.entrySet()) {
                    String factoryTypeName = ((String) entry.getKey()).trim();
                    String[] factoryImplementationNames =
                        StringUtils.commaDelimitedListToStringArray((String) entry.getValue());

                    // 根据org.springframework.boot.autoconfigure来获取所有类，返回
                    // 其他自动配置类同理，根据不同key来获取类
                    if (factoryTypeName.equals("org.springframework.boot.autoconfigure")) {
                        for (String factoryImplementationName : factoryImplementationNames) {
                            list.add(factoryImplementationName.trim());
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list.toArray(new String[0]);
    }
}
