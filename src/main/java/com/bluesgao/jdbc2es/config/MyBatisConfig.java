package com.bluesgao.jdbc2es.config;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * ClassName: MyBatisConfig
 * Desc: TODO
 * Author: gaoxin11
 * Date: 2018/6/13 14:08
 **/
//@Configuration
//@MapperScan(basePackages = {"com.bluesgao.jdbc2es.dao"})
//@EnableTransactionManagement
public class MyBatisConfig {
    //这里在测试的时候使用Autowired的方式无法注入
    //@Autowired
    //DataSource dataSource;
    //以参数传进的方式，springboot会根据引用自动地注入dataSource
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] mapperXmlResource = resolver.getResources("classpath:mapper/*.xml");
        sqlSessionFactory.setDataSource(dataSource);
        sqlSessionFactory.setMapperLocations(mapperXmlResource);
        sqlSessionFactory.setTypeAliasesPackage("com.bluesgao.jdbc2es.model");
        return sqlSessionFactory;
    }

//    @Bean
//    public MapperScannerConfigurer mapperScannerConfigurer() {
//        MapperScannerConfigurer msc = new MapperScannerConfigurer();
//        msc.setBasePackage("com.bluesgao.jdbc2es.dao");
//        msc.setSqlSessionFactoryBeanName("sqlSessionFactory");
//        return msc;
//    }
}
