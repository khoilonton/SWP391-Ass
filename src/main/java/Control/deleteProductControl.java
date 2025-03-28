/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import DAO.ProductDAO;
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
@WebServlet(name = "deleteProductControl", urlPatterns = {"/deleteProductControl"})
public class deleteProductControl extends HttpServlet {


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
      try {
            String idParam = request.getParameter("proID"); // Lấy tham số từ request
            if (idParam != null && !idParam.isEmpty()) {
                int proID = Integer.parseInt(idParam);
                
                ProductDAO productDAO = new ProductDAO();
                productDAO.deleteProduct(proID); // Gọi hàm xóa trong DAO
                
                System.out.println("Deleted product ID: " + proID);
            } else {
                System.out.println("Invalid product ID received.");
            }
        } catch (NumberFormatException e) {
            System.err.println("Error parsing product ID: " + e.getMessage());
        }

        response.sendRedirect("ViewProductAdmin");  // Điều hướng về trang danh sách
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
