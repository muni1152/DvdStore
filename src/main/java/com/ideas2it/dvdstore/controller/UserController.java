package com.ideas2it.dvdstore.controller;

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
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.User;
import com.ideas2it.dvdstore.service.impl.CustomerServiceImpl;
import com.ideas2it.dvdstore.service.impl.UserServiceImpl;
import com.ideas2it.dvdstore.service.CustomerService;
import com.ideas2it.dvdstore.service.UserService;


@Controller 
public class UserController extends HttpServlet {

    private UserService userService = new UserServiceImpl();
    

    @RequestMapping(value="/", method = RequestMethod.GET)  
    public ModelAndView DvdStore() {
        return new ModelAndView("index");
    }

    /** 
      * <p>
      * signIn function which is used to check the customer account is created 
      * or not
      * If the customer account is valid through login the user can perform
      * differnt operations
      * depend on the input the specific operation performed
      * </p>
      */
  
    @RequestMapping(value="/signIn", method = RequestMethod.POST)  
    public ModelAndView signIn(ModelMap model, HttpServletRequest request) {
        try {  
        User user = new User();
        user.setUserName(request.getParameter("mobileNumber"));
        user.setPassword(request.getParameter("password"));
        String role = request.getParameter("role");
        user.setRole(role);
        user = userService.signIn(user);
        if ( null != user) {
            HttpSession session  = request.getSession(false);
            session.setAttribute("userId", user.getId());
            if (role.equals("CUSTOMER")) {
                Customer customer = userService.searchCustomerByMobileNumber
                    (user.getUserName());
                session.setAttribute("id",customer.getId());
                return new ModelAndView("DisplayCustomer", "customer", 
                    customer);  
            } else {
                return new ModelAndView(DvdConstants.LABEL_DVDSTORE_JSP, DvdConstants.LABEL_ACTION, "admin");  
            }
        } else {
            return new ModelAndView(DvdConstants.LABEL_INDEX_JSP, 
               DvdConstants.LABEL_MESSAGE, "Sorry UserName or Password Error!");  
       }
       } catch (DvdException e) {
            return new ModelAndView(DvdConstants.LABEL_ERROR_JSP, 
                 DvdConstants.LABEL_MESSAGE, "Something error..try again");  
       }
    }

    /** Logout function which is used to switched to account off
      * At the logout stage session would be invalidated so that unauthorised 
      * access will be frestricted by checking condidtion
      */
    @RequestMapping(value="/logOut", method = RequestMethod.POST)  
    public ModelAndView logOut(HttpServletRequest request)
            throws ServletException, IOException {
        try { 
            HttpSession session  = request.getSession(false); 
            session.invalidate();  
        } catch (Exception e) {
            return new ModelAndView(DvdConstants.LABEL_ERROR_JSP, 
                 DvdConstants.LABEL_MESSAGE, "Something error..try again");  
        }
        return new ModelAndView(DvdConstants.LABEL_INDEX_JSP, 
            DvdConstants.LABEL_MESSAGE, "Your Account succesfully logged out");
    }
}
