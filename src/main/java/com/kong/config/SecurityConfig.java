package com.kong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

//@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //这两行是对密码加密，可以不写
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String pwd = bCryptPasswordEncoder.encode("123");
       //这个要写
        auth.inMemoryAuthentication()
               .withUser("zhangsan")//设置用户名
               .password(pwd)//设置密码
               .roles("");//设置角色
    }
    //因为上面对密码加密了，所以这个必须写，上面没加密就不用写
    @Bean
    PasswordEncoder password(){
        return new BCryptPasswordEncoder();
    }
}
