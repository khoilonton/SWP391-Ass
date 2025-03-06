/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import DAO.FeebackDAO;
import Model.Customer;
import Model.Feeback;
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
@WebServlet(name = "FeebackServlet", urlPatterns = {"/FeebackManager"})
public class FeebackServlet extends HttpServlet {

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
        FeebackDAO feeDAO= new FeebackDAO();
        ArrayList<Feeback> feeList= feeDAO.getAll();
        Map<Integer,List<Customer>> nameList= feeDAO.getNameCus();
        request.setAttribute("nameList", nameList);
        request.setAttribute("feeList", feeList);
        request.getRequestDispatcher("WEB-INF/CommentManager.jsp").forward(request, response);    
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
    FeebackDAO feeDAO = new FeebackDAO();
    String action = request.getParameter("action");
    if ("updateStatus".equalsIgnoreCase(action)) {
        int feedbackID = Integer.parseInt(request.getParameter("feedbackID"));
        String status = request.getParameter("status");
        boolean isUpdated = feeDAO.updateStatus(feedbackID, status);
        if (isUpdated) {
            request.setAttribute("message", "Approve success");
        } else {
            request.setAttribute("message", "Approve success");
        }
        response.sendRedirect(request.getContextPath() + "/FeebackManager");
    } else if ("delete".equalsIgnoreCase(action)) {
        int feedbackID = Integer.parseInt(request.getParameter("feedbackID"));
        boolean isDeleted = feeDAO.delete(feedbackID);
        if (isDeleted) {
            request.setAttribute("message", "Delete success");
        } else {
            request.setAttribute("message", "Delete  fail");
        }
        response.sendRedirect(request.getContextPath() + "/FeebackManager");
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
