package com.ideas2it.dvdstore.utils;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import com.ideas2it.dvdstore.common.DvdConstants;

/**
  * <p>
  * DvdStoreUtils  class is used to valid user input in different ways w
  * We can change the valid format depend on the corresponding requirement
  * </p>
  *
  * @author Muniraj M
  *
  */
public class DvdStoreUtils {

    /**
      * <p>
      * isValidUserInput function is used to valid the input which contains 
      * only alphabets and numbers
      * </p>
      * 
      * @param input 
      *        which is  input given by user
      *
      * @return It return true if input is valid else return false
      *
      */
    public static Boolean isValidUserInput(String input) {
        return (Pattern.matches("[a-zA-Z0-9\\s]+", input));
    }

    /**
      * isValidDvdRating is used to validate the rating which should be 0-5 
      * limit
      *
      * @param rating 
      *        which is  user input given by user
      * @return It return true if input is valid else return false
      */
    public static Boolean isValidDvdRating(float rating) {
        return (rating > DvdConstants.MAX_RATING || 
                DvdConstants.MIN_RATING > rating);
    }

   /**
     * isValidNumber is used to validate number that should vbe in positive
     *  number
     * 
     * @param input 
     *        which is  input given by user
     *
     * @return It return true if input is valid else return false
     */
    public static Boolean isValidNumber(int input) {
        return (input < 0 );
    }

    /**
      * isValidString function is used to valid the input which contains 
      * only alphabets that should be single word
      *
      * @param input 
      *        which is  input given by user
      *
      * @return It return true if input is valid else return false
      */
    public static Boolean isValidString(String input) {
        return (Pattern.matches("[a-zA-Z]+", input));
    }

     /**
      * isValidInput function is used to valid the input which contains 
      * only alphabets that should be muliple words
      *
      * @param input 
      *        which is input given by user
      *
      * @return It return true if input is valid else return false
      */
    public static Boolean isValidInput(String input) {
        return (Pattern.matches("[a-zA-Z\\s]+", input));
    }

     /**
      * isValidMobileNumber function is used to valid the input which contains 
      * only numbers that should be 10 digits 
      *
      * @param input 
      *        which is  input given by user
      *
      * @return It return true if input is valid else return false
      */
    public static Boolean isValidMobileNumber(String number) {
        return (Pattern.matches("[6789]{1}+[0-9]{9}", number));
    }
     
     /**
      * isValidMailId function is used to valid the input which should be
      * something@something.com
      *
      * @param input 
      *        which is  input given by user
      *
      * @return It return true if input is valid else return false
      *
     public static Boolean isValidMailId(String mailId) {
        return  (Pattern.matches("[a-zA-Z0-9]++@+[a-zA-Z0-9]++.[a-zA-Z]+{2,4}",
            mailId) || Pattern.matches(
            "[a-zA-Z0-9]++@+[a-zA-Z0-9]++.[a-zA-Z]+{2,4}+.[a-zA-Z]+{2,4}", 
 ([a-zA-Z0-9]+[@]{1}[a-zA-Z0-9]+[.]{1}[a-zA-Z]{2,4}[.]{1}[a-zA-Z]{2,4})
            mailId));
    }([a-zA-Z0-9]+[@]{1}[a-zA-Z0-9]+[.]{1}[a-zA-Z]{2,4})
    
    /**
      * isValidAddress function is used to valid the input which contains 
      * only alphabets and characters
      *
      * @param input 
      *        which is input given by user
      *
      * @return It return true if input is valid else return false
      */
    public static Boolean isValidAddress(String input) {
        return (Pattern.matches("[a-zA-Z0-9/,-.&\\s]+", input));
    }
}

