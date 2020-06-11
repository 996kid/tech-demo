package com.eastwood.config;

import com.alibaba.druid.pool.DruidDataSource;
import io.seata.rm.datasource.DataSourceProxy;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

    @Bean
    public DataSourceProxy dataSourceProxy(DataSource dataSource) {
        return new DataSourceProxy(dataSource);
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSourceProxy dataSourceProxy) {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSourceProxy);
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        try {
//            Resource[] mapperLocaltions = resolver.getResources(mybatisProperties.getMapperLocations()[0]);
//            bean.setMapperLocations(mapperLocaltions);
//
//            if (StringUtils.isNotBlank(mybatisProperties.getConfigLocation())) {
//                Resource[] resources = resolver.getResources(mybatisProperties.getConfigLocation());
//                bean.setConfigLocation(resources[0]);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return bean;
    }
}
