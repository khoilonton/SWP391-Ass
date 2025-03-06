/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DB.DBContext;
import Model.Discount;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TrangTrongKhoi-CE180958
 */
public class DiscountDAO {

    public ArrayList<Discount> getALl() {
        ArrayList<Discount> DisList = new ArrayList<>();
        String query = "select *\n"
                + "from Discount";
        DB.DBContext ne = new DBContext();
        try ( ResultSet rs = ne.execSelectQuery(query)) {
            while (rs.next()) {
                DisList.add(
                        new Discount(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getDouble(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getDate(8))
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(FeebackDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error retrieving movies");
        }
        return DisList;

    }

    public Discount getByid(int id) {
        String query = "select *\n"
                + "from Discount\n"
                + "where DisID=?";
        Object[] param = {id};
        DB.DBContext ne = new DBContext();
        try ( ResultSet rs = ne.execSelectQuery(query, param)) {
            if (rs.next()) {
                return new Discount(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getDouble(4), rs.getString(5), rs.getInt(6), rs.getInt(7), rs.getDate(8));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CustomerDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;

    }

    public int create(Discount discount) {
        String getNextID = "select max(DisID)+1 as nextID \n"
                + "from Discount";
        DB.DBContext ne = new DBContext();
        try ( ResultSet rs = ne.execSelectQuery(getNextID)) {
            if (rs.next()) {
                int nextID = rs.getInt(1);
                String query = "INSERT INTO Discount VALUES (?,?, ?, ?, ?, ?,?,?)";
                Object[] params = {nextID,
                    discount.getMinOrder(),
                    discount.getExpiry_date(),
                    discount.getPercent(),
                    discount.getCode(),
                    discount.getUsed_count(),
                    discount.getLimit_code(),
                    discount.getStart_date()
                };
                return ne.execQuery(query, params);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int update(Discount discount) {
        String query = "update Discount set MinOrderValue=?,Expiry_date=?,Percent_discount=?,Code=?,Used_count=?,Limit_code=?,Start_date=? \n"
                + "where DisID = ?";
        DB.DBContext ne = new DBContext();
        Object[] params = {
            discount.getMinOrder(),
            discount.getExpiry_date(),
            discount.getPercent(),
            discount.getCode(),
            discount.getUsed_count(),
            discount.getLimit_code(),
            discount.getStart_date(),
            discount.getDis_id()
        };
        try {
            return ne.execQuery(query, params);
        } catch (SQLException ex) {
            Logger.getLogger(DiscountDAO.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public int delete(int id) {
        String sql = "delete from Discount where DisID = ?";
        Object[] params = {id};
        DB.DBContext ne = new DBContext();
        try {
            return ne.execQuery(sql, params);

        } catch (SQLException ex) {
            return 0;
        }
    }

    public static void main(String[] args) {
        DiscountDAO disDAO = new DiscountDAO();
        ArrayList<Discount> list = disDAO.getALl();
        for (Discount account : list) {
            System.out.println(account.getStart_date());
        }
    }
}
