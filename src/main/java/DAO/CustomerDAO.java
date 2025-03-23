/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DB.DBContext;
import Model.Account;
import Model.Customer;
import Model.Feeback;
import java.sql.Connection;
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
public class CustomerDAO {

 public ArrayList<Customer> getCustomersByPage(int page, int recordsPerPage) {
    ArrayList<Customer> cusList = new ArrayList<>();
    String query = "SELECT * FROM Customer ORDER BY CustomerID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    DBContext ne = new DBContext();
    try (ResultSet rs = ne.execSelectQuery(query, new Object[]{(page - 1) * recordsPerPage, recordsPerPage})) {
        while (rs.next()) {
            cusList.add(new Customer(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5)));
        }
    } catch (SQLException ex) {
        Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return cusList;
}

    public Customer getByid(int cus_id) {
        String sql = "select *\n"
                + "from [dbo].[Customer]\n"
                + "where CustomerID=?";
        Object[] params = {cus_id};
        DB.DBContext ne = new DBContext();
        try ( ResultSet rs = ne.execSelectQuery(sql, params)) {
            if (rs.next()) {
                return new Customer(
                        rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5)
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getTotalInvoices(int cus_id) {
        String query = "select count(*) from Orders where CustomerID=?";
        int totalInvoices = 0;
        DB.DBContext ne = new DBContext();
        try ( ResultSet rs = ne.execSelectQuery(query, new Object[]{cus_id})) {
            if (rs.next()) {
                totalInvoices = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalInvoices;
    }

    public int getTotalAmount(int cus_id) {
        String query = "SELECT SUM(TotalAmount) FROM Orders WHERE CustomerID=?";
        int totalAmount = 0;
        DB.DBContext ne = new DBContext();
        try ( ResultSet rs = ne.execSelectQuery(query, new Object[]{cus_id})) {
            if (rs.next()) {
                totalAmount = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return totalAmount;
    }

    public Map<Integer, List<Account>> getStatusAcc() {
        Map<Integer, List<Account>> accList = new HashMap<>();
        String query = "select c.CustomerID,a.UserID,a.Status\n"
                + "from Account as a\n"
                + "join Customer as c on a.UserID=c.UserID";
        DBContext ne = new DBContext();
        try ( ResultSet rs = ne.execSelectQuery(query)) {
            while (rs.next()) {
                int accID = rs.getInt(1);
                accList.putIfAbsent(accID, new ArrayList<Account>());
                Account acc = new Account(rs.getInt(2), null, null, null, null, rs.getString(3));
                accList.get(accID).add(acc);
            }

        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return accList;
    }

    public boolean updateAccountStatus(int userId, String newStatus) {
        String query = "update Account set Status = ? where UserID = ?";
        DBContext db = new DBContext();
        try {
            return db.execQuery(query, new Object[]{newStatus, userId}) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    public int getCustomerIDByUserID(int userID) {
        int khachhangID = 0;
        String sql = "SELECT CustomerID FROM Customer WHERE UserID = ?";
         DBContext db = new DBContext();
        try ( Connection conn = db.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userID);
            try ( ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    khachhangID = rs.getInt("CustomerID");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return khachhangID;
    }
public int getTotalCustomers() {
    String query = "SELECT COUNT(*) FROM Customer";
    DBContext ne = new DBContext();
    int count = 0;
    try (ResultSet rs = ne.execSelectQuery(query)) {
        if (rs.next()) {
            count = rs.getInt(1);
        }
    } catch (SQLException ex) {
        Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return count;
}

    private Customer mapResultSetToCustomer(ResultSet rs) throws SQLException {
        Customer customer = new Customer();
        customer.setCus_id(rs.getInt("CustomerID"));
        customer.setName(rs.getString("Name"));
        customer.setPhone(rs.getInt("Phone"));
        customer.setAddress(rs.getString("Address"));
        return customer;
    }

//    public static void main(String[] args) {
//        CustomerDAO cusDAO = new CustomerDAO();
//        ArrayList<Customer> list = cusDAO.getAll();
//        for (Customer account : list) {
//            System.out.println(account.getName());
//        }
//        System.out.println(cusDAO.getByid(1).getName());
//
//    }
}
