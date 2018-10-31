package com.ideas2it.dvdstore.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.ideas2it.dvdstore.common.DvdConstants;
import com.ideas2it.dvdstore.dao.OrderDao;
import com.ideas2it.dvdstore.dao.impl.OrderDaoImpl;
import com.ideas2it.dvdstore.exception.DvdException;
import com.ideas2it.dvdstore.model.Orders;
import com.ideas2it.dvdstore.service.OrderService;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();

    /** 
      * @{inheritdoc}
      */
    public Boolean placeOrder(Orders order) throws DvdException {
        return orderDao.placeOrder(order);
    }

    
    /** 
      * @{inheritdoc}
      */
    public Boolean cancelOrder(int orderId) throws DvdException {
        Orders order = orderDao.searchOrder(orderId);
        if (null ==  order) {
            return Boolean.FALSE;
        } else {
            return orderDao.cancelOrder(order);
        }
    }

    /** 
      * @{inheritdoc}
      */
    public List<Orders> getOrders() throws DvdException {
        return orderDao.getOrders();
    }
}
