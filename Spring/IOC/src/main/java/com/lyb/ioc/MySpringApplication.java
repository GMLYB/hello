package com.lyb.ioc;

import com.lyb.ioc.autoconfig.MySpringContext;

import com.lyb.ioc.controller.UserController;

public class MySpringApplication {
    public static void main(String[] args) {
        MySpringContext context = new MySpringContext();
        try {
            System.out.println("开始初始化容器");
            long start = System.currentTimeMillis();
            context.initContext("com.lyb.ioc");
            System.out.println("容器初始化成功，花费时间为：" + (System.currentTimeMillis() - start));
            UserController userController = context.getBean("userController");
            userController.addUser();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
