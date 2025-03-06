/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DB.DBContext;
import Model.Staff;
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
public class StaffDAO {

    DBContext db = new DBContext();

    public List<Staff> getAllStaff() {
        List<Staff> staffList = new ArrayList<>();
        String query = "SELECT * FROM Staff";

        try ( Connection conn = db.getConnection();  PreparedStatement ps = conn.prepareStatement(query);  ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Staff staff = new Staff(
                        rs.getInt("StaffID"),
                        rs.getString("FullName"),
                        rs.getString("Address"),
                        rs.getInt("Phone"),
                        rs.getDate("DateJoin"),
                        rs.getInt("Salary"),
                        rs.getInt("UserID")
                );
                staffList.add(staff);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching all staff: " + e.getMessage());
        }
        return staffList;
    }

    public boolean updateSelfStaffInfo(int staffId, String address, int phone, String fileName) {
        String sql = "UPDATE Staff SET Address = ?, Phone = ?, Img = ? WHERE StaffID = ?";
        try (Connection conn = db.getConnection();  PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, address);
            stmt.setInt(2, phone);
            stmt.setString(3, fileName);
            stmt.setInt(4, staffId);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getStaffIDByUserID(int userID) {
        int staffID = 0;
        String sql = "SELECT StaffID FROM Staff WHERE UserID = ?";
        try ( Connection conn = db.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userID);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    staffID = rs.getInt("StaffID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return staffID;
    }

    public Staff getStaffByID(int staffID) {
        String query = "SELECT * FROM Staff WHERE StaffID = ?";
        try ( Connection conn = db.getConnection();  PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, staffID);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Staff(
                            rs.getInt("StaffID"),
                            rs.getString("FullName"),
                            rs.getString("Address"),
                            rs.getInt("Phone"),
                            rs.getDate("DateJoin"),
                            rs.getInt("Salary"),
                            rs.getInt("UserID")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching staff by ID: " + e.getMessage());
        }
        return null;
    }

    public boolean isStaffIDExists(int staffID) {
        String query = "SELECT COUNT(*) FROM Staff WHERE StaffID = ?";
        try ( Connection conn = new DBContext().getConnection();  PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, staffID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0; // Nếu COUNT > 0 thì StaffID đã tồn tại
            }
        } catch (SQLException e) {
            System.err.println("Error checking StaffID: " + e.getMessage());
        }
        return false;
    }

    public boolean isUserIDExists(int userID) {
        String query = "SELECT COUNT(*) FROM Account WHERE UserID = ?";
        try ( Connection conn = db.getConnection();  PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userID);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; // Nếu UserID đã tồn tại trong Account
                }
            }
        } catch (SQLException e) {
            System.err.println("Error checking UserID in Account: " + e.getMessage());
        }
        return false;
    }

    public boolean addStaff(Staff staff, String username, String password) {
        if (isUserIDExists(staff.getUserID())) {
            System.out.println("UserID đã tồn tại trong bảng Account!");
            return false;
        }

        Connection conn = null;
        PreparedStatement psAccount = null;
        PreparedStatement psStaff = null;

        try {
            conn = new DBContext().getConnection();
            conn.setAutoCommit(false);

            // Tạo Email từ Username
            String email = username.toLowerCase() + "@gmail.com";

            // Thêm vào bảng Account
            String insertAccountQuery = "INSERT INTO Account (UserID, Email, Role, Username, Password, Status) VALUES (?, ?, ?, ?, ?, ?)";
            psAccount = conn.prepareStatement(insertAccountQuery);
            psAccount.setInt(1, staff.getUserID());
            psAccount.setString(2, email);
            psAccount.setString(3, "staff");
            psAccount.setString(4, username);
            psAccount.setString(5, password);
            psAccount.setString(6, "active");
            psAccount.executeUpdate();

            // Thêm vào bảng Staff
            String insertStaffQuery = "INSERT INTO Staff (StaffID, FullName, Address, Phone, DateJoin, Salary, UserID) VALUES (?, ?, ?, ?, ?, ?, ?)";
            psStaff = conn.prepareStatement(insertStaffQuery);
            psStaff.setInt(1, staff.getStaffID());
            psStaff.setString(2, staff.getName());
            psStaff.setString(3, staff.getAddress());
            psStaff.setInt(4, staff.getPhone());
            psStaff.setDate(5, staff.getDateJoin());
            psStaff.setDouble(6, staff.getSalary());
            psStaff.setInt(7, staff.getUserID());
            psStaff.executeUpdate();

            conn.commit();
            return true;
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                if (psAccount != null) {
                    psAccount.close();
                }
                if (psStaff != null) {
                    psStaff.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateStaff(Staff staff, String username, String password, String email) {
        String updateStaffQuery = "UPDATE Staff SET FullName = ?, Address = ?, Phone = ?, DateJoin = ?, Salary = ?, UserID = ? WHERE StaffID = ?";
        String updateAccountQuery = "UPDATE Account SET Username = ?, Password = ?, Email = ? WHERE UserID = ?";

        try ( Connection conn = db.getConnection();  PreparedStatement psStaff = conn.prepareStatement(updateStaffQuery);  PreparedStatement psAccount = conn.prepareStatement(updateAccountQuery)) {

            // Update Staff table
            psStaff.setString(1, staff.getName());
            psStaff.setString(2, staff.getAddress());
            psStaff.setInt(3, staff.getPhone());
            psStaff.setDate(4, staff.getDateJoin());
            psStaff.setDouble(5, staff.getSalary());
            psStaff.setInt(6, staff.getUserID());
            psStaff.setInt(7, staff.getStaffID());

            int staffRowsAffected = psStaff.executeUpdate();

            // Update Account table
            psAccount.setString(1, username);
            psAccount.setString(2, password);
            psAccount.setString(3, email);
            psAccount.setInt(4, staff.getUserID());

            int accountRowsAffected = psAccount.executeUpdate();

            if (staffRowsAffected > 0 && accountRowsAffected > 0) {
                System.out.println("Staff and Account updated successfully.");
            } else {
                System.out.println("No staff or account found with the given UserID.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating staff and account: " + e.getMessage());
        }
    }

    public void deleteStaff(int staffID) {
        String getUserIDQuery = "SELECT UserID FROM Staff WHERE StaffID = ?";
        String deleteStaffQuery = "DELETE FROM Staff WHERE StaffID = ?";
        String deleteAccountQuery = "DELETE FROM Account WHERE UserID = ?";

        try ( Connection conn = db.getConnection();  PreparedStatement psGetUserID = conn.prepareStatement(getUserIDQuery);  PreparedStatement psDeleteStaff = conn.prepareStatement(deleteStaffQuery);  PreparedStatement psDeleteAccount = conn.prepareStatement(deleteAccountQuery)) {

            conn.setAutoCommit(false); // Bắt đầu transaction

            // Lấy UserID từ Staff
            psGetUserID.setInt(1, staffID);
            ResultSet rs = psGetUserID.executeQuery();
            int userID = -1;
            if (rs.next()) {
                userID = rs.getInt("UserID");
            }

            // Xóa Staff
            psDeleteStaff.setInt(1, staffID);
            int staffDeleted = psDeleteStaff.executeUpdate();

            // Nếu xóa thành công Staff thì xóa luôn tài khoản Account
            if (staffDeleted > 0 && userID != -1) {
                psDeleteAccount.setInt(1, userID);
                psDeleteAccount.executeUpdate();
            }

            conn.commit(); // Xác nhận transaction
            System.out.println("Staff and Account deleted successfully.");
        } catch (SQLException e) {
            System.err.println("Error deleting staff: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        StaffDAO sDao = new StaffDAO();
        List<Staff> list = sDao.getAllStaff();
        for (Staff staff : list) {
            System.out.println(staff.getName());
        }
    }
}
