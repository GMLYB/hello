package com.lyb.service.impl;

import com.lyb.dao.BookDao;
import com.lyb.dao.OrderDao;
import com.lyb.dao.OrderItemDao;
import com.lyb.dao.impl.BookDaoImpl;
import com.lyb.dao.impl.OrderDaoImpl;
import com.lyb.dao.impl.OrderItemDaoImpl;
import com.lyb.pojo.*;
import com.lyb.service.OrderService;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //生成订单号 = 时间戳 + userID
        String orderId = System.currentTimeMillis()+""+userId;
        //创建一个订单对象
        Order order = new Order(orderId,new Date(),cart.getTotalPrice(),0,userId);
        //保存订单项
        orderDao.saveOrder(order);

        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()){
            //获取购物项
            CartItem cartItem = entry.getValue();
            //转为订单项
            OrderItem orderItem = new OrderItem(null,cartItem.getName(),cartItem.getCount(),cartItem.getPrice(),cartItem.getTotalPrice(),orderId);
            //保存订单项到数据库中
            orderItemDao.saveOrderItem(orderItem);

            int t = 12 / 0;

            //更新库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            //更新销量
            book.setSales( book.getSales() + cartItem.getCount());
            //更新库存
            book.setStock( book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);
        }
        //清空购物车
        cart.clear();
        return orderId;
    }

    @Override
    public List<Order> showMyOrders(User user) {
        List<Order> orders = orderDao.queryOrderByUserId(user);
        return orders;
    }

    @Override
    public List<Order> showAllOrders() {
        List<Order> orders = orderDao.queryOrders();
        return orders;
    }
}
