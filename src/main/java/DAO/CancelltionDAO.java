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
    public ArrayList<Cancellation> getAll(int currentPage, int pageSize) {
    ArrayList<Cancellation> cancellList = new ArrayList<>();
    String query = "SELECT * FROM Cancellation ORDER BY CancelID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
    
    DB.DBContext ne = new DBContext();
    try (ResultSet rs = ne.execSelectQuery(query, new Object[]{(currentPage - 1) * pageSize, pageSize})) {
        while (rs.next()) {
            cancellList.add(new Cancellation(
                rs.getInt(1), 
                rs.getInt(2),  
                rs.getDate(3),      
                rs.getString(4) 
            ));
        }
    } catch (SQLException ex) {
        Logger.getLogger(CancelltionDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return cancellList;
}

public int getTotalRecords() {
    String query = "SELECT COUNT(*) FROM Cancellation";
    DB.DBContext ne = new DBContext();
    try (ResultSet rs = ne.execSelectQuery(query)) {
        if (rs.next()) {
            return rs.getInt(1);
        }
    } catch (SQLException ex) {
        Logger.getLogger(CancelltionDAO.class.getName()).log(Level.SEVERE, null, ex);
    }
    return 0;
}

}
