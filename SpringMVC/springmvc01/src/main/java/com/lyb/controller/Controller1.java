package com.lyb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Controller1 {


    @RequestMapping(value = "/add/{a}/{b}",method = RequestMethod.GET)
//    @PathVariable注解可以将参赛映射到URL上
    public String add(@PathVariable int a,@PathVariable String b, Model model){
        String res = a + b;
        model.addAttribute("msg","结果为"+res);
        return "a";
    }
}
