package com.example.xerox;

public class Card {

    String cardno;
    String cardname;
    String date;
    String cvv;

    public Card(String cardno, String cardname, String date, String cvv) {
        this.cardno = cardno;
        this.cardname = cardname;
        this.date = date;
        this.cvv = cvv;
    }

    public String getCardno() {
        return cardno;
    }

    public String getCardname() {
        return cardname;
    }

    public String getDate() {
        return date;
    }

    public String getCvv() {
        return cvv;
    }
}

