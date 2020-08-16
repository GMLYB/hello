package servlet;

import com.google.gson.Gson;
import pojo.Person;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AjaxServlet extends BaseServlet {


    protected void javaScriptAjax(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("javaScriptAjax 方法被调用");
        Person person = new Person(1, "男人至死是少年");
        Gson gson = new Gson();
        String s = gson.toJson(person);
        resp.getWriter().write(s);
    }
    protected void jQueryAjax(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("jQueryAjax 方法被调用");
        Person person = new Person(2, "男人至死是少年");
        Gson gson = new Gson();
        String s = gson.toJson(person);
        resp.getWriter().write(s);
    }
    protected void jQueryGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("jQueryGet 方法被调用");
        Person person = new Person(3, "男人至死是少年");
        Gson gson = new Gson();
        String s = gson.toJson(person);
        resp.getWriter().write(s);
    }
    protected void jQueryPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("jQueryPost 方法被调用");
        Person person = new Person(4, "男人至死是少年");
        Gson gson = new Gson();
        String s = gson.toJson(person);
        resp.getWriter().write(s);
    }

    protected void jQueryJSON(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("jQueryJSON 方法被调用");
        Person person = new Person(5, "男人至死是少年");
        Gson gson = new Gson();
        String s = gson.toJson(person);
        resp.getWriter().write(s);
    }

    protected void jQuerySerialize(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("jQuerySerialize 方法被调用");
        String username = req.getParameter("username");
        System.out.println("username = " + username);
        Person person = new Person(6, username);
        Gson gson = new Gson();
        String s = gson.toJson(person);
        resp.getWriter().write(s);
    }
}
