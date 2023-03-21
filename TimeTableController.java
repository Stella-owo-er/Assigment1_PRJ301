/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.LecturerDBContext;
import dal.SessionDBContext;
import dal.TimeSlotDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import model.Account;
import model.Lecturer;
import model.Session;
import model.TimeSlot;
import util.DateTimeHelper;

/**
 *
 * @author dell
 */
public class TimeTableController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Account account = (Account)request.getSession().getAttribute("account");
        String raw_from = request.getParameter("from");
        String raw_to = request.getParameter("to");
        java.sql.Date from = null;
        java.sql.Date to = null;
        if(raw_from == null || raw_from.length() ==0)
        {
            Date today = new Date();
            int todayOfWeek = DateTimeHelper.getDayofWeek(today);
            Date e_from = DateTimeHelper.addDays(today, 2 - todayOfWeek);
            Date e_to = DateTimeHelper.addDays(today, 8-todayOfWeek);
            from = DateTimeHelper.toDateSql(e_from);
            to = DateTimeHelper.toDateSql(e_to);
        }
        else
        {
            from = java.sql.Date.valueOf(raw_from);
            to = java.sql.Date.valueOf(raw_to);
        }
        
        request.setAttribute("from", from);
        request.setAttribute("to", to);
        request.setAttribute("dates", DateTimeHelper.getDateList(from, to));
        
        TimeSlotDBContext slotDB = new TimeSlotDBContext();
        ArrayList<TimeSlot> slots = slotDB.list();
        request.setAttribute("slots", slots);
        
        SessionDBContext sesDB = new SessionDBContext();
        ArrayList<Session> sessions = sesDB.filter(account.getUsername(), from, to);
        request.setAttribute("sessions", sessions);
        
        LecturerDBContext lecDB = new LecturerDBContext();
        Lecturer lecturer = lecDB.get(account.getUsername());
        request.setAttribute("lecturer", lecturer);
        
        request.getRequestDispatcher("../view/lecturer/timetable.jsp").forward(request, response);
        
    } 
    
    
//    String raw_to = request.getParameter("to");```
//
//3. It checks if "raw_from" is null, if it is null it will set "from" and "to" to a default range of dates 
//from the current date till the end of the current week.
//If "raw_from" is not null and has a length greater than 0, it will then parse both "raw_from" and "raw_to" 
//to SQL date objects and sets both to "from" and "to" respectively.

//```if(raw_from == null || raw_from.length() ==0)
//{
//    Date today = new Date();
//    int todayOfWeek = DateTimeHelper.getDayofWeek(today);
//    Date e_from = DateTimeHelper.addDays(today, 2 - todayOfWeek);
//    Date e_to = DateTimeHelper.addDays(today, 8-todayOfWeek);
//    from = DateTimeHelper.toDateSql(e_from);
//    to = DateTimeHelper.toDateSql(e_to);
//}
//else
//{
//    from = java.sql.Date.valueOf(raw_from);
//    to = java.sql.Date.valueOf(raw_to);
//}```
//

//4. It sets "from" and "to" as well as the date range list "dates" (generated using the DateTimeHelper helper class) 
//as attributes of the request object.
//```request.setAttribute("from", from);
//request.setAttribute("to", to);
//request.setAttribute("dates", DateTimeHelper.getDateList(from, to));```
//
//5. Creates an instance of the TimeSlotDBContext class and retrieves a list of TimeSlot objects from the database 
//and stores it in an ArrayList called "slots". It then adds "slots" as an attribute of the request object.
//```TimeSlotDBContext slotDB = new TimeSlotDBContext();
//ArrayList<TimeSlot> slots = slotDB.list();
//request.setAttribute("slots", slots);```
//

//6. Creates an instance of the SessionDBContext class and retrieves a filtered list of Session objects from the 
//database based on the username and date range filters specified by the "account", "from" and "to" objects respectively. 
//It then adds "sessions" as an attribute of the request object.
//```SessionDBContext sesDB = new SessionDBContext();
//ArrayList<Session> sessions = sesDB.filter(account.getUsername(), from, to);
//request.setAttribute("sessions", sessions);```
//

//7. Creates an instance of the LecturerDBContext and retrieves the Lecturer object associated with the username stored 
//in the "account" object. It then adds "lecturer" as an attribute of the request object.
//```LecturerDBContext lecDB = new LecturerDBContext();
//Lecturer lecturer = lecDB.get(account.getUsername());
//request.setAttribute("lecturer", lecturer);```


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
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
