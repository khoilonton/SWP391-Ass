/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import DAO.OrderDAO;
import Model.Order;
import Model.Product;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 *
 * @author TrangTrongKhoi-CE180958
 */
@WebServlet(name = "ViewOrderListAdmin", urlPatterns = {"/ViewOrderListAdmin"})
public class ViewOrderListAdmin extends HttpServlet {

    

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
        OrderDAO orderDAO= new OrderDAO();
        ArrayList<Order> orderList= orderDAO.getAll();
        Map<Integer,List<Product>> productList=orderDAO.getProductsByOrder();
        request.setAttribute("productList", productList);
        request.setAttribute("orderList", orderList);
        request.getRequestDispatcher("WEB-INF/ViewOrderAdminList.jsp").forward(request, response);
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
         int id = Integer.parseInt(request.getParameter("orderID"));
      String newStatus = request.getParameter("status");    
      OrderDAO orderDAO= new OrderDAO();
      
      if (orderDAO.updateOrderStatu(id, newStatus)) {
        request.setAttribute("message", "Update success");
    } else {
        request.setAttribute("message", "Update fail");
    }
  response.sendRedirect("ViewOrderListAdmin");
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
