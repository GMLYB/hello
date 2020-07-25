package servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class HelloServlet1 implements Servlet {

    public HelloServlet1() {
        System.out.println("1 构造器方法");
    }

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("2 初始化方法");
        System.out.println("HelloServlet程序的别名是："+ servletConfig.getServletName());
        System.out.println("HelloServlet程序的初始化参数是："+servletConfig.getInitParameter("username"));
        System.out.println("HelloServlet程序的初始化参数是："+servletConfig.getInitParameter("url"));
        System.out.println("HelloServlet程序的ervletContext是："+ servletConfig.getServletContext());
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    //service专门用来处理请求和响应的

    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {

        //ServletRequest类不能直接调用 getMethod() 方法，只有其子类才可以调用，例如HttpServletRequest
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
//        获取请求方式
        String method = httpServletRequest.getMethod();

        System.out.println(method);


        System.out.println("3 servlet 方法");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("4 destroy方法");

    }
}
