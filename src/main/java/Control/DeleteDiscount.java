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

/**
 *
 * @author TrangTrongKhoi-CE180958
 */
@WebServlet(name = "DeleteDiscount", urlPatterns = {"/DeleteDiscount"})
public class DeleteDiscount extends HttpServlet {

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
        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            request.setAttribute("errorMessage", "Discount ID not found.");
        } else {
            try {
                int id = Integer.parseInt(idParam);
                DiscountDAO disDAO = new DiscountDAO();
                Discount discount = disDAO.getByid(id);
                if (discount == null) {
                    request.setAttribute("errorMessage", "The discount does not exist.");
                } else {
                    request.setAttribute("discount", discount);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "Invalid discount ID.");
            }
        }
        request.getRequestDispatcher("WEB-INF/DeleteDiscount.jsp").forward(request, response);
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
            int id = Integer.parseInt(request.getParameter("id"));
            DiscountDAO disDAO = new DiscountDAO();

            int result = disDAO.delete(id);

            if (result == 1) {
                request.setAttribute("successMessage", "Delete success");
            } else {
                request.setAttribute("errorMessage", "Error: Discount not found.");
            }
         response.sendRedirect("DiscountManager");

        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid discount ID.");
            request.getRequestDispatcher("WEB-INF/DeleteDiscount.jsp").forward(request, response);
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
