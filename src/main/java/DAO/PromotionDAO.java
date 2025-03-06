/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DB.DBContext;
import Model.Product;
import Model.Promotion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author TrangTrongKhoi-CE180958
 */
public class PromotionDAO {

    public ArrayList<Promotion> getAll(int currentPage, int pageSize) {
        ArrayList<Promotion> promoList = new ArrayList<>();
        String query = "select * from Promotion ORDER BY PromoID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        DB.DBContext ne = new DBContext();
        try ( ResultSet rs = ne.execSelectQuery(query, new Object[]{(currentPage - 1) * pageSize, pageSize})) {
            while (rs.next()) {
                promoList.add(new Promotion(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getDouble(6)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromotionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return promoList;
    }

    public Map<Integer, List<Product>> getPromoProduct(int currentPage, int pageSize) {
        Map<Integer, List<Product>> proList = new HashMap<>();
        String query = "select pd.PromoID, pr.ProID, pr.ProName, pr.Expiry_date\n"
                + "from PromotionDetail as pd\n"
                + "join Product as pr on pd.ProId = pr.ProID\n"
                + "where pd.PromoID in (\n"
                + "    select PromoID from Promotion order by PromoID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY\n"
                + ")\n"
                + "ORDER BY pd.PromoID";
        DBContext ne = new DBContext();
        try ( ResultSet rs = ne.execSelectQuery(query, new Object[]{(currentPage - 1) * pageSize, pageSize})) {
            while (rs.next()) {
                int promoid = rs.getInt(1);
                proList.putIfAbsent(promoid, new ArrayList<Product>());
                Product pro = new Product(rs.getInt(2), rs.getDate(4), null, null, null, rs.getString(3), 0, 0, 0, null);
                proList.get(promoid).add(pro);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromotionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return proList;
    }

    public ArrayList<Product> getProduct() {
        ArrayList<Product> ProList = new ArrayList<>();
        String query = "SELECT ProID, ProName, Expiry_date FROM Product";
        DBContext ne = new DBContext();
        try ( ResultSet rs = ne.execSelectQuery(query)) {
            while (rs.next()) {
                Product product = new Product(rs.getInt(1), rs.getDate(3), null, null, null, rs.getString(2), 0, 0, 0, null);
                ProList.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromotionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ProList;

    }

    public int create(Promotion promotion) {
        String getNextID = "select max(PromoID)+1 as nextID \n"
                + "from Promotion";
        DBContext ne = new DBContext();
        try ( ResultSet rs = ne.execSelectQuery(getNextID)) {
            if (rs.next()) {
                int nextID = rs.getInt(1);
                String query = "INSERT INTO Promotion VALUES (?,?, ?, ?, ?, ?)";
                Object[] params = {nextID,
                    promotion.getDescription(),
                    promotion.getName(),
                    promotion.getStart_date(),
                    promotion.getEnd_date(),
                    promotion.getPercent()
                };
                int result = ne.execQuery(query, params);
                return result > 0 ? nextID : 0;
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromotionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public int createPromoProduct(int promo_id, int pro_id) {
        String getNextID = "select max(ProDetailID)+1 as nextID \n"
                + "from PromotionDetail";
        DBContext ne = new DBContext();
        try ( ResultSet rs = ne.execSelectQuery(getNextID)) {
            if (rs.next()) {
                int nextID = rs.getInt(1);
                String query = "INSERT INTO PromotionDetail VALUES (?,?,?)";
                Object[] params = {nextID,
                    promo_id,
                    pro_id
                };  
                return ne.execQuery(query, params);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromotionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Promotion getPromotionById(int promoID) {
        String query = "SELECT * FROM Promotion WHERE PromoID = ?";
        DBContext ne = new DBContext();
        try ( ResultSet rs = ne.execSelectQuery(query, new Object[]{promoID})) {
            if (rs.next()) {
                return new Promotion(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getDate(5), rs.getDouble(6));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean updatePromotion(Promotion promotion) {
        String query = "UPDATE Promotion SET Description = ?, PromotionName = ?, StartDate = ?, EndDate = ?, Percent_discount = ? WHERE PromoID = ?";
        DBContext ne = new DBContext();
        Object[] params = {
            promotion.getDescription(),
            promotion.getName(),
            promotion.getStart_date(),
            promotion.getEnd_date(),
            promotion.getPercent(),
            promotion.getPromo_id()
        };

        try {
            return ne.execQuery(query, params) > 0;
        } catch (SQLException ex) {
            return false;
        }
    }

    public boolean deletePromoProducts(int promoID) {
        String query = "DELETE FROM PromotionDetail WHERE PromoID = ?";
        DBContext ne = new DBContext();
        try {
            return ne.execQuery(query, new Object[]{promoID}) > 0;
        } catch (SQLException ex) {
            return false;
        }
    }

    public List<Integer> getProductsByPromoID(int promoID) {
        List<Integer> productIDs = new ArrayList<>();
        String query = "SELECT ProID FROM PromotionDetail WHERE PromoID = ?";
        DBContext ne = new DBContext();
        try ( ResultSet rs = ne.execSelectQuery(query, new Object[]{promoID})) {
            while (rs.next()) {
                productIDs.add(rs.getInt(1));
            }
        } catch (SQLException ex) {
        }
        return productIDs;
    }

    public boolean deletePromotion(int promoID) {
        DBContext ne = new DBContext();
        try {
            Object[] param = {promoID};
            String deleteDetails = "DELETE FROM PromotionDetail WHERE PromoID = ?";
            ne.execQuery(deleteDetails, param);
            String deletePromo = "DELETE FROM Promotion WHERE PromoID = ?";
            return ne.execQuery(deletePromo, param) > 0;
        } catch (SQLException ex) {
            return false;
        }
    }

    public int getSize() {
        String query = "SELECT COUNT(*) FROM Promotion";
        DBContext ne = new DBContext();
        try ( ResultSet rs = ne.execSelectQuery(query)) {
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromotionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public static void main(String[] args) {
        PromotionDAO proDAO = new PromotionDAO();
        ArrayList<Product> list = proDAO.getProduct();
        for (Product product : list) {
            System.out.println(product.getName());
        }
    }
}
