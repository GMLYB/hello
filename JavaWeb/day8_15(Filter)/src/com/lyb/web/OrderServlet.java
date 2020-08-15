package com.lyb.web;

import com.lyb.pojo.Cart;
import com.lyb.pojo.Order;
import com.lyb.pojo.User;
import com.lyb.service.OrderService;
import com.lyb.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();

    protected void showAllOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Order> orders = orderService.showAllOrders();
        //放到request域中
        req.setAttribute("allorders",orders);
        //请求转发
        req.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(req,resp);

    }

    protected void showMyOrders(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //先获取线程中的用户
        User loginuser = (User) req.getSession().getAttribute("user");
        //还没有登录
        if (loginuser == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        List<Order> orders = orderService.showMyOrders(loginuser);
        //放到request域中
        req.setAttribute("orders",orders);
        //请求转发
        req.getRequestDispatcher("/pages/order/order.jsp").forward(req,resp);

    }

    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        //获取user
        User loginuser = (User) req.getSession().getAttribute("user");
        //还没有登录
        if (loginuser == null){
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req,resp);
            return;
        }
        Integer userid = loginuser.getId();
        //调用orderService.createOrder(Cart, Userid) 生成订单
        String orderId = orderService.createOrder(cart, userid);

        //存储到session中
        req.getSession().setAttribute("orderId",orderId);
        //使用重定向，避免重复提交
        resp.sendRedirect(req.getContextPath()+"/pages/cart/checkout.jsp");

    }
}
