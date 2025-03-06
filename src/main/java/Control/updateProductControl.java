/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import DAO.ProductDAO;
import Model.Product;
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
@WebServlet(name = "updateProductControl", urlPatterns = {"/updateProductControl"})
public class updateProductControl extends HttpServlet {

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
     request.getRequestDispatcher("WEB-INF/updateProduct.jsp").forward(request, response);
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
            int proID = Integer.parseInt(request.getParameter("proID"));
            String proName = request.getParameter("proName");
            Date expiryDate = Date.valueOf(request.getParameter("expiry_date"));
            String description = request.getParameter("description");
            String stockStatus = request.getParameter("stock_status");
            String img = request.getParameter("img");
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            int catID = Integer.parseInt(request.getParameter("catID"));
            int proPrice = Integer.parseInt(request.getParameter("proPrice"));
            Date startdate= Date.valueOf(request.getParameter("start_date"));
       
            ProductDAO dao = new ProductDAO();
            dao.updateProduct(new Product(proID, expiryDate, description, stockStatus, img, proName, quantity, catID, proPrice,startdate));
            response.sendRedirect("ViewProductAdmin");
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("viewProductList.jsp?error=invalidData");
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
