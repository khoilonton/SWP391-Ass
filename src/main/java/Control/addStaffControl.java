/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import DAO.OrderDAO;
import DAO.StaffDAO;
import Model.Staff;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;


/**
 *
 * @author TrangTrongKhoi-CE180958
 */
@WebServlet(name = "addStaffControl", urlPatterns = {"/addStaffControl"})
public class addStaffControl extends HttpServlet {

  

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
       request.getRequestDispatcher("WEB-INF/addStaff.jsp").forward(request, response);
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
    try {
        int staffID = Integer.parseInt(request.getParameter("staffID"));
        String fullName = request.getParameter("fullName");
        String address = request.getParameter("address");
        int phone = Integer.parseInt(request.getParameter("phone"));
        Date dateJoin = Date.valueOf(request.getParameter("dateJoin"));
        int salary = Integer.parseInt(request.getParameter("salary"));
        int userID = Integer.parseInt(request.getParameter("userID"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        Staff staff = new Staff(staffID, fullName, address, phone, dateJoin, salary, userID);
        StaffDAO staffDAO = new StaffDAO();

        boolean isAdded = staffDAO.addStaff(staff, username, password);
        if (isAdded) {
            response.sendRedirect("ViewStaffList");
        } else {
            request.setAttribute("error", "Failed to add staff. UserID might already exist.");
            request.getRequestDispatcher("addStaff.jsp").forward(request, response);
        }
    } catch (Exception e) {
        request.setAttribute("error", "Invalid input. Please check your data.");
        request.getRequestDispatcher("addStaff.jsp").forward(request, response);
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
