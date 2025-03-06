/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Control;

import DAO.OrderDAO;
import Model.Order;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;

/**
 *
 * @author Lenovo
 */
@WebServlet(name = "ViewOrderStaffListServlet", urlPatterns = {"/viewOrderStaffListServlet"})
public class ViewOrderStaffListServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderAdminServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderAdminServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        HttpSession session = request.getSession();
        Integer staffID = (Integer) session.getAttribute("staffID");

        // Kiểm tra session (nếu người dùng chưa đăng nhập, chuyển hướng đến Login)
        if (staffID == null) {
            response.sendRedirect("Login.jsp");
            return;
        }

        // Lấy các tham số lọc từ request (nếu có)
        String statusFilter = request.getParameter("status");
        String dateFilter = request.getParameter("date");
        String orderedFilter = request.getParameter("ordered");

        OrderDAO orderDAO = new OrderDAO();
        List<Order> orders;

        // Nếu có tham số lọc thì gọi phương thức getFilteredOrders để lọc dữ liệu
        if ((statusFilter != null && !statusFilter.isEmpty())
                || (dateFilter != null && !dateFilter.isEmpty())
                || (orderedFilter != null && !orderedFilter.isEmpty())) {
            orders = orderDAO.getFilteredOrders(statusFilter, dateFilter, orderedFilter);
        } else {
            // Nếu không có tham số lọc, lấy tất cả đơn hàng
            orders = orderDAO.getAllOrders();
        }

        // Gửi danh sách đơn hàng vào request để hiển thị trong JSP
        request.setAttribute("orders", orders);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/staffViewOrderList.jsp");
        dispatcher.forward(request, response);
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
        processRequest(request, response);
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
