/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author TrangTrongKhoi-CE180958
 */
public class Order {

    private int order_id;
    private Date oder_date;
    private double total_amount;
    private String cancell;
    private String status;
    private int cus_id;
    private int dis_id;
    private int promo_id;
    private int used_discount;

    private String feedbackContent;
    private Payment payment;

    private List<OrderDetail> orderDetails = new ArrayList<>();

    public Order() {
    }

    public Order(int order_id, Date oder_date, double total_amount, String cancell, String status, int cus_id, int dis_id, int promo_id, int used_discount) {
        this.order_id = order_id;
        this.oder_date = oder_date;
        this.total_amount = total_amount;
        this.cancell = cancell;
        this.status = status;
        this.cus_id = cus_id;
        this.dis_id = dis_id;
        this.promo_id = promo_id;
        this.used_discount = used_discount;
    }

    public List<OrderDetail> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<OrderDetail> orderDetails) {
        this.orderDetails = orderDetails;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Date getOder_date() {
        return oder_date;
    }

    public void setOder_date(Date oder_date) {
        this.oder_date = oder_date;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public String getCancell() {
        return cancell;
    }

    public void setCancell(String cancell) {
        this.cancell = cancell;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCus_id() {
        return cus_id;
    }

    public void setCus_id(int cus_id) {
        this.cus_id = cus_id;
    }

    public int getDis_id() {
        return dis_id;
    }

    public void setDis_id(int dis_id) {
        this.dis_id = dis_id;
    }

    public int getPromo_id() {
        return promo_id;
    }

    public void setPromo_id(int promo_id) {
        this.promo_id = promo_id;
    }

    public int getUsed_discount() {
        return used_discount;
    }

    public void setUsed_discount(int used_discount) {
        this.used_discount = used_discount;
    }

    // Hỗ trợ JSP để hiển thị dữ liệu tốt hơn
    public boolean isUsedDiscount() {
        return used_discount == 1; // 1 là true, 0 là false
    }

    // Trả về chuỗi "N/A" nếu giá trị là 0
    public String getDisIdDisplay() {
        return dis_id == 0 ? "N/A" : String.valueOf(dis_id);
    }

    public String getPromoIdDisplay() {
        return promo_id == 0 ? "N/A" : String.valueOf(promo_id);
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public String getFeedbackContent() {
        return feedbackContent;
    }

    public void setFeedbackContent(String feedbackContent) {
        this.feedbackContent = feedbackContent;
    }

}
