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
public class Discount {
    public int dis_id;
    public int minOrder;
    public Date expiry_date;
    public double percent;
    public String code;
    public int used_count;
    public int limit_code;
    public Date start_date;

    public Discount() {
    }

    public Discount(int dis_id, int minOrder, Date expiry_date, double percent, String code, int used_count, int limit_code, Date Start_date) {
        this.dis_id = dis_id;
        this.minOrder = minOrder;
        this.expiry_date = expiry_date;
        this.percent = percent;
        this.code = code;
        this.used_count = used_count;
        this.limit_code = limit_code;
        this.start_date = Start_date;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }



    public int getDis_id() {
        return dis_id;
    }

    public void setDis_id(int dis_id) {
        this.dis_id = dis_id;
    }

    public int getMinOrder() {
        return minOrder;
    }

    public void setMinOrder(int minOrder) {
        this.minOrder = minOrder;
    }

    public Date getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(Date expiry_date) {
        this.expiry_date = expiry_date;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getUsed_count() {
        return used_count;
    }

    public void setUsed_count(int used_count) {
        this.used_count = used_count;
    }

    public int getLimit_code() {
        return limit_code;
    }

    public void setLimit_code(int limit_code) {
        this.limit_code = limit_code;
    }
    
}
