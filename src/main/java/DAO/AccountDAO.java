/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Model.Account;
import DB.DBContext;
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

    public ArrayList<Account> getAll() {
        ArrayList<Account> AccountList = new ArrayList<>();
        String query = "select *\n"
                + "from Account as a \n"
                + "where a.Role<>'admin'";
        DB.DBContext ne = new DBContext();
        try ( ResultSet rs = ne.execSelectQuery(query)) {
            while (rs.next()) {
                AccountList.add(new Account(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error retrieving movies");
        }
        return AccountList;
    }

    public static void main(String[] args) {
        AccountDAO accDao = new AccountDAO();
        ArrayList<Account> list = accDao.getAll();
        for (Account account : list) {
            System.out.println(account.getEmail());
        }
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
}
