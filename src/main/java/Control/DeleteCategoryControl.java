/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import DAO.CategoryDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;



/**
 *
 * @author TrangTrongKhoi-CE180958
 */
@WebServlet(name = "DeleteCategoryControl", urlPatterns = {"/deleteCategory"})
public class DeleteCategoryControl extends HttpServlet {

  

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
         String catID = request.getParameter("catID");
        
        if (catID != null) {
            try {
                int id = Integer.parseInt(catID);
                CategoryDAO dao = new CategoryDAO();
                boolean success = dao.deleteCategory(id);           
                if (success) {
                     request.getRequestDispatcher("WEB-INF/viewCategory.jsp").forward(request, response);
                } else {
                    request.setAttribute("error", "Failed to delete category.");           
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
            request.getRequestDispatcher("WEB-INF/viewCategory.jsp").forward(request, response);
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
