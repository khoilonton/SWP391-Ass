/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import DAO.DiscountDAO;
import Model.Discount;
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
@WebServlet(name = "EditDiscount", urlPatterns = {"/EditDiscount"})
public class EditDiscount extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

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
            DiscountDAO disDAO = new DiscountDAO();
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            request.setAttribute("errorMessage", "There is no discount with that ID");
        } else {
            try {
                int id = Integer.parseInt(idParam);
                Discount  discount = disDAO.getByid(id);
                if (discount == null) {
                    request.setAttribute("errorMessage", "There is no discount with that ID");
                } else {
                    request.setAttribute("discount", discount);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid discount ID");
            }
        }
        request.getRequestDispatcher("WEB-INF/EditDiscount.jsp").forward(request, response);
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
             int discountID = Integer.parseInt(request.getParameter("discountID")); 
           int min_value = Integer.parseInt(request.getParameter("minOrderValue"));
        String code = request.getParameter("code");
        Date start_date = Date.valueOf(request.getParameter("startDate"));
        Date end_date = Date.valueOf(request.getParameter("expiryDate"));
        double percent = Double.parseDouble(request.getParameter("percent"));
        int usedCount = Integer.parseInt(request.getParameter("usedCount"));
        int limitCode = Integer.parseInt(request.getParameter("limitCode"));
                DiscountDAO disDAO= new DiscountDAO();
            if (disDAO.update(new Discount(discountID,min_value,end_date,percent,code,usedCount,limitCode,start_date)) == 1) {
                    request.setAttribute("errorMessage", "Update success");
                         response.sendRedirect("DiscountManager");
            } else {
                request.setAttribute("errorMessage", "Update fail");
                    request.getRequestDispatcher("WEB-INF/EditDiscount.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid input for number fields.");
                request.getRequestDispatcher("WEB-INF/EditDiscount.jsp").forward(request, response);
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
