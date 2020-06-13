package com.bookstore.admin.orders.service;

import com.bookstore.commons.beans.Order;
import com.bookstore.commons.beans.OrderItem;

import java.util.List;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2020/6/7
 */
public interface IAdminOrderService {
    List<Order> findOrder();

    List<Order> findOrderByManyCondition(Order order);

    // Order findOrderById(String id);

    void removeOrder(String id);

    List<OrderItem> findOrderItemById(String id);
}
