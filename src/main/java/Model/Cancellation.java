/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author TrangTrongKhoi-CE180958
 */
public class Cancellation {
    private int cancell_id;
    private int order_id;
    private Date date;
    private String reason;

    public Cancellation() {
    }

    public Cancellation(int cancell_id, int order_id, Date date, String reason) {
        this.cancell_id = cancell_id;
        this.order_id = order_id;
        this.date = date;
        this.reason = reason;
    }

    public int getCancell_id() {
        return cancell_id;
    }

    public void setCancell_id(int cancell_id) {
        this.cancell_id = cancell_id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


    
    
}
