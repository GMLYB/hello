package com.lyb.mvc.controller;

import com.lyb.mvc.pojo.ResultBean;
import com.lyb.mvc.pojo.UserBean;
import com.lyb.mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    @RequestMapping("/add")
    @ResponseBody
    public ResultBean<Boolean> addUser(@RequestBody UserBean user) {
        return ResultBean.getSuccess(userService.addUser(user));
    }
}
