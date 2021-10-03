package com.example.xerox.classes;

import java.util.Date;

public class SupOrder {
    private String fcode;
    private String fname;
    private String fprice;
    private String  quantity;
    private String date;
    private String time;

    public SupOrder() {
    }

    public SupOrder(String fcode, String fname, String fprice, String quantity, String date, String time) {
        this.fcode = fcode;
        this.fname = fname;
        this.fprice = fprice;
        this.quantity = quantity;
        this.date = date;
        this.time = time;
    }

    public String getFcode() {
        return fcode;
    }

    public void setFcode(String fcode) {
        this.fcode = fcode;
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

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
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
