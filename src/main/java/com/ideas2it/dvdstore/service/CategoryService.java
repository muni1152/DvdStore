package com.ideas2it.dvdstore.service;

import com.ideas2it.dvdstore.exception.CategoryException;
import com.ideas2it.dvdstore.exception.DvdException;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.model.Dvd;
import java.util.List;

/**
  * <p>
  * CategoryService is an interface which used to hide the data  for create, 
  * update, delete and retrieve functions
  * <p/>
  *
  * @author Muniraj M
  *
  */
public interface CategoryService {
   
    /** 
      * createCategory is to create new Category and fetch the details of 
      * Category
      *
      * @return It returns integer value which is id for corresponding category
      *        
      * @param Category 
      *        Category object which is contains information about Category
      */
    public Boolean createCategory(Category category) throws CategoryException;

    /**
      * <P>
      * displayDvdsByCategories search the particular Category which we want
      * the Category is get from the list by using CategoryId
      * <p/>
      *
      * @param CategoryId
      *        unique number which is used to find the particular Category
      *
      * @return It return Category object if the Id found else return null
      */
    public Category displayDvdsByCategories(int categoryId) throws 
        CategoryException;

    /**
      * <P>
      * updateCategory update specific value for the particular Category which 
      * we want
      * the Category is get from the list by using CategoryId
      * <p/>
      * 
      * @param Category 
      *        Category object which is contains information about Category
      * 
      * @return boolean
      *         return the true if the Category updated else return false
      *
      */
    public Boolean updateCategory(Category category) throws CategoryException;

    /** 
      * <p>
      * deleteCategory is  to remove the particular Category which we want
      * the Category is get from the list by using CategoryId
      * </p>
      *
      * @param Category 
      *        Category object which is contains information about Category
      * 
      * @return boolean
      *         return true if the Category deleted else return false
      */
     public Boolean deleteCategory(Category category) throws CategoryException;

    /**
      * <p>
      * getCategoryStore is a List function which is return the List 
      * List contains all available Categorys
      * <p/>
      *
      * @param status
      *        status which contains Category active status
      *
      * @return It returns the List of categories
      * 
      */
    public List<Category> getCategories(String status) throws 
        CategoryException;

    /**
      * isCategoryExist is used to check the category is available in the 
      * list or not
      *
      * @param CategoryId
      *        unique number which is used to find the particular Category
      *
      * @param status
      *        status which contains Category active status
      *
      * @return category if category found else return null
      *
      */
    public Category getCategory(int categoryId) throws CategoryException;

    /**
      * <P>
      * restoreDvd restore the particular category which we entered
      * the category is get from the list by using categoryId
      * <p/>
      *
      * @param Category 
      *        Category object which is contains information about Category
      *
      * @return It retuns true if the category restored else return false
      */
    public Boolean restoreCategory(Category category) throws CategoryException;

    
}

