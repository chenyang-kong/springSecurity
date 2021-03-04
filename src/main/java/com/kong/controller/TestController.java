package com.kong.controller;

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

    @RequestMapping("/test/index")
    @ResponseBody
    public String test(){
        return "hello index页面";
    }
    @RequestMapping("/login")
    public String test2(){
        return "login.html";
    }
}
