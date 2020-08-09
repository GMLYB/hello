import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginSerlvet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if ("lyb123".equals(username) && "123456".equals(password)){
            Cookie cookie = new Cookie("username", username);
            cookie.setMaxAge(60*60*24);
            resp.addCookie(cookie);
            System.out.println("登录成功");
        }else {
            System.out.println("登录失败");
        }
    }
}
