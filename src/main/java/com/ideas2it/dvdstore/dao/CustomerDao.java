package com.ideas2it.dvdstore.dao;

import com.ideas2it.dvdstore.exception.DvdException;
import com.ideas2it.dvdstore.model.Customer;
import java.util.List;

/**
  * <p>
  * DvdDao is an interface which used to hide the data  for create, update,
  * delete and retrieve functions
  * <p/>
  *
  * @author Muniraj M
  *
  */
public interface CustomerDao {

   /** 
      * createCustomer is to create new customer account and fetch the details 
      *  of customer
      *
      * @return It returns true if customer account created else return false
      *        
      * @param customer 
      *        customer object which is contains information about customer
      */
    public Boolean insertCustomer(Customer customer) throws DvdException;

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
      * <P>
      * updateCustomer update specific value for the particular Customer which 
      * we want
      * the Customer is get from by using CustomerId
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
      * searchCustomer is used to check the Customer is available in the 
      * list or not
      *
      * @param CustomerId
      *        unique number which is used to find the particular Customer
      *
      * @return Customer if Customer found else return null
      *
      */
    public Customer searchCustomer(int CustomerId) throws DvdException;

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

