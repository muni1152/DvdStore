package com.ideas2it.dvdstore.model;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.dvdstore.common.DvdConstants;
import com.ideas2it.dvdstore.model.Address;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.model.User;
import com.ideas2it.dvdstore.model.Orders;
import com.ideas2it.dvdstore.utils.DateUtils;

/**
  * <p>
  * Customer is a POJO object which is Plain Old Java Object 
  * Customer class performs to set and get the Id, Name, mobileNumber, mailId, 
  * address   
  * Customer class override equals toString method
  * <p/>
  *
  * author Muniraj M
  */
public class Customer {

    private Integer id;
    private String name;
    private String mobileNumber;
    private String mailId;
    private List<Orders> orders;
    private List<Address> addresses;
    private User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }
    
    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }
   
    public String getMailId() {
        return mailId;
    }
    
    public void setMailId(String mailId) {
        this.mailId = mailId;
    }

    public List<Orders> getOrders() {
        return orders;
    }
    
    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }

    public List<Address> getAddresses() {
        return addresses;
    }
    
    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
   
