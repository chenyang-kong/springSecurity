package com.kong.config;

import com.kong.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig2 extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserServiceImpl userServiceImpl;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userServiceImpl).passwordEncoder(password());

    }
    @Bean
    PasswordEncoder password(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()//自定义自己编写的登陆页面
        .loginPage("/login.html")  //设置登陆页面
        .loginProcessingUrl("/login") //设置登陆路径
        . defaultSuccessUrl("/test/index") .permitAll() //登陆成功后跳转的路径
        .and().authorizeRequests()
         .antMatchers("/","/hello","/login") .permitAll() //不用认证即可访问的路径
        .anyRequest().authenticated()
        .and().csrf().disable(); //关闭csrf防护
    }
}
