package com.lyb.web;

import com.lyb.pojo.Book;
import com.lyb.service.BookService;
import com.lyb.service.impl.BookServiceImpl;
import com.lyb.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class BookServlet extends BaseServlet {

    BookService service = new BookServiceImpl();

    protected void addbook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());

        service.addBook(book);

        System.out.println(req.getContextPath());/* "/0730web" */

        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=querylist");

    }

    protected void updatebook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());

        service.updateBook(book);

        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=querylist");

    }

    protected void getbook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        Book book = service.queryBookById(id);

        req.setAttribute("book",book);

        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req,resp);

    }


    protected void deletebook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        service.deleteBookById(id);

        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=querylist");

    }

    protected void querylist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Book> books = service.queryBooks();

        req.setAttribute("books",books);

        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req,resp);
    }
}
