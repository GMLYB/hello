package com.lyb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {

    @RequestMapping(path = "/hello")
    public String SayHello(){
        System.out.println("你好，StringMVC");
        return "success2";
    }


    @RequestMapping("/hello2")
    public String SayHello2(Model model, HttpServletRequest servletRequest){
        System.out.println("你好，StringMVC2");
        model.addAttribute("msg","欢迎使用Spring MVC");
        servletRequest.setAttribute("msg1","欢迎使用Spring MVC1");
        return "success";
    }
}
