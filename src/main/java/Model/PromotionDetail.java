/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author TrangTrongKhoi-CE180958
 */
public class PromotionDetail {
    private int promodetail_id;
    private int promo_id;
    private int pro_id;
    private Product product;
    private Promotion promotion;
    

    public PromotionDetail() {
    }

    public PromotionDetail(int promodetail_id, int promo_id, int pro_id) {
        this.promodetail_id = promodetail_id;
        this.promo_id = promo_id;
        this.pro_id = pro_id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public int getPromodetail_id() {
        return promodetail_id;
    }

    public void setPromodetail_id(int promodetail_id) {
        this.promodetail_id = promodetail_id;
    }

    public int getPromo_id() {
        return promo_id;
    }

    public void setPromo_id(int promo_id) {
        this.promo_id = promo_id;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }
    
}
