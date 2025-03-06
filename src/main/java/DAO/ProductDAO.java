/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DB.DBContext;
import Model.Product;
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
public class ProductDAO {
      public double getTotalSales() {
        double totalSales = 0;
        String query = "SELECT SUM(TotalAmount) FROM Orders WHERE Status = 'Delivered'";
        try ( Connection conn = new  DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(query);  ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                totalSales = rs.getDouble(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalSales;
    }

    public double getTotalCosts() {
        double totalSales = getTotalSales();
        return totalSales * 0.7; // Giả định chi phí là 70%
    }

    public List<Product> getTop10ProductsByQuanity() {
        List<Product> products = new ArrayList<>();
        String query = "SELECT TOP 10 ProID, ProName, Description, Quanity, ProPrice, Img, Expiry_date FROM Product ORDER BY Quanity DESC";
        try ( Connection conn = new  DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(query);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Product product = new Product();
                product.setPro_id(rs.getInt("ProID"));
                product.setName(rs.getString("ProName"));
                product.setDescription(rs.getString("Description"));
                product.setQuantity(rs.getInt("Quanity"));
                product.setPrice(rs.getInt("ProPrice"));
                product.setImg(rs.getString("Img"));
                product.setExpiry_date(rs.getDate("Expiry_date"));
                products.add(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
    
  public List<Product> getProductsByPage(int page, int pageSize) {
    List<Product> list = new ArrayList<>();
    String query = "SELECT ProID, Expiry_date, Description, Stock_status, Img, ProName, Quanity, CatID, ProPrice, StartDate "
                 + "FROM Product ORDER BY ProID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        
        int offset = (page - 1) * pageSize;
        ps.setInt(1, offset);
        ps.setInt(2, pageSize);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            list.add(new Product(
                    rs.getInt("ProID"),
                    rs.getDate("Expiry_date"),
                    rs.getString("Description"),
                    rs.getString("Stock_status"),
                    rs.getString("Img"),
                    rs.getString("ProName"),
                    rs.getInt("Quanity"),
                    rs.getInt("CatID"),
                    rs.getInt("ProPrice"),
                    rs.getDate("StartDate")
            ));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

public int getTotalProducts() {
    String query = "SELECT COUNT(*) FROM Product";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {
        if (rs.next()) {
            return rs.getInt(1);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return 0;
}

 
    public void deleteProduct(int proID) {
        String query = "DELETE FROM Product WHERE ProID = ?";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, proID);
            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Product deleted successfully.");
            } else {
                System.out.println("No product found with ID: " + proID);
            }
        } catch (SQLException e) {
            System.err.println("Error deleting product: " + e.getMessage());
        }
    }
    
    public boolean checkProductIDExists(int proID) {
    String sql = "SELECT COUNT(*) FROM Product WHERE ProID = ?";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        
        ps.setInt(1, proID);
        ResultSet rs = ps.executeQuery();
        if (rs.next() && rs.getInt(1) > 0) {
            return true; // ID đã tồn tại
        }
    } catch (SQLException e) {
        System.err.println("Error checking Product ID: " + e.getMessage());
    }
    return false;
}


   public boolean addProduct(Product product) {
    String sql = "INSERT INTO Product (ProID, ProName, Expiry_date, Description, Stock_status, Img, Quanity, CatID, ProPrice, StartDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?,?)";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, product.getPro_id());  
        ps.setString(2, product.getName());
        ps.setDate(3, product.getExpiry_date());
        ps.setString(4, product.getDescription());
        ps.setString(5, product.getStock_status());
        ps.setString(6, product.getImg());
        ps.setInt(7, product.getQuantity());
        ps.setInt(8, product.getCat_id());
        ps.setDouble(9, product.getPrice());
         ps.setDate(10, product.getStart_end());

        int rowsInserted = ps.executeUpdate();
        return rowsInserted > 0;
    } catch (SQLException e) {
        System.err.println("Error adding product: " + e.getMessage());
        return false;
    }
}



    public Product getProductByID(int proID) {
        String query = "SELECT * FROM Product WHERE ProID = ?";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, proID);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getInt("ProID"),
                            rs.getDate("Expiry_date"),
                            rs.getString("Description"),
                            rs.getString("Stock_status"),
                            rs.getString("Img"),
                            rs.getString("ProName"),
                            rs.getInt("Quanity"),
                            rs.getInt("CatID"),
                            rs.getInt("ProPrice"),
                            rs.getDate("StartDate")
                    );
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null; // Trả về null nếu không tìm thấy sản phẩm
    }

    public void updateProduct(Product product) {
        String query = "UPDATE Product SET ProName = ?, Expiry_date = ?, Description = ?, Stock_status = ?, Img = ?, Quanity = ?, CatID = ?, ProPrice = ?,StartDate=? WHERE ProID = ?";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, product.getName());
            ps.setDate(2, product.getExpiry_date());
            ps.setString(3, product.getDescription());
            ps.setString(4, product.getStock_status());
            ps.setString(5, product.getImg());
            ps.setInt(6, product.getQuantity());
            ps.setInt(7, product.getCat_id());
            ps.setDouble(8, product.getPrice());
            ps.setInt(10, product.getPro_id());
            ps.setDate(9,product.getStart_end());

            int rowsUpdated = ps.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Product updated successfully.");
            } else {
                System.out.println("No product found with ID: " + product.getPro_id());
            }
        } catch (SQLException e) {
            System.err.println("Error updating product: " + e.getMessage());
        }
    }
    
    
    
//    public static void main(String[] args) {
//        ProductDAO pDao= new ProductDAO();
//        List<Product> list= pDao.getAllProducts();
//        for (Product product : list) {
//            System.out.println(product.getName());
//        }
//    }
}
