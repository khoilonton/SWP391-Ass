/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DB.DBContext;
import Model.Customer;
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
public class CustomerDAO {

    public ArrayList<Customer> getAll() {
        ArrayList<Customer> cusList = new ArrayList<>();
        String query = "select * \n"
                + "from [dbo].[Customer]";
        DB.DBContext ne = new DBContext();
        try ( ResultSet rs = ne.execSelectQuery(query)) {
            while (rs.next()) {
                cusList.add(new Customer(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getInt(5)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeebackDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error retrieving movies");
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
                        rs.getInt(1),rs.getString(2),rs.getInt(3),rs.getString(4),rs.getInt(5)
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
    try (ResultSet rs = ne.execSelectQuery(query, new Object[]{cus_id})) {
        if (rs.next()) {
            totalInvoices = rs.getInt(1);
        }
    } catch (SQLException ex) {
        Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return totalInvoices;
}
    public static void main(String[] args) {
        CustomerDAO cusDAO = new CustomerDAO();
        ArrayList<Customer> list = cusDAO.getAll();
        for (Customer account : list) {
            System.out.println(account.getName());
        }
        System.out.println(cusDAO.getByid(1).getName());
       
    }
}
