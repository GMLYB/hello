package com.lyb.service;

import com.lyb.pojo.Cart;
import com.lyb.pojo.Order;
import com.lyb.pojo.User;

import java.util.List;

public interface OrderService {
    String createOrder(Cart cart, Integer userId);

    List<Order> showMyOrders(User user);
}
