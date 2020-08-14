package com.lyb.dao.impl;

import com.lyb.dao.BaseDao;
import com.lyb.dao.OrderDao;
import com.lyb.pojo.Order;
import com.lyb.pojo.User;

import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public int saveOrder(Order order) {

        String sql = "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
        return update(sql,order.getOrderId(),order.getCreateTime(),order.getPrice(),order.getStatus(),order.getUserId());
    }

    @Override
    public List<Order> queryOrderByUserId(User user) {
        String sql = "SELECT order_id orderId,create_time createTime,price,STATUS,user_id userId FROM t_order WHERE `user_id` = ?";
        List<Order> orders = queryForList(Order.class, sql, user.getId());
        return orders;
    }
}
