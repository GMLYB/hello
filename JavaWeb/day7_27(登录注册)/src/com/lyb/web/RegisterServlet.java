package com.lyb.web;

import com.lyb.pojo.User;
import com.lyb.service.UserService;
import com.lyb.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {

    private UserService service = new UserServiceImpl();


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        /*  注册流程：
            1 获取请求参数
            2. 检查验证码  目前先写死，验证码为：abcde
                正确
                    检查用户名是否可用
                    可用
                        调用Service保存到数据库
                        跳转到注册成功界面 regist_success.html
                    不可用
                        返回注册界面
                错误
                    返回注册界面
         */

        //1 获取请求参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String code = req.getParameter("code");
        //2. 检查验证码  目前先写死，验证码为：abcde
        //equalsIgnoreCase 忽略大小写
        if ("abcde".equalsIgnoreCase(code)) {
//            正确
//                    检查用户名是否可用
            if (service.existUsername(username)){
//                    不可用
//            返回注册界面
                System.out.println("用户名["+ username +"]已存在！");
                req.getRequestDispatcher("/pages/user/regist.html").forward(req, resp);
            }else {
//            可用
//                    调用Service保存到数据库
                service.registerUser(new User(null,username,password,email));
//            跳转到注册成功界面 regist_success.html
                req.getRequestDispatcher("/pages/user/regist_success.html").forward(req, resp);
            }
        } else {
            //错误:返回注册界面
            System.out.println("验证码[" + code + "]错误!");
            req.getRequestDispatcher("/pages/user/regist.html").forward(req, resp);
        }


    }
}
