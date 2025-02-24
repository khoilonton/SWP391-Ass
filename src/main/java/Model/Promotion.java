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
public class Promotion {
    public int promo_id;
    public String description;
    public String name;
    public Date start_date;
    public Date end_date;
    public double percent;

    public Promotion() {
    }

    public Promotion(int promo_id, String description, String name, Date start_date, Date end_date, double percent) {
        this.promo_id = promo_id;
        this.description = description;
        this.name = name;
        this.start_date = start_date;
        this.end_date = end_date;
        this.percent = percent;
    }

    public int getPromo_id() {
        return promo_id;
    }

    public void setPromo_id(int promo_id) {
        this.promo_id = promo_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public double getPercent() {
        return percent;
    }

    public void setPercent(double percent) {
        this.percent = percent;
    }

  
 
}
