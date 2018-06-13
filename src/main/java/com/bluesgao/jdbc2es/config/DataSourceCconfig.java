package com.bluesgao.jdbc2es.config;


import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


/**
 * ClassName: DataSourceCconfig
 * Desc: TODO
 * Author: gaoxin11
 * Date: 2018/6/13 13:58
 **/

//@Configuration
public class DataSourceCconfig {
    // 配置连接池
    @Bean(name="dataSource")
    @ConfigurationProperties("spring.datasource")
    public DataSource druidDataSource() {
        return new DruidDataSource();
    }
}