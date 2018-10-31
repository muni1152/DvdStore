package com.ideas2it.dvdstore.filter;

import java.io.IOException;  
import java.io.PrintWriter;  
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.ideas2it.dvdstore.controller.UserController;
  
public class DvdStoreFilter implements Filter {  
  
    UserController userController = new UserController();

    public void init(FilterConfig arg0) throws ServletException {
    }  
      
    public void doFilter(ServletRequest request, ServletResponse response,  
            FilterChain chain) throws IOException, ServletException { 
 
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        HttpSession session  = req.getSession(false);
        Integer userId = null;
        if(null != session) {
            userId = (Integer) session.getAttribute("userId");
        }
  
        String uri = req.getRequestURI();
        
        if ((session == null && userId == null )) {
            if (uri.endsWith("DvdStore/") || uri.endsWith("signIn")) {
                chain.doFilter(request, response);
            } else {
                System.out.println("3");
                res.sendRedirect("/DvdStore");
            }
        } else {  
            if ((uri.endsWith("signIn")) && userId == null) {
                chain.doFilter(request, response);
            } else { 
                if ((!(uri.endsWith("DvdStore/"))) && userId == null) {
                    res.sendRedirect("/DvdStore");
                } else { System.out.println("5");
                    res.setHeader("Cache-Control"," no-cache, no-store, must-revalidate"); 
                    res.setHeader("Pragma", "no-cache"); 
                    res.setDateHeader("Expires", 0); 
                    chain.doFilter(request, response);
                }
            }  
        }    
    }

    public void destroy() {
    }  
}  
