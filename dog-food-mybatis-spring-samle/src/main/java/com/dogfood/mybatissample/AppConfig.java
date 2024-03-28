package com.dogfood.mybatissample;

import java.io.IOException;
import java.io.InputStream;

import com.dogfood.mybatisspring.MybatisMapperScan;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author zhongfupeng
 */
@ComponentScan("com.dogfood.mybatissample")
//@Import(MybatisImportBeanDefinitionRegistrar.class)
@MybatisMapperScan("com.dogfood.mybatissample.mapper")
public class AppConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        return sqlSessionFactory;
    }
}
