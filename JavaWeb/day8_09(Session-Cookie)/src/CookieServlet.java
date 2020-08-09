import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CookieServlet extends BaseServlet {

    protected void cookiesetpath(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        Cookie cookie = new Cookie("pathcookie", "/abc");
        cookie.setPath(req.getContextPath()+"/abc");
        resp.addCookie(cookie);
        resp.getWriter().write("设置了带路径的cookie,路径为：/abc");
    }

    protected void cookielifeonehour(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("LifeOneHour","LifeOneHour");
        cookie.setMaxAge(60*60);
        resp.addCookie(cookie);
    }

    protected void deletecookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");

        Cookie cookie = CookieUtil.getcookie("key1", req.getCookies());
        if (cookie != null){
            //调用 setMaxAge(0);表示马上删除，都不需要等待浏览器关闭
            cookie.setMaxAge(0);
            resp.addCookie(cookie);
            resp.getWriter().write("cookie删除成功");
        }

    }

    //负数，表示浏览器一关，Cookie 就会被删除（默认值是-1）
    protected void cookielife(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie cookie = new Cookie("defalutLife","defalutLife");
        cookie.setMaxAge(-1);
        resp.addCookie(cookie);
    }

    protected void cookieupdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        Cookie cookie = CookieUtil.getcookie("key1", req.getCookies());

        if (cookie != null){
            cookie.setValue("newvalue");
            resp.addCookie(cookie);
            resp.getWriter().write("修改成功!");
            resp.getWriter().write(cookie.getName()+" = "+cookie.getValue());
        }

    }

    protected void getcookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            resp.getWriter().write("Cookie["+cookie.getName()+"="+cookie.getValue()+"] <br> ");
        }

        Cookie cookie = CookieUtil.getcookie("key1", cookies);
        if (cookie != null){
            resp.getWriter().write("找到了cookie");
        }

    }

    protected void createcookie(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        Cookie cookie = new Cookie("key1","value1");
        resp.addCookie(cookie);

        resp.getWriter().write("cookie创建成功");

    }
}
