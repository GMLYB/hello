package com.lyb.springboot06.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

    @RequestMapping("/list")
    public String hello(){


        return "list";
    }

    @RequestMapping("/list1")
    public String hello1(){


        return "404";
    }

    @RequestMapping("/list2")
    public String hello2(){


        return "login";
    }
}
