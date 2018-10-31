package com.ideas2it.dvdstore.model;

import java.time.LocalDate;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.List;

import com.ideas2it.dvdstore.common.DvdConstants;
import com.ideas2it.dvdstore.utils.DateUtils;
import com.ideas2it.dvdstore.model.Category;


/**
  * <p>
  * Dvd is a POJO object which is Plain Old Java Object 
  * Dvd class performs to set and get the Id, Name, Rating, Category, Language
  *     Price and Release Date 
  * Dvd class override equals and toString method
  * <p/>
  *
  * author Muniraj M
  */
public class Dvd {

    private int id;
    private float rating;
    private float price;
    private String name;
    private List<Category> categories;
    private String language;
   @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public List<Category> getCategories() {
        return categories;
    }
    
    public void setCategories(List<Category>  categories) {
        this.categories = categories;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

}

