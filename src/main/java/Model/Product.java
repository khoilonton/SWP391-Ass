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
public class Product {

    public int pro_id;
    public Date expiry_date;
    public String description;
    public String Stock_status;
    public String img;
    public String name;
    public int quantity;
    public int cat_id;
    public int price;
    public Date start_end;

    public Product() {
    }

    public Product(int pro_id, Date expiry_date, String description, String Stock_status, String img, String name, int quantity, int cat_id, int price, Date start_end) {
        this.pro_id = pro_id;
        this.expiry_date = expiry_date;
        this.description = description;
        this.Stock_status = Stock_status;
        this.img = img;
        this.name = name;
        this.quantity = quantity;
        this.cat_id = cat_id;
        this.price = price;
        this.start_end = start_end;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public Date getExpiry_date() {
        return expiry_date;
    }

    public void setExpiry_date(Date expiry_date) {
        this.expiry_date = expiry_date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStock_status() {
        return Stock_status;
    }

    public void setStock_status(String Stock_status) {
        this.Stock_status = Stock_status;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getStart_end() {
        return start_end;
    }

    public void setStart_end(Date start_end) {
        this.start_end = start_end;
    }

   

}
