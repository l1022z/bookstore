package com.bookstore.client.order.dao;

import com.bookstore.commons.beans.Order;
import com.bookstore.commons.beans.OrderItem;

public interface IOrderDao {
    void insertOrder(Order order);

    void insertOrderItem(OrderItem item);

    void updateProductnum(OrderItem item);

    void updatePaystate(String order_id);
}
