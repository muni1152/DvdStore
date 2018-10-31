package com.ideas2it.dvdstore.model;

import java.time.LocalDate;
import java.util.List;

import com.ideas2it.dvdstore.model.Customer;


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
public class Address {

    private int id;
    private Integer customerId;
    private String pinCode;
    private String city;
    private String state;;
    private String country;
    private String addressLine;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }


    public String getAddressLine() {
        return addressLine;
    }
   
    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }
    
    public String toString() {
   return ("customer_add = " + customerId);
   }
}
    
