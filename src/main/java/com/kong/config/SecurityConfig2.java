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
        //配置没有访问权限后跳转的页面
        http.exceptionHandling().accessDeniedPage("/anauth.html");
        //退出登录配置
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/test/logout").permitAll();
        http.formLogin()//自定义自己编写的登陆页面
        .loginPage("/login.html")  //设置登陆页面
        .loginProcessingUrl("/login") //设置登陆路径
        . defaultSuccessUrl("/success.html") .permitAll() //登陆成功后跳转的路径
        .and().authorizeRequests()
         .antMatchers("/","/hello","/login") .permitAll() //不用认证即可访问的路径
         //只有拥有 admins权限的用户才能访问 /test/index路径
        .antMatchers("/test/index").hasAuthority("admin")
        // .antMatchers("/test/index").hasAnyAuthority("admin,aa")
         //只有拥有 sale角色的用户才能访问 /test/index路径
         //.antMatchers("/test/index").hasRole("sale")
        //拥有 sale或admin角色的用户才能访问 /test/index路径
         //.antMatchers("/test/index").hasAnyRole("sale,admin")
        .anyRequest().authenticated()
        .and().csrf().disable(); //关闭csrf防护
    }
}
