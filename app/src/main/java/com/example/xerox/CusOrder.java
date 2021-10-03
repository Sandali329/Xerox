package com.example.xerox;

import android.icu.text.SimpleDateFormat;

public class CusOrder {
    private String ItemName;
    private String Cusprice;
    private String  Cusqty;
    private String Cusdate;
    private String Custime;

    public CusOrder(){

    }


    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String itemName) {
        ItemName = itemName;
    }

    public String getCusprice() {
        return Cusprice;
    }

    public void setCusprice(String cusprice) {
        Cusprice = cusprice;
    }

    public String getCusqty() {
        return Cusqty;
    }

    public void setCusqty(String cusqty) {
        Cusqty = cusqty;
    }

    public String getCusdate(String finalCurrentDate) {
        return Cusdate;
    }

    public void setCusdate(String cusdate) {
        Cusdate = cusdate;
    }

    public String getCustime(String finalCurrentTime) {
        return Custime;
    }

    public void setCustime(String custime) {
        Custime = custime;
    }
}
