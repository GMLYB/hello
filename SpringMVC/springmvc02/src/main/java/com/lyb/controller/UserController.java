package com.lyb.controller;

import com.lyb.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("/t2")
    public String test2(@RequestParam("username") String name, Model model){
        System.out.println("name = " + name);
        model.addAttribute("msg",name);
        return "test";
    }

    @GetMapping("/t1")
    public String test1(String name, Model model){
        System.out.println("name = " + name);
        model.addAttribute("msg",name);
        return "test";
    }

    @GetMapping("/t3")
    public String test3(User user,Model model){
        System.out.println(user);
        model.addAttribute("msg",user);
        return "test";
    }

    @PostMapping("/t4")
    public String test4(@RequestParam("name") String name, Model model){
        System.out.println("name" + name);
        model.addAttribute("msg",name);
        return "test";
    }

    @GetMapping("/t4")
    public String test5(@RequestParam("name") String name, Model model){
        System.out.println("name" + name);
        model.addAttribute("msg",name);
        return "test";
    }
}
