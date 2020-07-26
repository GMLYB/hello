import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Servlet2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        System.out.println("在servlet 2 中接收的参数：" + username);

        Object key1 = req.getAttribute("key1");
        System.out.println("servlet 1 的标签: " + key1);

        System.out.println("Servlet 2 的事物");


    }

}
