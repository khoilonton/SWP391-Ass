/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author TrangTrongKhoi-CE180958
 */
public class Feeback  {
    private int feeback_id;
    private String content;
    private int customer_id;
    private int order_id;
    private String status;
    private Customer cus;
    public Feeback() {
    }

    public Feeback(int feeback_id, String content, int customer_id, int order_id, String status, int cus_id, String name) {
        this.feeback_id = feeback_id;
        this.content = content;
        this.customer_id = customer_id;
        this.order_id = order_id;
        this.status = status;
       this.cus = new Customer(name);
    }

    public Customer getCus() {
        return cus;
    }

    public void setCus(Customer cus) {
        this.cus = cus;
    }

    public int getFeeback_id() {
        return feeback_id;
    }

    public void setFeeback_id(int feeback_id) {
        this.feeback_id = feeback_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}