import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SessionServlet extends BaseServlet {

    String type = "text/html; charset=UTF-8";


    protected void defaultsession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(type);
        HttpSession session = req.getSession();
        int maxInactiveInterval = session.getMaxInactiveInterval();
        resp.getWriter().write("默认超时时间为:"+maxInactiveInterval);
    }
    protected void life3(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(type);
        HttpSession session = req.getSession();
        //让session 3s 后超时
        session.setMaxInactiveInterval(3);
        resp.getWriter().write("当前 Session 已经设置为 3 秒后超时");
    }
    protected void deletenow(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(type);
        HttpSession session = req.getSession();
        //让session马上超时
        session.invalidate();
        resp.getWriter().write("Session 已经设置为超时（无效）");
    }

    protected void createsession(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(type);
        HttpSession session = req.getSession();
        String id = session.getId();
        resp.getWriter().write("JSESSIONID:"+id+"<br/>");

        resp.getWriter().write("是新创建的："+session.isNew());

    }

    protected void setAttribute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        resp.setContentType(type);
        req.getSession().setAttribute("key1","value1");
        req.getSession().setAttribute("key2","value2");
        resp.getWriter().write("session储存了数据");

    }

    protected void getAttribute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(type);
        Object key1 = req.getSession().getAttribute("key2");
        resp.getWriter().write("session中key1的数据: "+ key1);
    }
}
