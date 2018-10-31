package com.ideas2it.dvdstore.service;

import com.ideas2it.dvdstore.exception.DvdException;
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.Dvd;
import com.ideas2it.dvdstore.model.Orders;
import java.util.List;


/**
  * <p>
  * CustomerService is an interface which used to hide the data  for create, 
  * update, delete and retrieve functions
  * <p/>
  *
  * @author Muniraj M
  *
  */
public interface CustomerService {
   
    /** 
      * createCustomer is to create new customer account and fetch the details 
      *  of customer
      *
      * @return It returns true if customer account created else return false
      *        
      * @param customer 
      *        customer object which is contains information about customer
      */
    public Boolean createCustomer(Customer customer) throws DvdException;

    /**
      * <P>
      * updateCustomer update specific value for the particular customer which 
      * we want
      * the customer is get  by using customerId
      * <p/>
      * 
      * @param Customer 
      *        Customer object which is contains information about Customer
      * 
      * @return boolean
      *         return the true if the Customer updated else return false
      *
      */
    public Boolean updateCustomer(Customer customer) throws DvdException;

    /**
      * isMobileNumberExist is used to check the customer mobile number is 
      * valid or not 
      *
      * @param number
      *        which is unique mobile number
      *
      *
      * @return Customer if Customer found else return null
      *
      */
    public Customer searchCustomerByMobileNumber(String number) throws 
        DvdException;

     /**
      * searchCustomer is used to check the Customer is available in the 
      * list or not
      *
      * @param CustomerId
      *        unique number which is used to find the particular Customer
      *
      * @return Customer if Customer found else return null
      *
      */
    public Customer searchCustomer(int customerId) throws 
        DvdException;

    /**
      * <p>
      * getDvds is a List function which is return the List 
      * List contains all available Dvds depend on the request
      * <p/>
      *
      * @param status
      *        status which contains information about Dvd active status
      *
      * @return It returns the List 
      */
    public List<Dvd> getDvds(String status) throws DvdException;
   
    /**
      * <P>
      * searchDvd search the particular Dvd which we want
      * the Dvd is get from the list by using DvdId
      * <p/>
      *
      * @param dvdId
      *        unique number which is used to find the particular Dvd
      *
      * @param status
      *        status which contains information about Dvd active status
      *
      * @return It return dvd object if the Id found else return null
      */
    public Dvd searchDvd(int dvdId) throws DvdException;

    /**
      * <p>
      * purchaseDvd function which is used to purchase a new dvd which we want 
      * It intimates user by providing order Id which is used for reference
      * </p>
      *
      * @param order 
      *        which contains details about order
      *
      * @return It return true if dvd purchased succesfully else return false
      */
    public Boolean purchaseDvds(Orders order) throws DvdException;

    /**
      * <p>
      * cancelOrder function used to cancel order which removes order details 
      * permanently
      *
      * @param orderId 
      *        unique number which is used to find the particular Order
      *
      * @return It return true if order cancelled  else return false
      * <p/>
      */
    public Boolean cancelOrder(int orderId) throws DvdException;

    /**
      * <p>
      * getCustomers is a List function which is return the List 
      * List contains all available customer details 
      * <p/>
      *
      * @return It returns the List 
      */
    public List<Customer> getCustomers() throws DvdException;


} 
