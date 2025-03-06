/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DB.DBContext;
import Model.Account;
import Model.Customer;
import Model.Feeback;
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
public class FeebackDAO {

    public ArrayList<Feeback> getAll() {
        ArrayList<Feeback> FeeList = new ArrayList<>();
        String query = " select *\n"
                + "   from Feedback";
        DB.DBContext ne = new DBContext();
        try ( ResultSet rs = ne.execSelectQuery(query)) {
            while (rs.next()) {
                FeeList.add(new Feeback(
                        rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getInt(4), rs.getString(5)
                ));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeebackDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error retrieving movies");
        }
        return FeeList;
    }

    public Map<Integer, List<Customer>> getNameCus() {
        Map<Integer, List<Customer>> nameList = new HashMap<>();
        String query = "  select f.FeedbackID,c.FullName\n"
                + " from Feedback as f\n"
                + " join Customer as c on f.CustomerID=c.CustomerID";
        DBContext ne = new DBContext();
        try ( ResultSet rs = ne.execSelectQuery(query)) {
            while (rs.next()) {
                int feeId = rs.getInt(1);
                nameList.putIfAbsent(feeId, new ArrayList<Customer>());
                Customer cus = new Customer(0, rs.getString(2), 0, null, 0);
                nameList.get(feeId).add(cus);
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeebackDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return nameList;

    }

    public boolean updateStatus(int feeback_id, String status) {
        String query = "update Feedback set Status = ? where FeedbackID = ?";
        DBContext db = new DBContext();
        Object[]param={status, feeback_id};
        try {
            return db.execQuery(query, param) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FeebackDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean delete(int feedbackID) {
        String query = "delete  from Feedback where FeedbackID = ?";
        DBContext db = new DBContext();
        try {
            int result = db.execQuery(query, new Object[]{feedbackID});
            return result > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FeebackDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static void main(String[] args) {
        FeebackDAO feeDAO = new FeebackDAO();
        ArrayList<Feeback> list = feeDAO.getAll();
        for (Feeback account : list) {
            System.out.println(account.getStatus());
        }
    }
}
