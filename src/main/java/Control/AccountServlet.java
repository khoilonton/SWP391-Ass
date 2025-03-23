/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import DAO.AccountDAO;
import Model.Account;
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
 * @author PC
 */
@WebServlet(name = "AccountServlet", urlPatterns = {"/AccountManager"})

public class AccountServlet extends HttpServlet {

    int pageSize = 10;

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
    int page = 1;

    if (request.getParameter("page") != null) {
        try {
            page = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            page = 1; 
        }
    }

    AccountDAO accDao = new AccountDAO();
    ArrayList<Account> accList = accDao.getAll(page, pageSize);
    int totalAccounts = accDao.getTotalAccounts();
    int totalPages = (int) Math.ceil((double) totalAccounts / pageSize);

  
    request.setAttribute("accList", accList);
    request.setAttribute("currentPage", page);
    request.setAttribute("pageSize", pageSize);
    request.setAttribute("totalPages", totalPages);
    request.getRequestDispatcher("WEB-INF/LockOrUnlock.jsp").forward(request, response);
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
        if (accDao.updateAccountStatus(id, newStatus)) {
            request.setAttribute("message", "Update success");
        } else {
            request.setAttribute("message", "Update fail");
        }

        response.sendRedirect(request.getContextPath() + "/AccountManager");
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
