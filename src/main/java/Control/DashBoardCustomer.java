package Control;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "DashBoardCustomer", urlPatterns = {"/dashBoardCustomer"})
public class DashBoardCustomer extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lấy session hiện tại
        HttpSession session = request.getSession(false); // false để không tạo session mới nếu không tồn tại

        if (session == null || session.getAttribute("customerID") == null) {
            // Nếu session null hoặc không có username, quay lại trang login
            response.sendRedirect("login");
            return;
        }

        // Lấy thông tin từ session
        int customerID = (Integer) session.getAttribute("customerID");

        // Đặt dữ liệu vào request để hiển thị trong JSP
        request.setAttribute("customerID", customerID);

        // Chuyển hướng đến dashboard
        request.getRequestDispatcher("WEB-INF/customerDashboard.jsp").forward(request, response);
    }
}
