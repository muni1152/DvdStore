package com.ideas2it.dvdstore.exception;

/**
  * DvdException class which is act as custom excepiton used to handle errors
  *
  * @author Muniraj M
  *
  */
public class DvdException extends Exception {

    /**
      * DvdException constructor used initialise object without creating object
      *
      * @param message
      *        which contains default information
      *
      */
    public DvdException(String message) {
        super(message);
    }
}
    
