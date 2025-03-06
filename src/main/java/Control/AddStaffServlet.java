/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

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
import java.text.SimpleDateFormat;

/**
 *
 * @author TrangTrongKhoi-CE180958
 */
@WebServlet(name = "AddStaffServlet", urlPatterns = {"/AddStaff"})
public class AddStaffServlet extends HttpServlet {

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
        response.sendRedirect("addStaff.jsp");
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
        int StaffID = Integer.parseInt(request.getParameter("staffID"));
        String Name = request.getParameter("name");
        String Address = request.getParameter("address");
        int Phone = Integer.parseInt(request.getParameter("phone"));
        String dateString = request.getParameter("dateJoin");

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date utilDate = null;
        try {
            utilDate = dateFormat.parse(dateString);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Date DateJoin = new Date(utilDate.getTime());

        int Salary = Integer.parseInt(request.getParameter("salary"));
        int UserID = Integer.parseInt(request.getParameter("userID"));

        Staff staff = new Staff(StaffID, Name, Address, Phone, DateJoin, Salary, UserID);

        StaffDAO staffDAO = new StaffDAO();
        boolean result = staffDAO.addStaff(staff, Name, Address);
        if (result) {
            response.sendRedirect(request.getContextPath() + "/ReadStaff");
        } else {

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
