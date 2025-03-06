package Control;

import DAO.StaffDAO;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;

@WebServlet(name = "UpdateStaffServlet", urlPatterns = {"/UpdateStaffServlet"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2, // 2MB
        maxFileSize = 1024 * 1024 * 10, // 10MB
        maxRequestSize = 1024 * 1024 * 50 // 50MB
)
public class UpdateSelfStaffServlet extends HttpServlet {

    private StaffDAO staffDAO = new StaffDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer staffID = (Integer) session.getAttribute("staffID");

        if (staffID == null) {
            response.sendRedirect("WEB-ÌNF/Login.jsp");
            return;
        }

        try {
            String address = request.getParameter("address");
            int phone = Integer.parseInt(request.getParameter("phone"));

            // Xử lý upload ảnh vào thư mục public/assets/img
            String img = "";
            try {
                Part part = request.getPart("img"); // Lấy file từ input có name là "img"
                String picFolder = "img";
                String[] context = request.getServletContext().getRealPath("").split("target");
                String realPath = context[0] + "src" + File.separator + "main" + File.separator + "webapp" + File.separator + "images" + File.separator + "staff" + File.separator + picFolder;

                // Lấy tên file từ đối tượng Part
                String fileName = Paths.get(part.getSubmittedFileName()).getFileName().toString();

                // Lưu file vào thư mục đích
                part.write(realPath + File.separator + fileName);
                img = fileName;
            } catch (Exception e) {
                e.printStackTrace();
                img = request.getParameter("txtPicOr"); // Giá trị mặc định nếu không upload được ảnh
            }

            // Cập nhật thông tin nhân viên
            boolean updateStaffInfo = staffDAO.updateSelfStaffInfo(staffID, address, phone, img);

            if (updateStaffInfo) {
                request.setAttribute("message", "Cập nhật thành công!");
            } else {
                request.setAttribute("errorMessage", "Cập nhật thất bại!");
            }

            request.getRequestDispatcher("readSelfStaffServlet").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Lỗi trong quá trình cập nhật!");
            request.getRequestDispatcher("staffView.jsp").forward(request, response);
        }
    }
}
