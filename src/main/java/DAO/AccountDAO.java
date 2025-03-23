/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Account;
import DB.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PC
 */
public class AccountDAO {

public ArrayList<Account> getAll(int currentPage, int pageSize) {
    ArrayList<Account> accountList = new ArrayList<>();
    String query = "SELECT * FROM Account WHERE Role <> 'admin' ORDER BY UserID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    DB.DBContext db = new DBContext();
    
    try (ResultSet rs = db.execSelectQuery(query, new Object[]{(currentPage - 1) * pageSize, pageSize})) {
        while (rs.next()) {
            accountList.add(new Account(
                rs.getInt(1), 
                rs.getString(2), 
                rs.getString(3), 
                rs.getString(4), 
                rs.getString(5), 
                rs.getString(6)
            ));
        }
    } catch (SQLException ex) {
        Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return accountList;
}
public int getTotalAccounts() {
    String query = "SELECT COUNT(*) FROM Account WHERE Role <> 'admin'";
    DBContext db = new DBContext();
    
    try (ResultSet rs = db.execSelectQuery(query, new Object[]{})) {
        if (rs.next()) {
            return rs.getInt(1);
        }
    } catch (SQLException ex) {
        Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    
    return 0;
}


//    public static void main(String[] args) {
//        AccountDAO accDao = new AccountDAO();
//        ArrayList<Account> list = accDao.getAll();
//        for (Account account : list) {
//            System.out.println(account.getEmail());
//        }
//    }

    // Lấy tài khoản theo UserID
    public Account getAccountByUserID(int userID) {
        String query = "SELECT * FROM Account WHERE UserID = ?";
        DBContext db = new DBContext();
        
        try (Connection conn = db.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setInt(1, userID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapResultSetToAccount(rs);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    // Cập nhật trạng thái tài khoản
    public boolean updateAccountStatus(int userId, String newStatus) {
        String query = "UPDATE Account SET Status = ? WHERE UserID = ?";
        DBContext db = new DBContext();
        Object[] param = {newStatus, userId};
        
        try {
            return db.execQuery(query, param) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }


        public Account authenticate(String username, String password) {
        Account account = null;
        String sql = "SELECT * FROM Account WHERE Username = ? AND Password = ?";
        DBContext db = new DBContext();
        try ( Connection conn = db.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username.trim());
            ps.setString(2, password.trim());
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    account = new Account();
                    account.setUserID(rs.getInt("UserID"));
                    account.setUserName(rs.getString("Username"));
                    account.setRole(rs.getString("Role"));
                    account.setEmail(rs.getString("Email"));
                    account.setPassword(rs.getString("Password"));
                    account.setStatus(rs.getString("Status"));
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        return account;
    }
           private Account mapResultSetToAccount(ResultSet rs) throws SQLException {
        Account account = new Account();
        account.setUserID(rs.getInt("UserID"));
        account.setEmail(rs.getString("Email"));
        account.setRole(rs.getString("Role"));
        account.setUserName(rs.getString("Username"));
        account.setPassword(rs.getString("Password"));
        account.setStatus(rs.getString("Status"));
        return account;
    }
}
