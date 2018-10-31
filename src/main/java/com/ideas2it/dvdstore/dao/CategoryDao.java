package com.ideas2it.dvdstore.dao;

import com.ideas2it.dvdstore.exception.CategoryException;
import com.ideas2it.dvdstore.model.Category;
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
public interface CategoryDao  {

    /**
      * <p>
      * createCategory function used to add a new Category 
      * <p/>
      *
      * @param Category 
      *        It is the Category object which to be stored into the Database
      *
      * @return return id if the Category added successsfully
      *         return 0 if Category already exist in the list
      */
    public Boolean insertCategory(Category category) throws CategoryException;
   

    /**
      * deleteCategory to remove a existing Category  into the Database 
      *
      * @param Category 
      *        It is the Category object which to be removed into the Database
      * @return return true if the Category removed successsfully
      *         return false if Category is not in the list
      */
    public Boolean deleteCategory(Category category) throws CategoryException;

    /**
      * <P>
      * searchCategory search the particular Category which we want.
      * the Category is get from the Database by using CategoryId
      * <p/>
      *
      * @param CategoryId
      *        unique number which is used to find the particular Category
      *
      * @return It return Category  if the Id found else return null
      */
    public Category displayDvdsByCategories(int categoryId) throws 
        CategoryException;

    /**
      * <P>
      * updateCategory update specific value for the particular Category which
      * we want.
      * the Category is get from the Database by using CategoryId
      * <p/>
      * 
      * @param Category 
      *        It is the Category object which to be storesd into the Database
      *
      * @return boolean
      *         return the true if the Category is found by CategoryId 
      *         return false if the CategoryId not in the list
      *
      */
    public Boolean updateCategory(Category category) throws CategoryException;

    /** 
      * <p>
      * getCategoryStore which is List function to return the list
      * The list object cointains all available Category's which is get from 
      * Database
      * </p>
      *
      * @return return all available Categorys
      */
    public List<Category> getCategories(String status) throws 
        CategoryException;
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

    /**
      * isCategoryIdExist is used to check the category is available in the 
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
    public Category getCategory(int categoryId) 
            throws CategoryException;
  
}
