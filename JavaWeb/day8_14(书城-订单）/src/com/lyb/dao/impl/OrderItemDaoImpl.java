package com.lyb.dao.impl;

import com.lyb.dao.BaseDao;
import com.lyb.dao.OrderItemDao;
import com.lyb.pojo.OrderItem;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
        return update(sql,orderItem.getName(),orderItem.getCount(),orderItem.getPrice(),orderItem.getTotalprice(), orderItem.getOrderId());
    }
}
