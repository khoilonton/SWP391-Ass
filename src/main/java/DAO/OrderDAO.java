/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DB.DBContext;
import Model.Customer;
import Model.Order;
import Model.OrderDetail;
import Model.Payment;
import Model.Product;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TrangTrongKhoi-CE180958
 */
public class OrderDAO {

    public ArrayList<Order> getAll() {
        ArrayList<Order> orderList = new ArrayList<>();
        String query = "select * from Orders";
        DBContext ne = new DBContext();

        try ( ResultSet rs = ne.execSelectQuery(query);) {
            while (rs.next()) {
                orderList.add(new Order(rs.getInt(1), rs.getDate(2), rs.getDouble(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromotionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderList;
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM Orders";

        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql);  ResultSet rs = ps.executeQuery()) { // Thêm ResultSet vào đây

            while (rs.next()) {
                Order order = new Order();
                order.setOrder_id(rs.getInt("OrderID"));
                order.setOder_date(rs.getDate("Order_Date")); // Đổi thành order_date
                order.setTotal_amount(rs.getDouble("TotalAmount"));
                order.setStatus(rs.getString("Status"));
                order.setCancell(rs.getString("CancellationReason"));
                order.setCus_id(rs.getInt("CustomerID"));
                order.setDis_id(rs.getObject("DisID") != null ? rs.getInt("DisID") : 0); // Đổi null thành 0
                order.setPromo_id(rs.getObject("PromoID") != null ? rs.getInt("PromoID") : 0); // Đổi null thành 0
                order.setUsed_discount(rs.getInt("Used_Discount"));

                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public static List<Order> getPurchasedProductByCustomerID(int customerID) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT o.OrderID, o.Order_Date, o.TotalAmount, o.Status, "
                + "p.ProID, p.ProName, p.Img, od.Quantity, od.UnitPrice, "
                + "pay.Payment_method, pay.Payment_status, f.Content "
                + "FROM Orders o "
                + "JOIN OrderDetail od ON o.OrderID = od.OrderID "
                + "JOIN Product p ON od.ProID = p.ProID "
                + "LEFT JOIN Payment pay ON o.OrderID = pay.OrderID "
                + "LEFT JOIN Feedback f ON od.OrderDetailID = f.OrderDetailID "
                + "WHERE o.CustomerID = ? AND o.Status = 'Delivered'";

        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerID);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Order order = new Order();
                order.setOrder_id(rs.getInt("OrderID"));
                order.setOder_date(rs.getDate("Order_Date"));
                order.setTotal_amount(rs.getDouble("TotalAmount"));
                order.setStatus(rs.getString("Status"));

                Product product = new Product();
                product.setPro_id(rs.getInt("ProID"));
                product.setName(rs.getString("ProName"));
                product.setImg(rs.getString("Img"));

                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProduct(product);
                orderDetail.setQuantity(rs.getInt("Quanity"));
                orderDetail.setUnit_price(rs.getDouble("UnitPrice"));

                Payment payment = new Payment();
                payment.setMethod(rs.getString("Payment_method"));
                payment.setStatus(rs.getString("Payment_status"));

                order.getOrderDetails().add(orderDetail);
                order.setPayment(payment);
                order.setFeedbackContent(rs.getString("Content"));

                orders.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return orders;
    }

    public static String getPaymentMethodByOrderID(int orderID) {
        String sql = "SELECT Payment_method FROM Payment WHERE OrderID = ? LIMIT 1";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("Payment_method");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Không xác định";
    }

    public static String getPaymentStatusByOrderID(int orderID) {
        String sql = "SELECT Payment_status FROM Payment WHERE OrderID = ? LIMIT 1";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("Payment_status");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Không xác định";
    }

    public boolean updateOrderStatus(int orderID, String status) {
        String query = "UPDATE Orders SET Status = ? WHERE OrderID = ?";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, status);
            ps.setInt(2, orderID);

            int rowsUpdated = ps.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Order> getFilteredOrders(String status, String date, String orderedBy) {
        List<Order> orders = new ArrayList<>();
        String query = "SELECT * FROM Orders WHERE 1=1";  // Cơ sở lọc mặc định

        // Thêm các điều kiện vào truy vấn
        if (status != null && !status.isEmpty()) {
            query += " AND Status = ?";
        }
        if (date != null && !date.isEmpty()) {
            query += " AND Order_Date = ?";
        }
        if (orderedBy != null && !orderedBy.isEmpty()) {
            query += " AND CustomerID = ?";
        }

        // Thực thi truy vấn
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(query)) {
            int index = 1;
            if (status != null && !status.isEmpty()) {
                ps.setString(index++, status);
            }
            if (date != null && !date.isEmpty()) {
                ps.setDate(index++, Date.valueOf(date));  // Giả sử ngày được gửi dưới dạng String (yyyy-MM-dd)
            }
            if (orderedBy != null && !orderedBy.isEmpty()) {
                ps.setInt(index++, Integer.parseInt(orderedBy));  // Giả sử orderedBy là customer_id
            }

            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Chuyển đổi kết quả từ ResultSet thành đối tượng Order
                    Order order = new Order();
                    order.setOrder_id(rs.getInt("OrderID"));
                    order.setOder_date(rs.getDate("Order_Date"));
                    order.setTotal_amount(rs.getDouble("TotalAmount"));
                    order.setStatus(rs.getString("Status"));
                    order.setCancell(rs.getString("CancellationReason"));
                    order.setCus_id(rs.getInt("CustomerID"));
                    order.setDis_id(rs.getObject("DisID") != null ? rs.getInt("DisID") : 0);
                    order.setPromo_id(rs.getObject("PromoID") != null ? rs.getInt("PromoID") : 0);

                    order.setUsed_discount(rs.getInt("Used_Discount"));
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public boolean updateOrderStatu(int orderID, String newStatus) {
        String query = "update Orders set Status=? where OrderID=?";
        DBContext db = new DBContext();
        Object[] param = {newStatus, orderID};
        try {
            return db.execQuery(query, param) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Map<Integer, List<Product>> getProductsByOrder() {
        Map<Integer, List<Product>> orderProducts = new HashMap<>();
        String query = "select o.OrderID, p.ProID, p.ProName\n"
                + "   from OrderDetail o\n"
                + "  join Product as p on o.ProID=p.ProID";

        DBContext db = new DBContext();
        try ( ResultSet rs = db.execSelectQuery(query)) {
            while (rs.next()) {
                int orderId = rs.getInt(1);
                Product product = new Product(rs.getInt(2), null, null, null, null, rs.getString(3), 0, 0, 0, null);
                orderProducts.putIfAbsent(orderId, new ArrayList<Product>());
                orderProducts.get(orderId).add(product);
                if (!orderProducts.get(orderId).contains(product)) {
                    orderProducts.get(orderId).add(product);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(OrderDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return orderProducts;
    }

    public List<OrderDetail> getOrderDetailsByOrderID(int orderID) {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String query = "SELECT od.OrderDetailID, od.Quanity, od.UnitPrice, od.TotalAmount, "
                + "p.ProID, p.ProName, p.Img, "
                + "c.FullName, pay.Payment_method "
                + "FROM OrderDetail od "
                + "JOIN Product p ON od.ProID = p.ProID "
                + "JOIN [Orders] o ON od.OrderID = o.OrderID "
                + "JOIN Customer c ON o.CustomerID = c.CustomerID "
                + "JOIN Payment pay ON od.OrderID = pay.OrderID "
                + "WHERE od.OrderID = ?";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, orderID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Product product = new Product();
                product.setPro_id(rs.getInt("ProID"));
                product.setName(rs.getString("ProName"));
                product.setImg(rs.getString("Img"));
                Customer customer = new Customer();
                customer.setName(rs.getString("FullName"));
                Payment pay = new Payment();
                pay.setMethod(rs.getString("Payment_method"));
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setOrder_detail_id(rs.getInt("OrderDetailID"));
                orderDetail.setQuantity(rs.getInt("Quanity"));
                orderDetail.setUnit_price(rs.getDouble("UnitPrice"));
                orderDetail.setTotal_amount(rs.getDouble("TotalAmount"));
                orderDetail.setProduct(product);
                orderDetail.setCustomer(customer);
                orderDetail.setPayment(pay);
                orderDetails.add(orderDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }

    public List<Order> getOrdersByCustomerID(int customerID) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT o.OrderID, o.Order_Date, o.TotalAmount, o.Status, "
                + "od.Quanity, od.UnitPrice, "
                + "p.ProName, p.Img, "
                + "c.FullName "
                + "FROM Orders o "
                + "JOIN OrderDetail od ON o.OrderID = od.OrderID "
                + "JOIN Product p ON od.ProID = p.ProID "
                + "JOIN Customer c ON o.CustomerID = c.CustomerID "
                + "WHERE o.CustomerID = ? "
                + "ORDER BY o.OrderID"; // Sắp xếp theo OrderID

        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, customerID);
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int orderID = rs.getInt("OrderID");
                    // Kiểm tra xem Order với orderID này đã tồn tại trong danh sách chưa
                    Order order = null;
                    for (Order o : orders) {
                        if (o.getOrder_id() == orderID) {
                            order = o;
                            break;
                        }
                    }

                    // Nếu chưa tồn tại, tạo mới và thêm vào danh sách
                    if (order == null) {
                        order = new Order();
                        order.setOrder_id(rs.getInt("OrderID"));
                        order.setOder_date(rs.getDate("Order_Date"));
                        order.setTotal_amount(rs.getDouble("TotalAmount"));
                        order.setStatus(rs.getString("Status"));
                        orders.add(order);
                    }

                    // Tạo OrderDetail và Product cho dòng kết quả hiện tại
                    OrderDetail orderDetail = new OrderDetail();
                    orderDetail.setQuantity(rs.getInt("Quanity"));
                    orderDetail.setUnit_price(rs.getDouble("UnitPrice"));

                    Product product = new Product();
                    product.setName(rs.getString("ProName"));
                    product.setImg(rs.getString("Img"));

                    orderDetail.setProduct(product);
                    order.getOrderDetails().add(orderDetail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

    public static void main(String[] args) {
        OrderDAO orderDAO = new OrderDAO();
//        ArrayList<Order> list = orderDAO.getAll();
//        for (Order order : list) {
//            System.out.println(order);
//        }
        List<OrderDetail> tlist = orderDAO.getOrderDetailsByOrderID(1);
        for (OrderDetail orderDetail : tlist) {
            System.out.println(orderDetail.getCustomer().getName());
        }
    }
}
