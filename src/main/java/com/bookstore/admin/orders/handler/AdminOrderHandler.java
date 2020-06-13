package com.bookstore.admin.orders.handler;

import com.bookstore.admin.orders.service.IAdminOrderService;
import com.bookstore.commons.beans.Order;
import com.bookstore.commons.beans.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * company: www.abc.com
 * Author: Administrator
 * Create Data: 2020/6/7
 */
@RequestMapping("/admin/orders")
@Controller
public class AdminOrderHandler {

    @Autowired
    IAdminOrderService adminOrderService;

    //获取所有订单列表
    @RequestMapping("/findOrder")
    public String findOrders(Model model) {
        List<Order> orders = adminOrderService.findOrder();
        model.addAttribute("orders",orders);
        for (Order p:orders) {
            System.out.println(p);
        }
        return "/admin/orders/list.jsp";
    }
    //按照多个条件查询订单
    @RequestMapping("/findOrderByManyCondition")
    public String findOrderByManyCondition(Order order, Model model) {
        List<Order> orders = adminOrderService.findOrderByManyCondition(order);
        model.addAttribute("orders",orders);
        for (Order p:orders) {
            System.out.println(p);
        }
        return "/admin/orders/list.jsp";
    }


    //查看订单详细信息
    @RequestMapping("/findOrderById")
    public String findOrderById(String id,Model model) {
        // List<Order> orders= adminOrderService.findOrderById(id);
        List<OrderItem> items = adminOrderService.findOrderItemById(id);
        List<Order> orders = adminOrderService.findOrder();
        model.addAttribute("orders",orders);
        model.addAttribute("items",items);
        for (Order p:orders) {
            System.out.println(p);
        }
        for (OrderItem p:items) {
           System.out.println(p);
         }
        return "/admin/orders/view.jsp";
    }

    //删除商品
    @RequestMapping("/removeOrder")
    public String removeOrder(String id) {
        adminOrderService.removeOrder(id);
        return "redirect:/admin/orders/findOrder";
    }
}
