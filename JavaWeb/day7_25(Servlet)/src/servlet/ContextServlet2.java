package servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContextServlet2 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取 ServletContext 对象
//        ServletContext context = getServletConfig().getServletContext();
        ServletContext context = getServletContext();

        context.setAttribute("key1","value1");

        System.out.println("Context 中获取域数据1的值: "+context.getAttribute("key1"));
    }
}
