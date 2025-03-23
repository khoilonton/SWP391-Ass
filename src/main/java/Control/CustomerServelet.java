/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import DAO.AccountDAO;
import DAO.CustomerDAO;
import Model.Account;
import Model.Customer;
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
@WebServlet(name = "CustomerServelet", urlPatterns = {"/CustomerManager"})
public class CustomerServelet extends HttpServlet {
int page = 1;
    int recordsPerPage = 5;
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
     * @throws IOException if an I/O error occurss
     */
   @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    CustomerDAO cusDAO = new CustomerDAO();

     
    if (request.getParameter("page") != null) {
        page = Integer.parseInt(request.getParameter("page"));
    }
    ArrayList<Customer> cusList = cusDAO.getCustomersByPage(page, recordsPerPage);
    int totalCustomers = cusDAO.getTotalCustomers();
    int totalPages = (int) Math.ceil((double) totalCustomers / recordsPerPage);
    Map<Integer, List<Account>> accList = cusDAO.getStatusAcc();
    for (Customer cus : cusList) {
        int totalInvoices = cusDAO.getTotalInvoices(cus.getCus_id());
        int totalAmount = cusDAO.getTotalAmount(cus.getCus_id());
        cus.setTotalInvoices(totalInvoices);
        cus.setTotalAmount(totalAmount);
    }
    request.setAttribute("cusList", cusList);
    request.setAttribute("accList", accList);
    request.setAttribute("currentPage", page);
    request.setAttribute("totalPages", totalPages);
    request.getRequestDispatcher("WEB-INF/ViewDetailCustomer.jsp").forward(request, response);
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
        int id = Integer.parseInt(request.getParameter("userId"));
        String newStatus = request.getParameter("status");
        AccountDAO accDao = new AccountDAO();
        accDao.updateAccountStatus(id, newStatus);
        response.sendRedirect ("CustomerManager");
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
