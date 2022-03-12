package com.librarymanager.library.cofig;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/index").permitAll();
    }

    //静态资源处理
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/*");
    }
}
///css/**","/images/**","/js/**","/scss/**","/vendor/**"
