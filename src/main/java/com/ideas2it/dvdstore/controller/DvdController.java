package com.ideas2it.dvdstore.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
 
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.Scanner;

import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.ModelAttribute;  
import org.springframework.web.bind.annotation.PathVariable;  
import org.springframework.web.bind.annotation.RequestMapping;  
import org.springframework.web.bind.annotation.RequestMethod;  
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView; 
import org.springframework.ui.ModelMap; 
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.ideas2it.dvdstore.controller.CategoryController;
import com.ideas2it.dvdstore.controller.UserController;
import com.ideas2it.dvdstore.common.DvdConstants;
import com.ideas2it.dvdstore.exception.CategoryException;
import com.ideas2it.dvdstore.exception.DvdException;
import com.ideas2it.dvdstore.logger.DvdLogger;
import com.ideas2it.dvdstore.model.Dvd;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.service.CategoryService;
import com.ideas2it.dvdstore.service.DvdService;
import com.ideas2it.dvdstore.service.impl.CategoryServiceImpl;
import com.ideas2it.dvdstore.service.impl.DvdServiceImpl;
import com.ideas2it.dvdstore.utils.DateUtils;
import com.ideas2it.dvdstore.utils.DvdStoreUtils;





/**
  * <p>
  * DvdController class performs to Create the new Dvd , Update existing Dvd
  * Display the availabale Dvds ,Search a particular dvd and Delete 
  * patricular Dvd
  * <p/>
  * 
  * author Muniraj M
  */
@Controller  
public class DvdController  extends HttpServlet {
  
    private CategoryController categoryController= new CategoryController();
    private DvdService dvdService = new DvdServiceImpl();
    private CategoryService categoryService = new CategoryServiceImpl();
    private DvdLogger dvdLogger = new DvdLogger();

   
    @RequestMapping(value="/dvd", method = RequestMethod.POST)  
    public ModelAndView dvd() {  
        return new ModelAndView(DvdConstants.LABEL_DVDSTORE_JSP, 
            DvdConstants.LABEL_ACTION, DvdConstants.LABEL_DVD);  
    }  

    @RequestMapping(value="/admin", method = RequestMethod.POST)  
    public ModelAndView admin() {  
        return new ModelAndView(DvdConstants.LABEL_DVDSTORE_JSP, 
            DvdConstants.LABEL_ACTION, DvdConstants.LABEL_ADMIN);  
    }  

    
     /** 
      * <p>
      * CreateDvd function used to create new Dvd and fetch the details of  dvd
      * The fetched details are Dvd Name, Language, Category, Price,
      * Rating and Release Date
      * It intimate user if the same Dvd is already exist 
      * Otherwise it intimates that the create was done Successful
      * <p/>
      *
      * @param model used to pass values to render a view.
      *
      */
    @RequestMapping(value="/create", method = RequestMethod.POST)  
    public String create(ModelMap model){  
        List<Category> categories = new ArrayList<Category>();
        try {
        categories = dvdService.getCategories(DvdConstants.LABEL_YES);
        model.addAttribute(DvdConstants.LABEL_VALUE, DvdConstants.LABEL_ADD);
        model.addAttribute(DvdConstants.LABEL_DVD, new Dvd());
        model.addAttribute(DvdConstants.LABEL_CATEGORIES, dvdService.getCategories(
            DvdConstants.LABEL_YES));
        } catch (CategoryException e) {
            model.addAttribute(DvdConstants.LABEL_MESSAGE, e.getMessage());
            return DvdConstants.LABEL_ERROR_JSP;
        }
        return DvdConstants.LABEL_CREATEDVD_JSP;
    }  

     /** 
      * <p>
      * CreateDvd function used to create new Dvd and fetch the details of  dvd
      * The fetched details are Dvd Name, Language, Category, Price,
      * Rating and Release Date
      * It intimate user if the same Dvd is already exist 
      * Otherwise it intimates that the create was done Successful
      * <p/>
      *
      * @param request which is send from client to trigger an action on the server
      */
    @RequestMapping(value="/createDvd", method = RequestMethod.POST)  
    public ModelAndView createDvd(@ModelAttribute(DvdConstants.LABEL_DVD) Dvd 
            dvd, BindingResult bindingResult,HttpServletRequest request, ModelMap model) {  
        try {
             String[] values=request.getParameterValues(DvdConstants.LABEL_ID);
             List<Category> categories = new ArrayList<Category>();
            for(int i=0;i<values.length;i++) {
                Category category = new Category();
                category.setId(Integer.parseInt(values[i]));
                categories.add(category);
            }
            dvd.setCategories(categories);
            if (dvdService.createDvd(dvd)) {
                model.addAttribute(DvdConstants.LABEL_MESSAGE,
                    DvdConstants.MESSAGE_DVD_CREATE + dvd.getId());
                return dvd();
            }
        } catch (DvdException e) {
            return new ModelAndView(DvdConstants.LABEL_ERROR_JSP, 
                DvdConstants.LABEL_MESSAGE, e.getMessage() + "");
        }
        return null;
    }  
   

    /**
      * <p>
      * display function performs to get all available Dvd's form the list
      * Available dvd's are in active 
      * </p>
      */
    @RequestMapping(value="/display", method = RequestMethod.GET)  
    public ModelAndView display() {  
         List<Dvd> dvds = new ArrayList<Dvd>();
         try {
        
         dvds =  dvdService.getDvds(DvdConstants.LABEL_YES);
         }  catch (DvdException e) {
             return new ModelAndView(DvdConstants.LABEL_ERROR_JSP, 
               DvdConstants.LABEL_MESSAGE, e.getMessage());
         }
        return new ModelAndView(DvdConstants.LABEL_DISPLAYDVD_JSP,
            DvdConstants.LABEL_DVDS,dvds);
	
    }  

    /**
      * <p>
      * delete function performs to remove the particular Dvd from the list
      * Dvd code used as a reference to delete particular Dvd
      * If the code not exist in the list it intimates user Dvd code not  exist
      * <p/>
      *
      * @param request which is send from client to trigger an action on the server
      */
    @RequestMapping(value="/delete", method = RequestMethod.POST)  
     public ModelAndView delete(HttpServletRequest request, ModelMap model) {  
        Integer dvdId = Integer.parseInt(request.getParameter(
            DvdConstants.LABEL_DVD_ID));
        try {
            Dvd dvd = dvdService.searchDvd(dvdId);
            if (dvdService.deleteDvd(dvd)) {
                model.addAttribute(DvdConstants.LABEL_MESSAGE, 
                    DvdConstants.MESSAGE_DVD_DELETE + dvdId);
            }
        } catch (DvdException e) {
            return new ModelAndView(DvdConstants.LABEL_ERROR_JSP, 
                DvdConstants.LABEL_MESSAGE, e.getMessage());
        }
        return display();
    } 

    /**
      * <p>
      * restore used to restore the dvd the particular dvd. 
      * Dvd Id values is get from user
      * Here dvd will be changed inactive status into active
      * </p>
      * 
      * @param request which is send from client to trigger an action on the server
      */
    @RequestMapping(value="/restore", method = RequestMethod.POST)  
     public ModelAndView restore(HttpServletRequest request, ModelMap model) {  
        Integer dvdId = Integer.parseInt(request.getParameter(
            DvdConstants.LABEL_DVD_ID));
        try {
            Dvd dvd = dvdService.searchDvd(dvdId);
            if (dvdService.restoreDvd(dvd)) {
                model.addAttribute(DvdConstants.LABEL_MESSAGE, 
                    DvdConstants.MESSAGE_DVD_RESTORE + dvdId);
            }
            
        } catch (DvdException e) {
             return new ModelAndView(DvdConstants.LABEL_ERROR_JSP, 
                DvdConstants.LABEL_MESSAGE, e.getMessage());
        }
        return restoreDvds();
    } 

    /**
      * <p>
      * restoreDvds used to restore the dvd
      * Here dvd will be changed inactive status into active
      * </p>
      */
    @RequestMapping(value="/restoreDvds", method = RequestMethod.POST)  
    public ModelAndView restoreDvds() {  
        List<Dvd> dvds = new ArrayList<Dvd>();
         try {
        
         dvds =  dvdService.getDvds(DvdConstants.LABEL_NO);
         }  catch (DvdException e) {
            return new ModelAndView(DvdConstants.LABEL_ERROR_JSP, 
                DvdConstants.LABEL_MESSAGE, e.getMessage());
         }
        return new ModelAndView(DvdConstants.LABEL_RESTOREDVD_JSP,
            DvdConstants.LABEL_DVDS,dvds);  
    } 

     /** 
      * <p>
      * updateDvd performs to update existing records 
      * It update Dvd's Name, Language, Category, Price, Rating and Release date
      * Dvd Id is used for which Dvd to be updated
      * <p/>
      *
      * @param request which is send from client to trigger an action on the server
      */
     @RequestMapping(value="/updateDvd", method = RequestMethod.POST)  
     public String updateDvd(ModelMap model, HttpServletRequest request) {  
        Integer dvdId = 
            Integer.parseInt(request.getParameter(DvdConstants.LABEL_DVD_ID));
        try {
            Dvd dvd = dvdService.searchDvd(dvdId);
            model.addAttribute(DvdConstants.LABEL_DVD, dvd);
            model.addAttribute(DvdConstants.LABEL_DVD, dvd);
            model.addAttribute(DvdConstants.LABEL_CATEGORIES, dvdService.getCategories(
                DvdConstants.LABEL_YES));
        } catch (DvdException e) {
            model.addAttribute(DvdConstants.LABEL_MESSAGE, e.getMessage());
            return DvdConstants.LABEL_ERROR_JSP;
        } catch (CategoryException e) {
            model.addAttribute(DvdConstants.LABEL_MESSAGE, e.getMessage());
            return DvdConstants.LABEL_ERROR_JSP;
        }
        return DvdConstants.LABEL_UPDATEDVD_JSP;
    } 
    
    /** 
      * <p>
      * update performs to update existing records 
      * It update Dvd's Name, Language, Category, Price, Rating and Release date
      * These values get from user
      * <p/>
      *
      * @param request which is send from client to trigger an action on the server
      */
    @RequestMapping(value="/update", method = RequestMethod.POST)  
    public ModelAndView update(@ModelAttribute(DvdConstants.LABEL_DVD) Dvd dvd, 
            BindingResult  bindingResult,HttpServletRequest request) {  
        try {
            
            String[] values = 
                request.getParameterValues(DvdConstants.LABEL_CATEGORY_ID);
            List<Category> categories = new ArrayList<Category>();
            for(int i=0;i<values.length;i++) {
                Category category = new Category();
                category.setId(Integer.parseInt(values[i]));
                categories.add(category);
            }
            dvd.setCategories(categories);
            dvd.setStatus(DvdConstants.LABEL_YES);
            dvdService.updateDvd(dvd);
        } catch (DvdException e) {
            return new ModelAndView(DvdConstants.LABEL_ERROR_JSP, 
                DvdConstants.LABEL_MESSAGE, e.getMessage());
        }
        return display();
    }  

    /**
      * <p>
      * searchDvd  performs to search particular Dvd from the list
      * Dvd code is used as reference which Dvd to be displayed
      * If the code not exist in the list it intimates user Dvd code not  exist
      * <p/>
      *
      * @param request which is send from client to trigger an action on the server
      */
     @RequestMapping(value="/search", method = RequestMethod.POST)  
     public ModelAndView search(HttpServletRequest request) {  
        Integer dvdId = 
            Integer.parseInt(request.getParameter(DvdConstants.LABEL_DVD_ID));
        List<Dvd> dvds = new ArrayList<Dvd>();
        try {
            Dvd dvd = dvdService.searchDvd(dvdId);
            dvds.add(dvd);
        } catch (DvdException e) {
            return new ModelAndView(DvdConstants.LABEL_ERROR_JSP,
                DvdConstants.LABEL_MESSAGE, e.getMessage());
        }
        return new ModelAndView(DvdConstants.LABEL_DISPLAYDVD_JSP,DvdConstants.LABEL_DVDS,dvds);
    } 

}
