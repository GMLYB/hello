import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet1 extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        System.out.println("在servlet 1 中接收的参数：" + username);

        //使用域数据给 username 弄上标签，然后传递到 servlet2
        request.setAttribute("key1","servlet1标签");

        //问路径 servlet2
        /**
         * 请求转发必须要用 / 开头； / 表示 http://ip:port/工程名/   映射到web目录
         */
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/servlet2");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/form.html");

        //走向 servlet2
        requestDispatcher.forward(request,response);

    }
}
