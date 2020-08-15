package com.lyb.test;

import com.lyb.pojo.Cart;
import com.lyb.pojo.CartItem;
import com.lyb.pojo.Order;
import com.lyb.pojo.User;
import com.lyb.service.OrderService;
import com.lyb.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class OrderServiceTest {

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"java从入门到精通",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(1,"java从入门到精通",1,new BigDecimal(1000),new BigDecimal(1000)));
        cart.addItem(new CartItem(2, "数据结构与算法", 1, new BigDecimal(100),new BigDecimal(100)));
        OrderService orderService = new OrderServiceImpl();
        System.out.println( "订单号是：" + orderService.createOrder(cart, 1) );

    }

    @Test
    public void showMyOrders(){
        OrderService orderservice = new OrderServiceImpl();
        List<Order> orders = orderservice.showMyOrders(new User(1,"","",""));
        orders.forEach(System.out::println);
    }
}