package com.lyb.springboot05.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
public class HelloController {

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(){
        return "hello!";
    }

    @RequestMapping("/success")
    public String succcess(){
        return "success";
    }

    @RequestMapping("/show")
    public String show(Map<String,Object> map){
        map.put("hello","扣你及哇");
        map.put("name","<h1>爷将主宰你的世界</h1>");
        map.put("users", Arrays.asList("wangwu","尼古拉斯赵四"));

        return "success";
    }
}
