package com.lyb.web;

import com.lyb.pojo.User;
import com.lyb.service.UserService;
import com.lyb.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");


        //2.调用 XxxService.xxx() 处理业务
        // userService.login() 登录
        User loginUser = userService.login(new User(null, username, password, null));
        //3. 根据 login() 方法返回结果判断是否登录成功
        if (loginUser != null){
            //成功：跳回登录成功页面 login_success.html
            System.out.println("登录成功");
            req.getRequestDispatcher("/pages/user/login_success.html").forward(req,resp);

        }else {
            //失败：跳回登录页面
            System.out.println("账号或密码错误");
            req.getRequestDispatcher("/pages/user/login.html").forward(req,resp);
        }




    }
}
