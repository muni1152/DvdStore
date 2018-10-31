package com.ideas2it.dvdstore.dao;

import com.ideas2it.dvdstore.exception.DvdException;
import com.ideas2it.dvdstore.logger.DvdLogger;
import com.ideas2it.dvdstore.model.Dvd;
import java.util.List;

/**
  * <p>
  * DvdDao is an interface which used to hide the data  for create, update,
  * delete and retrieve functions
  * <p/>
  *
  * @author Muniraj M
  *
  */
public interface DvdDao {

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
    public Boolean insertDvd(Dvd dvd) throws DvdException;

    
    /**
      * deleteDvd to remove a existing Dvd  into the Database 
      *
      * @param dvd 
      *        dvd object which is contains information about dvd
      *
      * @return return true if the dvd removed successsfully
      *         return false if dvd is not in the list
      *
      */
    public Boolean deleteDvd(Dvd dvd) throws DvdException;

    /**
      * <P>
      * searchDvd search the particular Dvd which we want.
      * the Dvd is get from the Database by using DvdId
      * <p/>
      *
      * @param dvdId
      *        unique number which is used to find the particular Dvd
      *
      * @param status
      *        status which contains information about Dvd active status
      *
      * @return It returns dvdObject if found else returns null
      *
      */
    public Dvd searchDvd(int dvdId) throws DvdException;

    /**
      * <P>
      * updateDvd update specific value for the particular Dvd which we want.
      * the Dvd is get from the Database by using DvdId
      * <p/>
      * 
      * @param dvd 
      *        dvd object which is contains information about dvd
      *
      * @return It return true  if dvd updated else return false
      */
    public Boolean updateDvd(Dvd dvd) throws DvdException;

    /** 
      * <p>
      * getDvdStore which is List function to return the list
      * The list object cointains all available dvd's which is get from Database
      * <p/>
      *
      * @param status
      *        status which contains information about Dvd active status
      *
      * @return return all available dvds
      */
    public List<Dvd> getDvds(String status) throws DvdException;

     /**
      * <P>
      * restoreDvd restore the particular Dvd which we entered
      * the Dvd is get from the list by using DvdId
      * <p/>
      *
      * @param dvd 
      *        dvd object which is contains information about dvd
      *
      * @return It retuns true if dvd restored else return false
      */
    public Boolean restoreDvd(Dvd dvd) throws DvdException;

}
