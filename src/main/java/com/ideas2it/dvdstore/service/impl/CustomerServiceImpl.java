package com.ideas2it.dvdstore.service.impl;

import com.ideas2it.dvdstore.dao.CustomerDao;
import com.ideas2it.dvdstore.dao.impl.CustomerDaoImpl;
import com.ideas2it.dvdstore.service.CustomerService;
import com.ideas2it.dvdstore.service.DvdService;
import com.ideas2it.dvdstore.service.impl.DvdServiceImpl;
import com.ideas2it.dvdstore.exception.DvdException;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.Dvd;
import com.ideas2it.dvdstore.model.Orders;
import com.ideas2it.dvdstore.service.OrderService;
import com.ideas2it.dvdstore.service.impl.OrderServiceImpl;
import com.ideas2it.dvdstore.service.impl.UserServiceImpl;
import com.ideas2it.dvdstore.service.UserService;


import java.util.List;

/** 
  * <p>
  * DvdSevice class performs  validation process and business logic 
  * Crete ,Update, Delete, Search, Display method's validation process done here
  * <p/>
  * 
  * author Muniraj M
  */
public class CustomerServiceImpl implements CustomerService {

    private CustomerDao customerDao = new CustomerDaoImpl();
    private DvdService dvdService = new DvdServiceImpl();
    private OrderService orderService = new OrderServiceImpl();
    private UserService userService = new UserServiceImpl();


    /**
      * @{inheritdoc}
      */
    public Boolean createCustomer(Customer customer) throws DvdException {
         if (userService.saveUser(customer.getUser())) {
            return customerDao.insertCustomer(customer);
         } else {
            return Boolean.FALSE;
         }
    }

    /**
      * @{inheritdoc}
      */
    public Customer searchCustomerByMobileNumber(String number) throws 
            DvdException  {
        return customerDao.searchCustomerByMobileNumber(number);
    }

    /**
      * @{inheritdoc}
      */
    public Boolean updateCustomer(Customer customer) throws DvdException {
        return customerDao.updateCustomer(customer);
    }
 
      /**
      * @{inheritdoc}
      */
    public List<Dvd> getDvds(String status) throws DvdException {
        return dvdService.getDvds(status);
    }

    /**
      * @{inheritdoc}
      */
    public Customer searchCustomer(int customerId) throws 
            DvdException  {
        return customerDao.searchCustomer(customerId);
    }

    /**
      * @{inheritdoc}
      */
    public Dvd searchDvd(int dvdId) throws DvdException {
        return dvdService.searchDvd(dvdId);
    }

    /**
      * @{inheritdoc}
      */
    public Boolean purchaseDvds(Orders order) throws DvdException {
        return orderService.placeOrder(order);
    }

    /**
      * @{inheritdoc}
      */
    public Boolean cancelOrder(int orderId) throws DvdException {
        return orderService.cancelOrder(orderId);
    }

    /**
      * @{inheritdoc}
      */
    public List<Customer> getCustomers() throws DvdException {
        return customerDao.getCustomers();
    }

  

}
