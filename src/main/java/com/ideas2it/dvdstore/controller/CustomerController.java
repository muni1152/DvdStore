package com.ideas2it.dvdstore.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.time.LocalDate;
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
import com.ideas2it.dvdstore.exception.DvdException;
import com.ideas2it.dvdstore.model.Address;
import com.ideas2it.dvdstore.model.Category;
import com.ideas2it.dvdstore.model.Customer;
import com.ideas2it.dvdstore.model.Dvd;
import com.ideas2it.dvdstore.model.Orders;
import com.ideas2it.dvdstore.model.User;
import com.ideas2it.dvdstore.service.CustomerService;
import com.ideas2it.dvdstore.service.impl.CustomerServiceImpl;
import com.ideas2it.dvdstore.service.impl.UserServiceImpl;
import com.ideas2it.dvdstore.service.UserService;
import com.ideas2it.dvdstore.utils.DvdStoreUtils;



/**
  * <p>
  * CustomerController class performs    to Create the new Customer Account , 
  * Update existing account ,purchase Dvd ,view order details and cancel order
  * <p/>
  * 
  * author Muniraj M
  */
@Controller  
public class CustomerController extends HttpServlet {

    private CustomerService customerService = new CustomerServiceImpl();
    
    /**
      * index is a function which is redirect to the index.jsp page
      */
    @RequestMapping(value="/index", method = RequestMethod.POST)  
    public String index(ModelMap model) { 
        return (DvdConstants.LABEL_INDEX_JSP);  
    }

    
    /**
      * signUp is a function which is redirect to the signUp page 
      * Here empty customer object is send as a model attribute
      */
    @RequestMapping(value="/signUp", method = RequestMethod.POST)  
    public ModelAndView signUp(ModelMap model) { 
        return new ModelAndView(DvdConstants.LABEL_CREATE_CUSTOMER_JSP,
            DvdConstants.LABEL_CUSTOMER,new Customer());
    }

     /** 
      * <p>
      * createCustomer to create new Customer account  and fetch the details of 
      * the Customer.The fetched details are Customer Name, mobile number,mail 
      * id and address
      * It intimate user if the same Customer datais already exist 
      * Otherwise it shows that the create was done Successful
      * <p/>
      */
    @RequestMapping(value="/createCustomer", method = RequestMethod.POST)  
    public ModelAndView createCustomer(@ModelAttribute(DvdConstants.LABEL_CUSTOMER)  
        Customer customer, BindingResult bindingResult,ModelMap model) {  
        try {
             User user = customer.getUser();
             user.setRole(DvdConstants.CUSTOMER);
             customer.setUser(user);
            if (customerService.createCustomer(customer)) {
                model.addAttribute(DvdConstants.LABEL_MESSAGE, 
                    DvdConstants.MESSAGE_CUSTOMER_CREATE);
            }
        } catch (DvdException e) {
            return new ModelAndView(DvdConstants.LABEL_ERROR_JSP,  
                DvdConstants.LABEL_MESSAGE, e.getMessage());
        }
        return new ModelAndView(DvdConstants.LABEL_INDEX_JSP);
    } 

    /** 
      * <p>
      * updateCustomer to update existing Customer account  
      * The update details are Customer Name, mobile number,mail 
      * id and address
      * It intimate user if the same Customer data is already exist 
      * Otherwise it shows that the update was done Successful
      * <p/>
      *
      * @param request which is send from client to trigger an action on the server
      */
     @RequestMapping(value="/updateCustomer", method = RequestMethod.POST)  
     public ModelAndView updateCustomer(HttpServletRequest request) {  
        Integer customerId = Integer.parseInt(request.getParameter
            (DvdConstants.LABEL_CUSTOMER_ID));
        try {
            
            return new ModelAndView(DvdConstants.LABEL_UPDATE_CUSTOMER_JSP,
                DvdConstants.LABEL_CUSTOMER, customerService.searchCustomer(
                 customerId));
        } catch (DvdException e) {
            return new ModelAndView(DvdConstants.LABEL_ERROR_JSP, 
                DvdConstants.LABEL_MESSAGE, e.getMessage());
        }
    } 

     /** 
      * <p>
      * updateCustomerForm to update existing Customer account  and get values
      * from user
      * <p/>
      *
      * @param request which is send from client to trigger an action on the server
      */
    @RequestMapping(value="/updateCustomerForm", method = RequestMethod.POST)  
    public String updateCustomerForm(@ModelAttribute(DvdConstants.LABEL_CUSTOMER) Customer 
            customer, BindingResult bindingResult, HttpServletRequest request, 
            ModelMap model) {  
        try {
            Customer customerData = customerService.searchCustomer(customer.getId());
            customer.setAddresses(customerData.getAddresses());
            customer.setOrders(customerData.getOrders());
            if (customerService.updateCustomer(customer)) {
                model.addAttribute(DvdConstants.LABEL_MESSAGE,"Your Account Succesfully updated");
                model.addAttribute(DvdConstants.LABEL_CUSTOMER,customer);
            }
           
        } catch (DvdException e) {
            model.addAttribute(DvdConstants.LABEL_MESSAGE, e.getMessage());
            return DvdConstants.LABEL_ERROR_JSP;
        }
        return DvdConstants.LABEL_DISPLAY_CUSTOMER_JSP;
    }  
    
    @RequestMapping(value="/addAddress", method = RequestMethod.POST)  
    public ModelAndView addAddress(ModelMap model) { 
        return new ModelAndView("Address","address",new Address());
    }
   
    
     /** 
      * <p>
      * addAddressForm to add  new address for customer and fetech the details 
      * of address
      * The details are addressLine,City, State, Country 
      * <p/>
      *
      * @param request which is send from client to trigger an action on the server
      */
   @RequestMapping(value="/addAddressForm", method = RequestMethod.POST)  
    public String addAddressForm(@ModelAttribute("address") Address address, 
            BindingResult bindingResult, ModelMap model, HttpServletRequest request) {  
        try {
            HttpSession session  = request.getSession(Boolean.FALSE);
            Customer customer = 
                customerService.searchCustomer((Integer) session.getAttribute(DvdConstants.LABEL_ID));   
            List<Address> addresses = customer.getAddresses();
            addresses.add(address);
          
            if (customerService.updateCustomer(customer)) {
                model.addAttribute(DvdConstants.LABEL_CUSTOMER,customer);
                model.addAttribute(DvdConstants.LABEL_MESSAGE,"New address created Succesfully");
            }
        } catch (DvdException e) {
            model.addAttribute(DvdConstants.LABEL_MESSAGE, e.getMessage());
            return DvdConstants.LABEL_ERROR_JSP;
        }
        return DvdConstants.LABEL_DISPLAY_CUSTOMER_JSP;
    } 

     
     /** 
      * <p>
      * removeAddress to delete existing Customer address Details 
      * The details are addressLine,City, State, Country 
      * <p/>
      *
      * @param request which is send from client to trigger an action on the server
      */
    @RequestMapping(value="/removeAddress", method = RequestMethod.POST)  
     public String removeAddress(HttpServletRequest request, ModelMap model) { 
 
        Integer addressId = Integer.parseInt(request.getParameter("addressId"));
        try {
            HttpSession session  = request.getSession(Boolean.FALSE);
            Customer customer = 
                customerService.searchCustomer((Integer) session.getAttribute(DvdConstants.LABEL_ID));
           List<Address> addresses = customer.getAddresses();
           for (Address address : addresses) {
                if (address.getId() == addressId) {
                    addresses.remove(address);
                    break;
                }
            }
           if (customerService.updateCustomer(customer)) {
                model.addAttribute(DvdConstants.LABEL_CUSTOMER,customer);
                model.addAttribute(DvdConstants.LABEL_MESSAGE,"Address removed successfullly " +
                    "for Address Id = " + addressId);
            }

        } catch (DvdException e) {
            model.addAttribute(DvdConstants.LABEL_MESSAGE, e.getMessage());
            return DvdConstants.LABEL_ERROR_JSP;
        }
        return DvdConstants.LABEL_DISPLAY_CUSTOMER_JSP;
    } 

    
    /** 
      * <p>
      * updateAddress to update existing Customer address Details 
      * The details are addressLine,City, State, Country
      * <p/>
      *
      * @param request which is send from client to trigger an action on the server
      */
    @RequestMapping(value="/updateAddress", method = RequestMethod.POST)  
    public ModelAndView updateAddress(ModelMap model, HttpServletRequest request) { 
        Integer addressId = Integer.parseInt(request.getParameter("addressId"));
        try {
        HttpSession session  = request.getSession(Boolean.FALSE);
        Customer customer = 
            customerService.searchCustomer((Integer) session.getAttribute(DvdConstants.LABEL_ID));
        List<Address> addresses = customer.getAddresses();
        for (Address address : addresses) {
            if (address.getId() == addressId) {
                model.addAttribute("address", address);
                model.addAttribute(DvdConstants.LABEL_ACTION,"updateAddress");
                break;
             }
        }
        } catch (DvdException e) {
            return new ModelAndView(DvdConstants.LABEL_ERROR_JSP, DvdConstants.LABEL_MESSAGE, 
                e.getMessage());
        }
        return new ModelAndView("Address");
    }

    /** 
      * <p>
      * updateAddressForm to update existing Customer address Details 
      * The details are addressLine,City, State, Country which is get from user
      * <p/>
      *
      * @param request which is send from client to trigger an action on the server
      */
    @RequestMapping(value="/updateAddressForm", method = RequestMethod.POST)  
    public String updateAddressForm(@ModelAttribute("address") Address address, 
            BindingResult bindingResult, ModelMap model, 
            HttpServletRequest request) {  
        try {
            HttpSession session  = request.getSession(Boolean.FALSE);
            Customer customer = 
                customerService.searchCustomer((Integer) session.getAttribute(DvdConstants.LABEL_ID));   
            List<Address> addresses = customer.getAddresses();
            for (Address addressData : addresses) {
                if (addressData.getId() == address.getId()) {
                    Integer index = addresses.indexOf(addressData);
                    addresses.set(index,address);
                    break;
                 }
            }
          
            if (customerService.updateCustomer(customer)) {
                model.addAttribute(DvdConstants.LABEL_CUSTOMER,customer);
                model.addAttribute(DvdConstants.LABEL_MESSAGE," Address updated Succesfully" + 
                    "for Address Id = " + address.getId());
            }
        } catch (DvdException e) {
            model.addAttribute(DvdConstants.LABEL_MESSAGE, e.getMessage());
            return DvdConstants.LABEL_ERROR_JSP;
        }
        return DvdConstants.LABEL_DISPLAY_CUSTOMER_JSP;
    } 
    
     /**
       * <p>
       * displayCustomer function which is used to display customer profile
       * It consists the details of customer
       * </p>
       *
       */
     @RequestMapping(value="/displayCustomer", method = RequestMethod.POST)  
     public ModelAndView displayCustomer(HttpServletRequest request) {  
        try {
            HttpSession session  = request.getSession(Boolean.FALSE);
            Integer customerId = (Integer) session.getAttribute(DvdConstants.LABEL_ID);
            return new ModelAndView(DvdConstants.LABEL_DISPLAY_CUSTOMER_JSP, DvdConstants.LABEL_CUSTOMER, 
                customerService.searchCustomer(customerId));
        } catch (DvdException e) {
            return new ModelAndView(DvdConstants.LABEL_ERROR_JSP, DvdConstants.LABEL_MESSAGE, 
                e.getMessage());
        }
    } 

    /**
      * <p>
      * purchaseDvd function which is used to purchase a new dvd which we want 
      * It intimates user by providing order Id which is used for reference
      * </p>
      */
    @RequestMapping(value="/purchaseDvd", method = RequestMethod.POST)  
    public String purchaseDvd(ModelMap model, HttpServletRequest request) { 
        try {
            HttpSession session  = request.getSession(Boolean.FALSE);
            model.addAttribute(DvdConstants.LABEL_DVDS, customerService.getDvds("Y"));
            model.addAttribute(DvdConstants.LABEL_CUSTOMER,customerService.searchCustomer(
                (Integer) session.getAttribute(DvdConstants.LABEL_ID)));
        } catch (DvdException e) {
            model.addAttribute(DvdConstants.LABEL_MESSAGE, e.getMessage());
            return DvdConstants.LABEL_ERROR_JSP;
        }
        return "PurchaseDvd";
    }

    /**
      * <p>
      * placeOrder function is used to confirm the order and provide order Id 
      * which is used for reference
      * </p>
      */
    @RequestMapping(value="/placeOrder", method = RequestMethod.POST)  
    public String placeOrder(ModelMap model, HttpServletRequest request) { 
        try {
            Orders order = new Orders();
            String[] values=request.getParameterValues("dvdId");
            List<Dvd> dvds = new ArrayList<Dvd>();
            for(int i=0;i<values.length;i++) {
                Dvd dvd = new Dvd();
                dvd.setId(Integer.parseInt(values[i]));
                dvds.add(dvd);
            }
            order.setDvds(dvds);
            HttpSession session  = request.getSession(Boolean.FALSE);
            order.setCustomerId((Integer)(session.getAttribute(DvdConstants.LABEL_ID)));
            order.setOrderDate(LocalDate.now());
            Address address = new Address();
            address.setId(Integer.parseInt(request.getParameter("addressId")));
            order.setAddress(address);
            if (customerService.purchaseDvds(order)) {
                model.addAttribute(DvdConstants.LABEL_MESSAGE,"Your order placed Successfully." +
                    " Your order Id = " + order.getOrderId());
                Customer customer = customerService.searchCustomer(
                    (Integer)(session.getAttribute(DvdConstants.LABEL_ID)));
                model.addAttribute(DvdConstants.LABEL_CUSTOMER,customer);
             }
        } catch (DvdException e) {
            model.addAttribute(DvdConstants.LABEL_MESSAGE, e.getMessage());
            return DvdConstants.LABEL_ERROR_JSP;
        }
        return DvdConstants.LABEL_DISPLAY_CUSTOMER_JSP;
    } 

     /**
      * <p>
      * ViewOrder function is used to display the order details
      * The order details consists of orderId ,order date and dvd details
      * </p>
      *
      * @param request which is send from client to trigger an action on the server
      */
    @RequestMapping(value="/viewOrder", method = RequestMethod.POST)  
    public String viewOrder(ModelMap model, HttpServletRequest request) { 
        try {
            HttpSession session  = request.getSession(Boolean.FALSE);
            Integer customerId = (Integer)(session.getAttribute(DvdConstants.LABEL_ID));
            model.addAttribute(DvdConstants.LABEL_CUSTOMER,
                customerService.searchCustomer(customerId));
            model.addAttribute(DvdConstants.LABEL_ACTION,"viewOrder");
        } catch (DvdException e) {
            model.addAttribute(DvdConstants.LABEL_MESSAGE, e.getMessage());
            return DvdConstants.LABEL_ERROR_JSP;
        }
        return DvdConstants.LABEL_DISPLAY_CUSTOMER_JSP;
    } 

     /**
      * <p>
      * CancelOrder function used to cancel order which removes order details 
      * permanently
      * <p/>
      *
      * @param request which is send from client to trigger an action on the server
      */
    @RequestMapping(value="/cancelOrder", method = RequestMethod.POST)  
    public String cancelOrder(ModelMap model, HttpServletRequest request) { 
        try {
            Integer orderId = Integer.parseInt(request.getParameter("orderId"));
            if (customerService.cancelOrder(orderId)) {
                HttpSession session  = request.getSession(Boolean.FALSE);
                Customer customer = 
     customerService.searchCustomer((Integer)(session.getAttribute(DvdConstants.LABEL_ID)));
                model.addAttribute(DvdConstants.LABEL_MESSAGE, "Your order cancelled " +
                    "Successfully...order Id = " + orderId);
                model.addAttribute(DvdConstants.LABEL_CUSTOMER, customerService.searchCustomer(
                    (Integer)(session.getAttribute(DvdConstants.LABEL_ID))));
            }
        } catch (DvdException e) {
            model.addAttribute(DvdConstants.LABEL_MESSAGE, e.getMessage());
            return DvdConstants.LABEL_ERROR_JSP;
        }
        return DvdConstants.LABEL_DISPLAY_CUSTOMER_JSP;
    }

    /**
      * <p>
      * viewCustomers is a List function which is return the List 
      * List contains all available customer details 
      * <p/>
      */
    @RequestMapping(value="/viewCustomers", method = RequestMethod.POST)  
    public ModelAndView viewCustomers() { 
        try {
            return new ModelAndView("DisplayCustomers", "customers", customerService.getCustomers());
        } catch (DvdException e) {
            return new ModelAndView(DvdConstants.LABEL_ERROR_JSP, DvdConstants.LABEL_MESSAGE, 
                e.getMessage());
        }
    }
}
