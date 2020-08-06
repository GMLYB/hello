package com.lyb.web;

import com.lyb.pojo.User;
import com.lyb.service.UserService;
import com.lyb.service.impl.UserServiceImpl;
import com.lyb.utils.WebUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class UserServlet extends BaseServlet {

    private UserService userService = new UserServiceImpl();


    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取请求参数
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());



        //2.调用 XxxService.xxx() 处理业务
        // userService.login() 登录
//        User loginUser = userService.login(new User(null, username, password, null));
        User loginUser = userService.login(user);
        //3. 根据 login() 方法返回结果判断是否登录成功
        if (loginUser != null){
            //成功：跳回登录成功页面 login_success.jsp
            System.out.println("登录成功");
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req,resp);

        }else {
            //失败：跳回登录页面
            System.out.println("账号或密码错误");
            req.setAttribute("errorMsg","账号或者密码错误！");
            req.setAttribute("username",user.getUsername());
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
        }
    }


    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1 获取请求参数
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//        String email = req.getParameter("email");
        String code = req.getParameter("code");
        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
        //2. 检查验证码  目前先写死，验证码为：abcde
        //equalsIgnoreCase 忽略大小写

//        try {
//            User user = new User();
//            System.out.println("注入之前："+ user);
//
//            // 把所有参数都注入到 User 对象中
//            BeanUtils.populate(user,req.getParameterMap());
//
//            System.out.println("注入之后："+user);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
//        User user = new User();
//        WebUtils.copyParamToBean(req.getParameterMap(),user);
//        User user = WebUtils.copyParamToBean(req.getParameterMap(), new User());
//        System.out.println(user);

        if ("abcde".equalsIgnoreCase(code)) {
            // 检查用户名是否可用
            //正确
            if (userService.existUsername(user.getUsername())){
                //不可用
                //返回注册界面
                req.setAttribute("errorMsg","用户名已存在!");
                req.setAttribute("username",user.getUsername());
                req.setAttribute("email",user.getEmail());
                req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
            }else {
                //可用
                //调用Service保存到数据库
                userService.registerUser(user);
//            跳转到注册成功界面 regist_success.jsp
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);
            }
        } else {
            //错误:返回注册界面
            System.out.println("验证码[" + code + "]错误!");
            req.setAttribute("errorMsg","验证码错误!");
            req.setAttribute("username",user.getUsername());
            req.setAttribute("email",user.getEmail());
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        String action = req.getParameter("action");
////        if ("login".equals(action)){
////            login(req,resp);
////        }else if ("regist".equals(action)){
////            regist(req,resp);
////        }
//        //通过反射直接调用 目标业务 的方法
//        try {
//            Method method = this.getClass().getDeclaredMethod(action,HttpServletRequest.class,HttpServletResponse.class);
//            method.invoke(this,req,resp);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
