/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DB.DBContext;
import Model.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TrangTrongKhoi-CE180958
 */
public class CategoryDAO {
      public List<Category> getAllCategories() {
    List<Category> list = new ArrayList<>();
    String query = "SELECT * FROM Category";
    
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query);
         ResultSet rs = ps.executeQuery()) {
        
        while (rs.next()) {
            list.add(new Category(
                rs.getInt("CatID"),
                rs.getString("CatName")
            ));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}

  public Category getCategoryByID(int catID) {
    String query = "SELECT CatID, CatName FROM Category WHERE CatID = ?";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, catID);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return new Category(rs.getInt("CatID"), rs.getString("CatName"));
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}

    
    public boolean isCategoryIdExists(int catID) {
    String query = "SELECT COUNT(*) FROM Category WHERE CatID = ?";
    
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, catID);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next() && rs.getInt(1) > 0) {
                return true; // ID đã tồn tại
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}

    
 public boolean addCategory(int catID, String catName) {
    if (isCategoryIdExists(catID)) {
        return false; // Không cho phép thêm nếu ID đã tồn tại
    }

    String query = "INSERT INTO Category (CatID, CatName) VALUES (?, ?)";
    
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, catID);
        ps.setString(2, catName);

        int result = ps.executeUpdate();
        return result > 0;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}

public boolean deleteCategory(int catID) {
    String query = "DELETE FROM Category WHERE CatID = ?";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setInt(1, catID);
        int result = ps.executeUpdate();
        return result > 0;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}
public boolean updateCategory(int catID, String catName) {
    String query = "UPDATE Category SET CatName = ? WHERE CatID = ?";
    try (Connection conn = new DBContext().getConnection();
         PreparedStatement ps = conn.prepareStatement(query)) {
        ps.setString(1, catName);
        ps.setInt(2, catID);
        return ps.executeUpdate() > 0;
    } catch (Exception e) {
        e.printStackTrace();
    }
    return false;
}
}
