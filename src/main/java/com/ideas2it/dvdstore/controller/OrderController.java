package com.ideas2it.dvdstore.controller;
 
import java.util.List;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

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
import com.ideas2it.dvdstore.exception.DvdException;
import com.ideas2it.dvdstore.model.Dvd;
import com.ideas2it.dvdstore.model.Orders;
import com.ideas2it.dvdstore.service.OrderService;
import com.ideas2it.dvdstore.service.impl.OrderServiceImpl;


/**
  * <p>
  * OrderController class performes to get All order details and order with  
  * corresponding customer details 
  * <p/>
  * 
  * author Muniraj M
  */
@Controller 
public class OrderController extends HttpServlet {

     private OrderService orderService = new OrderServiceImpl();

    /**
      * <p>
      * viewOrders is a  function which is return the List 
      * List contains all available Order details 
      * <p/>
      *
      * @return It returns ModelAndView
      */
    @RequestMapping(value="/viewOrders", method = RequestMethod.POST)  
    public ModelAndView viewOrders() { 
        try {
            return new ModelAndView("ViewOrders", "orders", orderService.getOrders());
        } catch (DvdException e) {
            return new ModelAndView(DvdConstants.LABEL_ERROR_JSP, DvdConstants.LABEL_MESSAGE, 
                e.getMessage());
        }
    }
    
}

