package com.ideas2it.dvdstore.service;

import com.ideas2it.dvdstore.exception.DvdException;
import com.ideas2it.dvdstore.model.User;
import com.ideas2it.dvdstore.model.Customer;

/**
  * <p>
  * LogInService is an interface which used to hide the data  for create, 
  * update, delete and retrieve functions
  * <p/>
  *
  * @author Muniraj M
  *
  */
public interface UserService {

    public User signIn(User user) throws DvdException;

    
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
    public Customer searchCustomerByMobileNumber(String mobileNumber) throws 
        DvdException;

     /** 
      * createCustomer is to create new customer account and fetch the details 
      *  of customer
      *
      * @return It returns true if customer account created else return false
      *        
      * @param customer 
      *        customer object which is contains information about customer
      */
    public Boolean saveUser(User user) throws DvdException;

}
