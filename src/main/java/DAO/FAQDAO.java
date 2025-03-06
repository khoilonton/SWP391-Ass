/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DB.DBContext;
import Model.FAQ;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TrangTrongKhoi-CE180958
 */
public class FAQDAO {
       private DBContext dbContext;

    public FAQDAO() {
        dbContext = new DBContext(); // Khởi tạo để lấy kết nối
    }

    public List<FAQ> getAll() {
        List<FAQ> faqs = new ArrayList<>();
        String sql = "SELECT * FROM FAQs";

        try ( Connection conn = dbContext.getConnection();  Statement stmt = conn.createStatement();  ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                FAQ faq = mapResultSetToFAQ(rs);
                faqs.add(faq);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return faqs;
    }

    private FAQ mapResultSetToFAQ(ResultSet rs) throws SQLException {
        FAQ faq = new FAQ();
        faq.setFaqID(rs.getInt("FaqID")); // Sửa tên cột từ "id" thành "FaqID"
        faq.setQuestion(rs.getString("Question"));
        faq.setAnswer(rs.getString("Answer"));
        return faq;
    }
}
