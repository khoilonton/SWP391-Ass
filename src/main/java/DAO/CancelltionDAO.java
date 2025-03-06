/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DB.DBContext;
import Model.Cancellation;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TrangTrongKhoi-CE180958
 */
public class CancelltionDAO {
    public ArrayList<Cancellation> getAll(){
    ArrayList<Cancellation>cancellList= new ArrayList<>();
    String query="select * from Cancellation";
     DB.DBContext ne = new DBContext();
        try ( ResultSet rs = ne.execSelectQuery(query)) {
            while (rs.next()) {
                cancellList.add(
                    new Cancellation(rs.getInt(1), rs.getInt(2), rs.getDate(3), rs.getString(4))
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(CancelltionDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error retrieving movies");
        }
        return cancellList;
    
    
    }
    public static void main(String[] args) {
       CancelltionDAO caDAO = new CancelltionDAO();
         ArrayList<Cancellation>cancellList=caDAO.getAll();
         for (Cancellation cancellation : cancellList) {
             System.out.println(cancellation.getOrder_id());
        }
    }
}
