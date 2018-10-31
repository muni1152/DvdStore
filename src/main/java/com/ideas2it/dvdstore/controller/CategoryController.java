package com.ideas2it.dvdstore.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
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

import com.ideas2it.dvdstore.common.DvdConstants;
import com.ideas2it.dvdstore.exception.CategoryException;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.model.Dvd;
import com.ideas2it.dvdstore.exception.DvdException;
import com.ideas2it.dvdstore.service.CategoryService;
import com.ideas2it.dvdstore.utils.DateUtils;
import com.ideas2it.dvdstore.utils.DvdStoreUtils;
import com.ideas2it.dvdstore.service.impl.CategoryServiceImpl;



/**
  * <p>
  * CategoryControlletr class performes to Create the new Category ,Update 
  * existing Category, Display the availabale Categories ,Search a particular 
  * Category and Delete patricular Category
  * <p/>
  * 
  * author Muniraj M
  */
@Controller  
public class CategoryController extends HttpServlet {

    private CategoryService categoryService = new CategoryServiceImpl();

    /** 
      * <p>
      * category function is used to redirect the category page 
      * This page consists category related operations
      * </p>
      *
      */
    @RequestMapping(value="/category", method = RequestMethod.POST)  
    public ModelAndView category() { 
        return new ModelAndView(DvdConstants.LABEL_DVDSTORE_JSP,
            DvdConstants.LABEL_ACTION,DvdConstants.LABEL_CATEGORY);  
    }  

    @RequestMapping(value="/createCategory", method = RequestMethod.POST)  
    public String createCategory(ModelMap model){  

        model.addAttribute(DvdConstants.LABEL_ACTION, DvdConstants.LABEL_ADD);
        model.addAttribute(DvdConstants.LABEL_CATEGORY, new Category());
       
        return DvdConstants.LABEL_CREATE_CATEGORY_JSP;
    }  

    /** 
      * <p>
      * createCategory to create new Category and fetch the details of the 
      * Category
      * It intimate user if the same Category is already exist in the list
      * Otherwise it shows that the create was done Successful
      * <p/
      */
    @RequestMapping(value="/addCategory", method = RequestMethod.POST)  
    public ModelAndView addCategory(@ModelAttribute(DvdConstants.LABEL_CATEGORY) 
            Category category, BindingResult bindingResult, ModelMap model, 
            HttpServletRequest request) {  
        try {
            if (categoryService.createCategory(category)) {
                model.addAttribute(DvdConstants.LABEL_MESSAGE, 
                    DvdConstants.MESSAGE_CATEGORY + category.getId());
            }
        } catch (CategoryException e) {
            return new ModelAndView(DvdConstants.LABEL_ERROR_JSP, 
                DvdConstants.LABEL_MESSAGE, e.getMessage());
        }
        return new ModelAndView(DvdConstants.LABEL_DVDSTORE_JSP);
    }  

    /**
      * <p>
      * displayCategory performs to display available categories form the list
      * The List value is get from Database
      * </p>
      */
    @RequestMapping(value="/displayCategory", method = RequestMethod.POST)  
    public String displayCategory(ModelMap model) {  
        List<Category> categories = new ArrayList<Category>();
        try {
            categories = categoryService.getCategories(DvdConstants.LABEL_YES); 
            model.addAttribute(DvdConstants.LABEL_OPERATION, 
                DvdConstants.LABEL_DISPLAY);
            model.addAttribute(DvdConstants.LABEL_CATEGORIES,categories);
        } catch (CategoryException e) {
            model.addAttribute(DvdConstants.LABEL_MESSAGE, e.getMessage());
            return DvdConstants.LABEL_ERROR_JSP;
        }
        return DvdConstants.LABEL_DISPLAY_CATEGORY_JSP;
    } 

    /**
      * <p>
      * deleteCategory used to remove the particular Category and remove 
      * category for specific dvd from the list
      * category id used as a reference to delete particular category
      * <p/>
      */
    @RequestMapping(value="/deleteCategory", method = RequestMethod.POST)  
     public String deleteCategory(ModelMap model, HttpServletRequest request) {  
        try {
            Integer categoryId = Integer.parseInt(request.getParameter(
                DvdConstants.LABEL_ID));
            Category category = categoryService.getCategory(categoryId);
            if (categoryService.deleteCategory(category)) {
                model.addAttribute(DvdConstants.LABEL_MESSAGE, 
                    DvdConstants.MESSAGE_CATEGORY_DELETE + categoryId + "");
            }
        } catch (CategoryException e) {
            model.addAttribute(DvdConstants.LABEL_MESSAGE, e.getMessage());
            return DvdConstants.LABEL_ERROR_JSP;
        }
        return displayCategory(model);
    } 
    
    /**
      * <p>
      * restoreCategory used to restore the Category
      * Here the details of deleted categories sent to the page 
      * </p>
      */
    @RequestMapping(value="/restoreCategoryForm", method = RequestMethod.POST)  
    public String restoreCategoryForm(ModelMap model) {  
        List<Category> categories = new ArrayList<Category>();
        try {
            categories = categoryService.getCategories(DvdConstants.LABEL_NO); 
            model.addAttribute(DvdConstants.LABEL_OPERATION, DvdConstants.LABEL_RESTORE);
            model.addAttribute(DvdConstants.LABEL_CATEGORIES,categories);
        } catch (CategoryException e) {
            model.addAttribute(DvdConstants.LABEL_MESSAGE, e.getMessage());
            return DvdConstants.LABEL_ERROR_JSP;
        }
        return DvdConstants.LABEL_DISPLAY_CATEGORY_JSP;
    } 

     /**
      * <p>
      * restoreCategory used to restore the Category
      * Here Category will be changed inactive status into active
      * </p>
      */
    @RequestMapping(value="/restoreCategory", method = RequestMethod.POST)  
     public String restoreCategory(ModelMap model, HttpServletRequest request) {  
        try {
            Integer categoryId = Integer.parseInt(request.getParameter(
                DvdConstants.LABEL_ID));
            Category category = categoryService.getCategory(categoryId);
            categoryService.restoreCategory(category);
        } catch (CategoryException e) {
            model.addAttribute(DvdConstants.LABEL_MESSAGE, e.getMessage());
            return DvdConstants.LABEL_ERROR_JSP;
        }
        return restoreCategoryForm(model);
    } 

     /**
      * <p>
      * changeCategory function is used to change the category name
      * The changed name does not similar to existing categories
      * </p>
      */
    @RequestMapping(value="/updateCategory", method = RequestMethod.POST)  
     public String updateCategory(ModelMap model, HttpServletRequest request) {  
        try {
            Integer categoryId = Integer.parseInt(request.getParameter(
                DvdConstants.LABEL_ID));
            model.addAttribute(DvdConstants.LABEL_ACTION, 
                DvdConstants.LABEL_UPDATE);
            model.addAttribute(DvdConstants.LABEL_CATEGORY, 
                categoryService.getCategory(categoryId));
        } catch (CategoryException e) {
            model.addAttribute(DvdConstants.LABEL_MESSAGE, e.getMessage());
            return DvdConstants.LABEL_ERROR_JSP;
        }
        return DvdConstants.LABEL_CREATE_CATEGORY_JSP;
    } 

     /**
      * <p>
      * updateCategoryForm function is used to update the category name
      * The form details which is get values from user are saved at the database
      * </p>
      */
    @RequestMapping(value="/updateCategoryForm", method = RequestMethod.POST)  
    public String updateCategoryForm(@ModelAttribute(DvdConstants.LABEL_CATEGORY) 
            Category  category, BindingResult bindingResult, ModelMap model) {  
        try {
            if (categoryService.updateCategory(category)) {
                  model.addAttribute(DvdConstants.LABEL_MESSAGE,
                    DvdConstants.MESSAGE_CATEGORY_UPDATE + category.getId());
            }
        } catch (CategoryException e) {
            model.addAttribute(DvdConstants.LABEL_MESSAGE, e.getMessage());
            return DvdConstants.LABEL_ERROR_JSP;
        }
        return displayCategory(model);
    } 

      /**
      * <p>
      * viewDvdsByCategory  performs to search particular Category's dvds
      *  from the list
      * Category id is used as reference which Category's dvds to be displayed
      * <p/>
      */
     @RequestMapping(value="/viewDvdsByCategory", method = RequestMethod.POST)  
     public String viewDvdsByCategory(ModelMap model, HttpServletRequest request) {  
        try {
            Integer categoryId = Integer.parseInt(request.getParameter(
                DvdConstants.LABEL_ID));
            Category category = categoryService.getCategory(categoryId);
            List<Dvd> dvds = new ArrayList<Dvd>();
            model.addAttribute(DvdConstants.LABEL_DVDS, category.getDvds());
            model.addAttribute(DvdConstants.LABEL_ACTION, DvdConstants.LABEL_CATEGORY);
            model.addAttribute(DvdConstants.LABEL_CATEGORY, category);
        } catch (CategoryException e) {
            model.addAttribute(DvdConstants.LABEL_MESSAGE, e.getMessage());
            return DvdConstants.LABEL_ERROR_JSP;
        }
        return DvdConstants.LABEL_DISPLAYDVD_JSP;
    }  

     /**
      * <p>
      * removeCategoryToDvd used to remove the particular Category for specific  
      * Dvd
      * category id used as a reference to delete particular category
      * <p/>
      */
    @RequestMapping(value="/removeCategoryToDvd", method = RequestMethod.POST)  
     public String removeCategoryToDvd(ModelMap model, HttpServletRequest request) {  
        try {

            Integer dvdId =  
                Integer.parseInt(request.getParameter(DvdConstants.LABEL_ID));
            Integer categoryId =  Integer.parseInt(request.getParameter(
                DvdConstants.LABEL_CATEGORY_ID));
            Category category = categoryService.getCategory(categoryId);
            List<Dvd> dvds = category.getDvds();
            for (Dvd dvd : dvds) {
                int id = dvd.getId();
                if (id == dvdId) {
                    dvds.remove(dvd);
                    break;
                }
            }
            category.setDvds(dvds);
            categoryService.updateCategory(category);

            model.addAttribute(DvdConstants.LABEL_DVDS,category.getDvds());
            model.addAttribute(DvdConstants.LABEL_ACTION,DvdConstants.LABEL_CATEGORY);
        } catch (CategoryException e) {
            model.addAttribute(DvdConstants.LABEL_MESSAGE, e.getMessage());
            return DvdConstants.LABEL_ERROR_JSP;
        }
        return DvdConstants.LABEL_DISPLAYDVD_JSP;
    }  
}

    
