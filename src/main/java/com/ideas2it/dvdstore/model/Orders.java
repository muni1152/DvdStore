package com.ideas2it.dvdstore.model;

import java.util.List;
import java.time.LocalDate;
import com.ideas2it.dvdstore.common.DvdConstants;
import com.ideas2it.dvdstore.model.Dvd;
import com.ideas2it.dvdstore.model.Customer;


/**
  * <p>
  * Order is a POJO object which is Plain Old Java Object 
  * Category class performs to set and get the Id, Name for category
  * <p/>
  *
  * author Muniraj M
  */
public class Orders {

    private int orderId;
    private int customerId;
    private LocalDate orderDate;
    private List<Dvd> dvds;
    private Address address;

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public List<Dvd> getDvds() {
        return dvds;
    }

    public void setDvds(List<Dvd> dvds) {
        this.dvds = dvds;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public Address getAddress() {
      return address;
   }
   
   public void setAddress( Address address ) {
      this.address = address;
   }
    
   public String toString() {
   return ("customer_order = " + customerId);
   }
}
