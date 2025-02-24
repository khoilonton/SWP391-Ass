/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author TrangTrongKhoi-CE180958
 */
public class Customer {
    private int cus_id;
    private String name;
    private int phone;
    private String address;
    private int user_id;
    private int totalInvoices;

    public int getTotalInvoices() {
        return totalInvoices;
    }

    public void setTotalInvoices(int totalInvoices) {
        this.totalInvoices = totalInvoices;
    }


    public Customer() {
    }

    public Customer(int cus_id, String name, int phone, String address, int user_id) {
        this.cus_id = cus_id;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.user_id = user_id;
    }

    Customer(String name) {
    this.name = name;
    }

    public int getCus_id() {
        return cus_id;
    }

    public void setCus_id(int cus_id) {
        this.cus_id = cus_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    
           
}
