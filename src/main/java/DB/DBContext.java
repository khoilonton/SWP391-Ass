/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author PC
 */
/**
 *
 *
 */
public class DBContext {

    private Connection conn;

    public DBContext() {
        try {
        String user = "sa";
        String pass = "1234567";
        String url = "jdbc:sqlserver://localhost:57199;databaseName=Shop_Flower_ver1_2;encrypt=false;trustServerCertificate=true";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection(url, user, pass);
    } catch (SQLException ex) {
        System.err.println("Lỗi SQL: " + ex.getMessage());
    } catch (ClassNotFoundException ex) {
    }
    }

    //Lay connection 
    public Connection getConnection() {
        return conn;
    }
    //Phuong thuc cho cac lenh SELECT(co params)
    public ResultSet execSelectQuery(String query, Object[] params) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
        }
        return preparedStatement.executeQuery();
    }

    //Phuong thuc cho cac lenh select khong co params
    public ResultSet execSelectQuery(String query) throws SQLException {
        return this.execSelectQuery(query, null);
    }
    //Phuong thuc cho cac lenh INSERT UPDATE DELETE 
    public int execQuery(String query, Object[] params) throws SQLException {
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                preparedStatement.setObject(i + 1, params[i]);
            }
        }
        return preparedStatement.executeUpdate();
    }
//    public static void main(String[] args) {
//    DBContext db = new DBContext();
//    if (db.getConnection() != null) {
//        System.out.println("Kết nối thành công!");
//    } else {
//        System.out.println("Kết nối thất bại!");
//    }
//}

}

