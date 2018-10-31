package com.ideas2it.dvdstore.dao;

import com.ideas2it.dvdstore.exception.DvdException;
import com.ideas2it.dvdstore.model.User;
import java.util.List;

/**
  * <p>
  * CategoryDao is an interface which used to hide the data  for create, update,
  * delete and retrieve functions
  * <p/>
  *
  * @author Muniraj M
  *
  */
public interface UserDao  {

     public User signIn(User user) throws DvdException;

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
