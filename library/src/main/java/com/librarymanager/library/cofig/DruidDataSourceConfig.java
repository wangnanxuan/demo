package com.librarymanager.library.cofig;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootConfiguration//springboot自己的配置类注解
public class DruidDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")//将数据库连接信息直接封装到数据对象中
    public DataSource getDruidDataSource(){
        return new DruidDataSource();
    }
}
