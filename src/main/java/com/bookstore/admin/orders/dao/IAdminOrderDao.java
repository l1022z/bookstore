package com.bookstore.admin.orders.dao;

import com.bookstore.commons.beans.Order;
import com.bookstore.commons.beans.OrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2020/6/7
 */
public interface IAdminOrderDao {
    List<Order> selectOrder();
    
    void deleteOrderById(String id);

    void deleteOrderItemById(String id);

    List<OrderItem> selectOrderItemById(String id);

    List<Order> selectOrderByManyCondition(@Param("order") Order order);
}
