package com.librarymanager.library.cofig;

import com.librarymanager.library.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/index","/css/**","/images/**","/js/**","/scss/**","/vendor/**","/book/**","/user/**","/logout")
                .permitAll()
                .antMatchers("/","/index","/book/toBookTables","/user/toTable").hasAnyRole("LEVEL2","LEVEL1")
                .antMatchers("/user/**").hasRole("LEVEL2")
                .antMatchers("/book/**").hasRole("LEVEL1")
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/toLogin")
                .defaultSuccessUrl("/index").permitAll()
                .and()
                .rememberMe()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/unAuthorize");

                //关闭csrf防护
                http.csrf().disable();
                http.logout().logoutUrl("/logout").logoutSuccessUrl("/toLogin").permitAll();
    }

    //静态资源处理
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("css/**","/images/**","/js/**","/scss/**","/vendor/**");
    }


}

