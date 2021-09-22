package com.example.xerox;

import android.os.Parcel;
import android.os.Parcelable;

public class fruit implements Parcelable {
    private String Fcode;
    private String Fname;
    private String Fprice;



    public fruit() {
    }

    protected fruit(Parcel in) {
        Fcode = in.readString();
        Fname = in.readString();
        Fprice = in.readString();
    }

    public static final Creator<fruit> CREATOR = new Creator<fruit>() {
        @Override
        public fruit createFromParcel(Parcel in) {
            return new fruit(in);
        }

        @Override
        public fruit[] newArray(int size) {
            return new fruit[size];
        }
    };

    public fruit(String fcode, String fname, String fprice) {
this.Fcode=fcode;
this.Fname=fname;
this.Fprice=fprice;
    }

    public String getFcode() {
        return Fcode;
    }

    public void setFcode(String Fcode) {
        this.Fcode = Fcode;
    }

    public String getFname() {
        return Fname;
    }

    public void setFname(String Fname) {
        this.Fname= Fname;
    }

    public String getFprice() {
        return Fprice;
    }

    public void setFprice(String Fprice) {
        this.Fprice = Fprice;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Fcode);
        parcel.writeString(Fname);
        parcel.writeString(Fprice);
    }
}
