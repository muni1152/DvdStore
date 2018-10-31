package com.ideas2it.dvdstore.dao;

import com.ideas2it.dvdstore.exception.DvdException;
import com.ideas2it.dvdstore.model.Orders;
import java.util.List;

/**
  * <p>
  * OrderDao is an interface which used to hide the data  for place order, 
  * cancel order, search order and get All orders
  * <p/>
  *
  * @author Muniraj M
  *
  */
public interface OrderDao {
   
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
      * @param order 
      *        order object which is contains information about order
      * <p/>
      */
    public Boolean cancelOrder(Orders order) throws DvdException;

    /**
      * <P>
      * searchOrder search the particular order which we want.
      * the Order is get from the Database by using orderId
      * <p/>
      *
      * @param orderId
      *        unique number which is used to find the particular order
      *
      * @return It returns Orders Object if found else returns null
      *
      */
    public Orders searchOrder(int orderId) throws DvdException;

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
