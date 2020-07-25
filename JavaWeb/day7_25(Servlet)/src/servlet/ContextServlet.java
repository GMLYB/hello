package servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContextServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.获取 web.xml 中配置的上下文参数
        ServletConfig config = getServletConfig();
        ServletContext context = config.getServletContext();
        String username = context.getInitParameter("username");
        String password = context.getInitParameter("password");
        System.out.println("username = "+ username + " ; password = " + password);
        //2.获取当前的工程路径，格式为：/工程名
        System.out.println("当前工程路径:"+context.getContextPath());
        //3.获取工程部署后在服务器硬盘上的绝对路径
        //context.getRealPath("/") / 被服务器解析为： http://ip:port/工程名/ 映射到IDEA代码的web目录
        System.out.println("工程部署后在服务器硬盘上的绝对路径："+context.getRealPath("/"));

    }
}
