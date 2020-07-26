import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class ParameterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("========post===================");
        request.setCharacterEncoding("UTF-8");
        //获取请求的参数
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String[] hobbies = request.getParameterValues("hobby");
        System.out.println("用户名: " + username);
        System.out.println("密码: " + password);
        System.out.println("爱好: " + Arrays.asList(hobbies));
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("------------------get-------------------");
        String password = request.getParameter("password");
        request.setCharacterEncoding("UTF-8");
        //获取请求的参数
        String username = request.getParameter("username");
        String[] hobbies = request.getParameterValues("hobby");
        System.out.println("用户名: " + username);
        System.out.println("密码: " + password);
        System.out.println("爱好: " + Arrays.asList(hobbies));

    }
}
