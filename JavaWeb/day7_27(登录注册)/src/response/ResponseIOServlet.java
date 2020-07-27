package response;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ResponseIOServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println(" 初始字符集 "+resp.getCharacterEncoding());// 初始字符集 ISO-8859-1
        //解决中文乱码方式一
        //设置服务器字符集为UTF-8  要是仍然乱码，则是浏览器的编码不正确。为GDK
//        resp.setCharacterEncoding("UTF-8");

        //通过响应头。设置浏览器也使用UTF-8字符集
//        resp.setHeader("Content-Type","text/html; charset=UTF-8");

        //解决中文乱码方式二
        //同时设置服务器和浏览器都使用UTF-8字符集，还设置了响应头
        //注意：此方法一定要在获取流对象之前调用才有效
        resp.setContentType("text/html; charset=UTF-8");

        //两个只能存在一个
//        resp.getOutputStream();
        PrintWriter writer = resp.getWriter();
        writer.write("response's content  大佬666");
    }
}
