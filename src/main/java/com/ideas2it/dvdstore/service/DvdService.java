package com.ideas2it.dvdstore.service;

import com.ideas2it.dvdstore.exception.CategoryException;
import com.ideas2it.dvdstore.exception.DvdException;
import com.ideas2it.dvdstore.logger.DvdLogger;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.model.Dvd;
import java.util.List;

/**
  * <p>
  * DvdService is an interface which used to hide the data  for create, update,
  * delete and retrieve functions
  * <p/>
  *
  * @author Muniraj M
  *
  */
public interface DvdService {
   
    /**
      * <p>
      * createDvd function is used to create new Dvd
      * <p/>
      *
      * @param dvd 
      *        dvd object which is contains information about dvd
      *
      * @return return true if the dvd added successsfully else return false
      *
      */
    public Boolean createDvd(Dvd dvd) throws DvdException;

    /**
      * <P>
      * searchDvd search the particular Dvd which we want
      * the Dvd is get from the list by using DvdId
      * <p/>
      *
      * @param dvdId
      *        unique number which is used to find the particular Dvd
      *
      * @return It return dvd object if the Id found else return null
      */
    public Dvd searchDvd(int dvdId) throws DvdException;

    /**
      * <P>
      * updateDvd update specific value for the particular Dvd which we want
      * the Dvd is get from the list by using DvdId
      * <p/>
      * 
      * @param dvd 
      *        dvd object which is contains information about dvd
      *
      * @param updateChoice 
      *        which is use for specific update
      *
      * @return It return true dvd updated else return false
      */
    public Boolean updateDvd(Dvd dvd) throws DvdException;

    /** 
      * <p>
      * deleteDvd is  to remove the particular Dvd which we want
      * the Dvd is get from the list by using DvdId
      *
      * @param dvdId
      *        unique number which is used to find the particular Dvd
      * 
      * @return boolean
      *         return true if dvd deleted
      *         return false if the dvdId not found
      */
     public Boolean deleteDvd(Dvd dvd) throws DvdException;

    /**
      * <p>
      * getDvds is a List function which is return the List 
      * List contains all available Dvds depend on the request
      * <p/>
      *
      * @param status
      *        status which contains information about Dvd active status
      *
      * @return It returns the List 
      */
    public List<Dvd> getDvds(String status) throws DvdException;

    /**
      * <P>
      * restoreDvd restore the particular Dvd which we entered
      * the Dvd is get from the list by using DvdId
      * <p/>
      *
      * @param dvdId
      *        unique number which is used to find the particular Dvd
      *
      * @return It retuns true if the Id found else return false
      */
    public Boolean restoreDvd(Dvd dvd) throws DvdException;
    
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


}

