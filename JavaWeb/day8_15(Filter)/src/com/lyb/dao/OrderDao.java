package com.lyb.dao;

import com.lyb.pojo.Order;
import com.lyb.pojo.User;

import java.util.List;

public interface OrderDao {
    int saveOrder(Order order);

    List<Order> queryOrderByUserId(User user);

    List<Order> queryOrders();
}
