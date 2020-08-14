package com.lyb.web;

import com.lyb.pojo.Book;
import com.lyb.pojo.Cart;
import com.lyb.pojo.CartItem;
import com.lyb.service.BookService;
import com.lyb.service.impl.BookServiceImpl;
import com.lyb.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartServlet extends BaseServlet {

    BookService bookService = new BookServiceImpl();

    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取id和数量
        int id = WebUtils.parseInt(req.getParameter("id"),0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);
        // 获取 购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        System.out.println(cart);
        if (cart != null){
            cart.updateItem(id,count);
            //重定向回到原来购物车展示界面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取 购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        System.out.println(cart);
        if (cart != null){
            cart.clear();
            //重定向回到原来购物车展示界面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 删除商品项
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取id
        System.out.println(req.getParameter("id"));
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        System.out.println("id = " + id);
        // 获取 购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        System.out.println(cart);
        if (cart != null){
            cart.deleteItem(id);
            //重定向回到原来购物车展示界面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 添加商品项
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取id
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //查询图书信息
        Book book = bookService.queryBookById(id);
        // 添加进入商品项
        CartItem cartItem = new CartItem(book.getId(),book.getName(),1,book.getPrice(),book.getPrice());
        //获取session中的购物车
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null){
            //为空。就造一辆新的购物车
            cart = new Cart();
            //存到线程中
            req.getSession().setAttribute("cart",cart);
        }
        cart.addItem(cartItem);

//        System.out.println("Referer = " + req.getHeader("Referer")); // http://localhost:8080/0730web
//        System.out.println("URL = " + req.getRequestURL()); // http://localhost:8080/0730web/cartServlet
//        System.out.println();

        //将最后一个商品名称添加到session中
        req.getSession().setAttribute("lastName",cartItem.getName());

        //错的
//        resp.sendRedirect(String.valueOf(req.getRequestURL()));
        //重定向回到原来购物车展示界面
        resp.sendRedirect(req.getHeader("Referer"));
    }



}
