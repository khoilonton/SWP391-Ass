package Control;

import DAO.StaffDAO;
import Model.Staff;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "ReadSelfStaffServlet", urlPatterns = {"/readSelfStaffServlet"})
public class ReadSelfStaffServlet extends HttpServlet {

    private StaffDAO staffDAO = new StaffDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer staffID = (Integer) session.getAttribute("staffID");

        // Kiểm tra session (nếu người dùng chưa đăng nhập, chuyển hướng đến Login)
        if (staffID == null) {
            response.sendRedirect("WEB-INF/Login.jsp");
            return;
        }

        Staff staff = staffDAO.getStaffByID(staffID);
        if (staff == null) {
            request.setAttribute("errorMessage", "Không tìm thấy nhân viên.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/Error.jsp");
            dispatcher.forward(request, response);
            return;
        }

        request.setAttribute("staff", staff);
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/ViewSelfStaff.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
