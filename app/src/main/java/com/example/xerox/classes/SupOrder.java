package com.example.xerox.classes;

import java.util.Date;

public class SupOrder {
    private String fname;
    private String fprice;
    private String  qty;
    private String date;
    private String time;

    public SupOrder() {
    }

    public SupOrder(String fname, String fprice, String qty, String date, String time) {
        this.fname = fname;
        this.fprice = fprice;
        this.qty = qty;
        this.date = date;
        this.time = time;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getFprice() {
        return fprice;
    }

    public void setFprice(String fprice) {
        this.fprice = fprice;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
