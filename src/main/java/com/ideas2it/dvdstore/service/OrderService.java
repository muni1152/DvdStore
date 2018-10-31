package com.ideas2it.dvdstore.service;

import com.ideas2it.dvdstore.exception.DvdException;
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.Orders;
import com.ideas2it.dvdstore.model.Dvd;
import com.ideas2it.dvdstore.service.CustomerService;
import com.ideas2it.dvdstore.service.impl.CustomerServiceImpl;
import java.util.List;

/**
  * <p>
  * CategoryService is an interface which used to hide the data  for create, 
  * update, delete and retrieve functions
  * <p/>
  *
  * @author Muniraj M
  *
  */
public interface OrderService {
   
    /**
      * <p>
      * placeOrder function which is used to purchase a new dvd which we want 
      * It intimates user by providing order Id which is used for reference
      * </p>
      *
      * @param order 
      *        which contains details about order
      *
      * @return It return true if dvd purchased succesfully else return false
      */
    public Boolean placeOrder(Orders order) throws DvdException;

    /**
      * <p>
      * cancelOrder function used to cancel order which removes order details 
      * permanently
      *
      * @param orderId 
      *        unique number which is used to find particular order
      * <p/>
      */
    public Boolean cancelOrder(int orderId) throws DvdException;

    /**
      * <p>
      * getOrders is a List function which is return the List 
      * List contains all available order details 
      * <p/>
      *
      * @return It returns the List 
      */
    public List<Orders> getOrders() throws DvdException;
}
