package com.example.xerox;

import android.os.Parcel;
import android.os.Parcelable;

public class fruit implements Parcelable {
    private String Fcode;
    private String Fname;
    private String Fprice;
    private String Fcusprice;
    private String Fimglink;


    public fruit() {
    }

    protected fruit(Parcel in) {
        Fcode = in.readString();
        Fname = in.readString();
        Fprice = in.readString();
        Fcusprice=in.readString();
        Fimglink=in.readString();
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

    public fruit(String fcode, String fname, String fprice,String fcusprice,String fimglink) {
this.Fcode=fcode;
this.Fname=fname;
this.Fprice=fprice;
this.Fcusprice=fcusprice;
this.Fimglink=fimglink;
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

    public String getFcusprice() {
        return Fcusprice;
    }

    public void setFcusprice(String Fcusprice) {
        this.Fcusprice = Fcusprice;
    }

    public String getFimglink() {
        return Fimglink;
    }

    public void setFimglink(String Fimglink) {
        this.Fimglink = Fimglink;
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
        parcel.writeString(Fcusprice);
        parcel.writeString(Fimglink);
    }
}
