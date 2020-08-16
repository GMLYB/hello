package servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //一定要在获取请求参数之前调用才有效
        req.setCharacterEncoding("UTF-8");
        //设置response编码
        resp.setContentType("text/html; charset=UTF-8");
        String action = req.getParameter("action");
//        if ("login".equals(action)){
//            login(req,resp);
//        }else if ("regist".equals(action)){
//            regist(req,resp);
//        }
        //通过反射直接调用 目标业务 的方法
        try {
            Method method = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            //这里的异常也要抛出，给Filter捕获
            throw new RuntimeException(e);
        }
    }
}
