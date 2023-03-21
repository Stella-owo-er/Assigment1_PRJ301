/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.authentication;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.User;

/**
 *
 * @author dell
 */
public abstract class BaseAuthenticationController extends HttpServlet {

    
     private boolean isAuthenticated(HttpServletRequest request) {
        return request.getSession().getAttribute("account") != null;
    }
     
      protected abstract void doGet(HttpServletRequest request, HttpServletResponse response,User user)
            throws ServletException, IOException;
    protected abstract void doPost(HttpServletRequest request, HttpServletResponse response,User user)
            throws ServletException, IOException;
     
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      //  processRequest(request, response);
      if(isAuthenticated(request))
        {
            //do business
            doGet(request, response, (User)request.getSession().getAttribute("account"));
        }
        else
        {
            response.getWriter().println("access denied!");
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
     
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      //  processRequest(request, response);
       if(isAuthenticated(request))
        {
            //do business
            doPost(request, response, (User)request.getSession().getAttribute("user"));
        }
        else
        {
            response.getWriter().println("access denied!");
        }
    }
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
