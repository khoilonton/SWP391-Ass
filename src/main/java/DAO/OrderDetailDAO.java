/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DB.DBContext;
import Model.OrderDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TrangTrongKhoi-CE180958
 */
public class OrderDetailDAO {
     public List<OrderDetail> getAllOrderDetail() {
        List<OrderDetail> orderDetails = new ArrayList<>();
        String sql = "SELECT * FROM OrderDetail";
         DBContext db = new DBContext();
        try ( Connection conn = db.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {
            try ( ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {

                    OrderDetail orderDetail = new OrderDetail();

                    orderDetail.setOrder_detail_id(rs.getInt("OrderDetailID"));
                    orderDetail.setQuantity(rs.getInt("Quanity"));
                    orderDetail.setUnit_price(rs.getDouble("UnitPrice"));
                    orderDetail.setTotal_amount(rs.getDouble("TotalAmount"));
                    orderDetail.setOrder_id(rs.getInt("OrderID"));
                    orderDetail.setPro_id(rs.getInt("ProID"));
                    orderDetails.add(orderDetail);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orderDetails;
    }
     public static void main(String[] args) {
        OrderDetailDAO nn= new OrderDetailDAO();
        List<OrderDetail>details= nn.getAllOrderDetail();
         for (OrderDetail detail : details) {
             System.out.println(detail.getQuantity());
         }
    }
}
