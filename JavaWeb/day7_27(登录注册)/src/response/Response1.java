package response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Response1 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("轻轻的我走了，正如我轻轻的来；我轻轻的招手，作别西天的云彩。");

        //设置响应状态码 302  表示重定向（已搬迁）
        //方式一
        resp.setStatus(302);
        //设置响应头，说明新的地址在哪里
        resp.setHeader("Location","http://localhost:8080/0727web/response2");

        //方式二
        resp.sendRedirect("http://localhost:8080/0727web/response2");
    }
}
