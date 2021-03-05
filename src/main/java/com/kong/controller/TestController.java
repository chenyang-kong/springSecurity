package com.kong.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class TestController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello SpringSecurity";
    }

    @RequestMapping("/login")
    public String test2(){
        return "login.html";
    }

    @RequestMapping("/test/index")
    @ResponseBody
    public String test(){
        return "hello index页面";
    }

    @RequestMapping("/test/logout")
    @ResponseBody
   // @Secured({"ROLE_admin1","ROLE_sale1"})
    public String test3(){
        return "hello 退出登录页面";
    }
    @RequestMapping("/test/aa")
    @ResponseBody
    //@Secured({"ROLE_admin","ROLE_sale"})
    @PreAuthorize("hasAnyAuthority('admins')")
    public String test4(){
        return "hello aaaaaaa页面";
    }
}
