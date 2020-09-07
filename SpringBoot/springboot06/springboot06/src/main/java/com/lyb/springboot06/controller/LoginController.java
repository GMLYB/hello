package com.lyb.springboot06.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {


    @PostMapping("/user/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, Model model, HttpSession session) {
        System.out.println("login->");
        if ((!StringUtils.isEmpty(username)) && "123456".equals(password)) {
            session.setAttribute("loginUser",username);
            System.out.println("成功");
            return "redirect:/main.html";
        }else {
            model.addAttribute("msg","用户名密码错误");
            System.out.println("错误");
            return "login";
        }

    }

}
