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

/**
 *
 * @author TrangTrongKhoi-CE180958
 */
@WebServlet(name = "EditPromotionServlet", urlPatterns = {"/EditPromotion"})
public class EditPromotionServlet extends HttpServlet {

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

    if (idParam == null || idParam.trim().isEmpty()) {
        request.setAttribute("errorMessage", "Promotion ID cannot be blank!");
        request.getRequestDispatcher("WEB-INF/EditPromotion.jsp").forward(request, response);
        return;
    }

    try {
        int promoID = Integer.parseInt(idParam);
        PromotionDAO promoDAO = new PromotionDAO();
        Promotion promotion = promoDAO.getPromotionById(promoID);
        if (promotion == null) {
            request.setAttribute("errorMessage", "Promotion ID does not exist!");
            request.getRequestDispatcher("WEB-INF/EditPromotion.jsp").forward(request, response);
            return;
        }
        ArrayList<Product> productList = promoDAO.getProduct();
        List<Integer> selectedProducts = promoDAO.getProductsByPromoID(promoID);  
        request.setAttribute("promotion", promotion);
        request.setAttribute("productList", productList);
        request.setAttribute("selectedProducts", selectedProducts);
        request.getRequestDispatcher("WEB-INF/EditPromotion.jsp").forward(request, response);
    } catch (NumberFormatException e) {
        request.setAttribute("errorMessage", "Promotion ID is invalid!");
        request.getRequestDispatcher("WEB-INF/EditPromotion.jsp").forward(request, response);
    }
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
        int promoID = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Date startDate = Date.valueOf(request.getParameter("start_date"));
        Date endDate = Date.valueOf(request.getParameter("end_date"));
        double percent = Double.parseDouble(request.getParameter("percent"));
        String[] selectedProductIDs = request.getParameterValues("productID");

        PromotionDAO promoDAO = new PromotionDAO();
        Promotion promotion = new Promotion(promoID, description, name, startDate, endDate, percent);

        boolean success = promoDAO.updatePromotion(promotion);
        if (success) {
            promoDAO.deletePromoProducts(promoID);
            if (selectedProductIDs != null) {
                for (String productID : selectedProductIDs) {
                    promoDAO.createPromoProduct(promoID, Integer.parseInt(productID));
                }
            }
            response.sendRedirect("PromotionManager");
        } else {
            request.setAttribute("errorMessage", "Update failed. Please try again!");
            request.setAttribute("promotion", promotion);
            doGet(request, response);
        }
    } catch (NumberFormatException e) {
        request.setAttribute("errorMessage", "Invalid data! Please check again.");
        doGet(request, response);
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
