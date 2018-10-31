package com.ideas2it.dvdstore.exception;

/**
  * CategoryException class which is act as custom excepiton used to handle errors
  *
  * @author Muniraj M
  *
  */
public class CategoryException extends Exception {

    /**
      * DvdException constructor used initialise object without creating object
      *
      * @param message
      *        which contains default information
      *
      */
    public CategoryException(String message) {
        super(message);
    }
}
