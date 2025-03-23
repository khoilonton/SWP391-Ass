/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import DAO.PromotionDAO;
import Model.Promotion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;


/**
 *
 * @author TrangTrongKhoi-CE180958
 */
@WebServlet(name = "DeletePromotion", urlPatterns = {"/DeletePromotion"})
public class DeletePromotion extends HttpServlet {

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
    if (idParam == null || !idParam.matches("\\d+")) {
        request.setAttribute("errorMessage", "Invalid Promotion ID.");
      request.getRequestDispatcher("WEB-INF/DeletePromotion.jsp").forward(request, response);
        return;
    }

    int promoId = Integer.parseInt(idParam);
    PromotionDAO promoDAO = new PromotionDAO();
    Promotion promoList = promoDAO.getPromotionById(promoId);
    if (promoList == null) {
        request.setAttribute("errorMessage", "Promotion ID not found.");
        request.getRequestDispatcher("WEB-INF/DeletePromotion.jsp").forward(request, response);
        return;
    }

    request.setAttribute("promoList", promoList);
    request.getRequestDispatcher("WEB-INF/DeletePromotion.jsp").forward(request, response);
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
           int promoId = Integer.parseInt(request.getParameter("id"));
      PromotionDAO promotionDAO = new PromotionDAO();

       try {
          boolean isDeleted = promotionDAO.deletePromotion(promoId);
          if (isDeleted) {
             response.sendRedirect("PromotionManager");
          } else {
              request.setAttribute("errorMessage", "Failed to delete promotion.");
               request.getRequestDispatcher("DeletePromotion.jsp").forward(request, response);
           }
       } catch (Exception e) {
           request.setAttribute("errorMessage", "Error occurred while deleting promotion.");
           request.getRequestDispatcher("DeletePromotion.jsp").forward(request, response);       
       }
    }
   

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */    @Override
 /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */

    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}

