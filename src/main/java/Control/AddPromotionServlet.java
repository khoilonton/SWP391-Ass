/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import DAO.PromotionDAO;
import Model.Product;
import Model.Promotion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author TrangTrongKhoi-CE180958
 */
@WebServlet(name = "AddPromotionServlet", urlPatterns = {"/AddPromotion"})
public class AddPromotionServlet extends HttpServlet {

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
                   PromotionDAO promoDAO= new PromotionDAO();
                 ArrayList<Product> proList= promoDAO.getProduct();
                 request.setAttribute("proList", proList);
                   request.getRequestDispatcher("WEB-INF/AddPromotion.jsp").forward(request, response);
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
         String name = request.getParameter("name");
    String description = request.getParameter("description");
    Date startDate = Date.valueOf(request.getParameter("start_date"));
    Date endDate = Date.valueOf(request.getParameter("end_date"));
    double percent = Double.parseDouble(request.getParameter("percent"));
   String[] productIDs = request.getParameterValues("productID");
  PromotionDAO proDAO = new PromotionDAO();
    Promotion promotion = new Promotion(0, description, name, startDate, endDate, percent);
    int promoID = proDAO.create(promotion);

    if (promoID > 0) { 
        if (productIDs != null) {
            for (String productID : productIDs) {
                proDAO.createPromoProduct(promoID, Integer.parseInt(productID));
            }
        }
         request.setAttribute("error", "Add success");
        response.sendRedirect("PromotionManager");
    } else {
        request.setAttribute("error", "Add fail");
        request.getRequestDispatcher("WEB-INF/AddPromotion.jsp").forward(request, response);
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
