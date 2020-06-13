package com.bookstore.admin.orders.service;

import com.bookstore.admin.orders.dao.IAdminOrderDao;
import com.bookstore.commons.beans.Order;
import com.bookstore.commons.beans.OrderItem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2020/6/7
 */
@Service
public class IAdminOrderServiceImpl implements IAdminOrderService {
    @Resource
    IAdminOrderDao adminOrderDao;

    @Override
    public List<Order> findOrder() {
        return adminOrderDao.selectOrder();
    }

    @Override
    public List<Order> findOrderByManyCondition(Order order) {
        return adminOrderDao.selectOrderByManyCondition(order);
    }

    // @Override
    // public Order findOrderById(String id) {
    //     return adminOrderDao.selectOrderById(id);
    // }

    @Override
    public void removeOrder(String id) {
        adminOrderDao.deleteOrderById(id);
        adminOrderDao.deleteOrderItemById(id);
    }

    @Override
    public List<OrderItem> findOrderItemById(String id) {
        return adminOrderDao.selectOrderItemById(id);
    }
}
