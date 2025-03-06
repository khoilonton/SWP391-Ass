package Control;

import DAO.OrderDAO;
import Model.Order;
import Model.Payment;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name="customerPurchasedProduct", urlPatterns={"/customerPurchasedProduct"})
public class customerPurchasedProduct extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Integer customerID = (Integer) session.getAttribute("customerID");

        if (customerID == null) {
            response.sendRedirect("Login.jsp");
            return;
        } else {
            int id = customerID;
            List<Order> orders = OrderDAO.getPurchasedProductByCustomerID(id);
            
            // Đảm bảo mỗi đơn hàng có thông tin Payment đầy đủ
            for (Order order : orders) {
                Payment payment = new Payment();
                payment.setMethod(OrderDAO.getPaymentMethodByOrderID(order.getOrder_id()));
                payment.setStatus(OrderDAO.getPaymentStatusByOrderID(order.getOrder_id()));
                order.setPayment(payment);
            }

            request.setAttribute("orders", orders);
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/ViewPurchasedProductCustomer.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet xử lý danh sách sản phẩm đã mua của khách hàng";
    }
}
