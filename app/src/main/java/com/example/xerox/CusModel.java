package com.example.xerox;

public class CusModel {

    String cusprice,cusqty,itemName;

    CusModel(){

    }

    public CusModel(String cusprice, String cusqty, String itemName) {
        this.cusprice = cusprice;
        this.cusqty = cusqty;
        this.itemName = itemName;
    }

    public String getCusprice() {
        return cusprice;
    }

    public void setCusprice(String cusprice) {
        this.cusprice = cusprice;
    }

    public String getCusqty() {
        return cusqty;
    }

    public void setCusqty(String cusqty) {
        this.cusqty = cusqty;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
}
