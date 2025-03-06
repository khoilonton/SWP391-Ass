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
import java.io.PrintWriter;

/**
 *
 * @author TrangTrongKhoi-CE180958
 */
@WebServlet(name = "AddCategoryControl", urlPatterns = {"/addCategory"})
public class AddCategoryControl extends HttpServlet {

  

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
      request.getRequestDispatcher("WEB-INF/addCategory.jsp").forward(request, response);
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
  String catIDStr = request.getParameter("catID").trim();
        String catName = request.getParameter("catName").trim();

        if (catIDStr.isEmpty() || catName.isEmpty()) {
            request.setAttribute("error", "Both ID and Category name cannot be empty!");
            request.getRequestDispatcher("addCategory.jsp").forward(request, response);
            return;
        }

        try {
            int catID = Integer.parseInt(catIDStr);
            CategoryDAO dao = new CategoryDAO();

            // Kiểm tra nếu ID đã tồn tại
            if (dao.isCategoryIdExists(catID)) {
                request.setAttribute("error", "Category ID already exists. Please enter a unique ID.");
                request.getRequestDispatcher("addCategory.jsp").forward(request, response);
                return;
            }

            boolean success = dao.addCategory(catID, catName);
            if (success) {
                response.sendRedirect("viewCategory");
            } else {
                request.setAttribute("error", "Failed to add category.");
                request.getRequestDispatcher("addCategory.jsp").forward(request, response);
            }
        } catch (NumberFormatException e) {
            request.setAttribute("error", "Invalid ID format. Please enter a valid number.");
            request.getRequestDispatcher("addCategory.jsp").forward(request, response);
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
