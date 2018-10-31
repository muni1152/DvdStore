package com.ideas2it.dvdstore.model;

import java.util.List;

import com.ideas2it.dvdstore.common.DvdConstants;

/**
  * <p>
  * Category is a POJO object which is Plain Old Java Object 
  * Category class performs to set and get the Id, Name for category
  * <p/>
  *
  * author Muniraj M
  */
public class Category {

     private int id;
     private String category;
     private List<Dvd> dvds;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getCategory() {
        return category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }

    public List<Dvd> getDvds() {
        return dvds;
    }
    
    public void setDvds(List<Dvd>  dvds) {
        this.dvds = dvds;
    }
     
    @Override
    public String toString() {
       return (category);
    }
}
