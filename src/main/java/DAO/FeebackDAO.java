/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DB.DBContext;
import Model.Account;
import Model.Feeback;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TrangTrongKhoi-CE180958
 */
public class FeebackDAO {

    public ArrayList<Feeback> getAll() {
        ArrayList<Feeback> FeeList = new ArrayList<>();
        String query = "select f.FeedbackID, f.Content, f.CustomerID, f.OrderID, f.Status, c.CustomerID, c.FullName\n"
                + " from Feedback f \n"
                + "join Customer c on f.CustomerID = c.CustomerID";
        DB.DBContext ne = new DBContext();
        try ( ResultSet rs = ne.execSelectQuery(query)) {
            while (rs.next()) {
                FeeList.add(new Feeback(
                        rs.getInt(1), 
                        rs.getString(2), 
                        rs.getInt(3), 
                        rs.getInt(4), 
                        rs.getString(5), 
                        rs.getInt(6), 
                        rs.getString(7))) 
                ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeebackDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error retrieving movies");
        }
        return FeeList;
    }

    public boolean updateStatus(int feeback_id, String status) {
        String query = "UPDATE Feedback SET Status = ? WHERE FeedbackID = ?";
        DBContext db = new DBContext();
        try {
            return db.execQuery(query, new Object[]{status, feeback_id}) > 0;
        } catch (SQLException ex) {
            Logger.getLogger(FeebackDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean delete(int feedbackID) {
        String query = "DELETE FROM Feedback WHERE FeedbackID = ?";
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
