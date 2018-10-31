
package com.ideas2it.dvdstore.service.impl;

import com.ideas2it.dvdstore.dao.UserDao;
import com.ideas2it.dvdstore.dao.impl.UserDaoImpl;
import com.ideas2it.dvdstore.exception.DvdException;
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.Orders;
import com.ideas2it.dvdstore.model.User;
import com.ideas2it.dvdstore.service.UserService;
import com.ideas2it.dvdstore.service.CustomerService;
import com.ideas2it.dvdstore.service.impl.CustomerServiceImpl;


/** 
  * <p>
  * DvdSevice class performs  validation process and business logic 
  * Crete ,Update, Delete, Search, Display method's validation process done here
  * <p/>
  * 
  * author Muniraj M
  */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();
      

    /**
      * @{inheritdoc}
      */
    public User signIn(User user) throws DvdException {
        return userDao.signIn(user);
    }

    /**
      * @{inheritdoc}
      */
    public Customer searchCustomerByMobileNumber(String mobileNumber) throws 
            DvdException  {
        CustomerService customerService = new CustomerServiceImpl();
        return customerService.searchCustomerByMobileNumber(mobileNumber);
    }

    public Boolean saveUser(User user) throws DvdException {
        return userDao.saveUser(user);
    }

}
    
