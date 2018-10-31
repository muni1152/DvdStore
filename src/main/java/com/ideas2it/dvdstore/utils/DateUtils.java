package com.ideas2it.dvdstore.utils;

import java.time.LocalDate;
import java.time.Period;
import java.util.Date;
import java.util.Scanner;

import com.ideas2it.dvdstore.common.DvdConstants;

/**
  * DateUtils which is used to calculate date difference between present date 
  * and user input date which is  release date
  *
  * author Muniraj M
  */
public class DateUtils {

    /** 
      * <p>
      * get the date as a string and display in the form of string
      * DateTimeFormatter is the type of format to the date entered.
      * we may change the type of format for our convenient
      * <p/>
      *
      * @param date
      *        date contains information about dvd released date
      *
      * @return time duration which contains the date information when 
      * dvd release
      *
      */
     public static Period dateDifference(LocalDate date) {
        LocalDate todayDate = LocalDate.now();
        Period difference = Period.between(date, todayDate);
        return difference;
    }

    /**
      * <p>
      * resultToDateDifference function is used to return the result of date
      * difference between  two dates
      * </p>
      * 
      * @param date
      *        date contains information about dvd released date
      *
      * @return It returns dateDiffernce
      *
      */
    public static String calculateDateDifference(LocalDate date) {
        Period difference = dateDifference(date); 
        if (DvdConstants.MIN_DAYS < difference.getYears()) {
            return (difference.getYears() + DvdConstants.MESSAGE_YEARS);
        } else if(DvdConstants.MIN_DAYS < difference.getMonths()) { 
            return (difference.getMonths() + DvdConstants.MESSAGE_MONTHS);
        } else {
            return (difference.getDays() + DvdConstants.MESSAGE_DAYS);
        }
    }
    
}
