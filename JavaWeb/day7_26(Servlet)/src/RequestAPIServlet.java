import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestAPIServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //getRequestURI() 获取请求的资源路径
        System.out.println("getRequestURI: " + req.getRequestURI());
        //getRequestURL() 获取请求的统一资源定位符（绝对路径）
        System.out.println("getRequestURL: " + req.getRequestURL());
        //getRemoteHost() 获取客户端的 ip 地址
        System.out.println("getRemoteHost : " + req.getRemoteHost());
        //getHeader() 获取请求头
        System.out.println("User-Agent: " + req.getHeader("User-Agent"));
        //getMethod() 获取请求的方式 GET 或 POST
        System.out.println("getMethod: " + req.getMethod());

        System.out.println("--------------------");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }
}
