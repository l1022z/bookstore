package com.bookstore.client.order.service;

import com.bookstore.commons.beans.Order;
import com.bookstore.commons.beans.Product;

import java.util.Map;

public interface IOrderService {
    void CreateOrder(Order order, Map<Product, Integer> cart);

    void paySuccess(String order_id);
}
