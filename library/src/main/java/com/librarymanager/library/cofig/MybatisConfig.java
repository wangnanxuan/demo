package com.librarymanager.library.cofig;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static com.baomidou.mybatisplus.annotation.DbType.MYSQL;

@Configuration
//开启事务
@EnableTransactionManagement
//扫描mapper文件
@MapperScan("com/librarymanager/library/mapper")
public class MybatisConfig {
    //乐观锁插件配置
    @Bean
    public MybatisPlusInterceptor mybatisPlusOptimisticInterceptorOptimistic() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        mybatisPlusInterceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        return mybatisPlusInterceptor;
    }

    //配置分页插件
    @Bean
    public MybatisPlusInterceptor mybatisPlusPaginationInnerInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(MYSQL));
        return interceptor;
    }
}
