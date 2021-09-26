
package com.example.xerox;

import android.os.Parcel;
import android.os.Parcelable;

public class vegetable implements Parcelable {
    private String Vcode;
    private String Vname;
    private String Vprice;
    private String Vcusprice;
    private String Vimglink;


    public vegetable() {
    }

    protected vegetable(Parcel in) {
        Vcode = in.readString();
        Vname = in.readString();
        Vprice = in.readString();
        Vcusprice=in.readString();
        Vimglink=in.readString();
    }

    public static final Creator<vegetable> CREATOR = new Creator<vegetable>() {
        @Override
        public vegetable createFromParcel(Parcel in) {
            return new vegetable(in);
        }

        @Override
        public vegetable[] newArray(int size) {
            return new vegetable[size];
        }
    };

    public vegetable(String Vcode, String Vname, String Vprice,String Vcusprice,String Vimglink) {
        this.Vcode=Vcode;
        this.Vname=Vname;
        this.Vprice=Vprice;
        this.Vcusprice=Vcusprice;
        this.Vimglink=Vimglink;
    }

    public String getVcode() {
        return Vcode;
    }

    public void setVcode(String Vcode) {
        this.Vcode = Vcode;
    }

    public String getVname() {
        return Vname;
    }

    public void setVname(String Vname) {
        this.Vname= Vname;
    }

    public String getVprice() {
        return Vprice;
    }

    public void setVprice(String Vprice) {
        this.Vprice = Vprice;
    }

    public String getVcusprice() {
        return Vcusprice;
    }

    public void setVcusprice(String Vcusprice) {
        this.Vcusprice = Vcusprice;
    }

    public String getVimglink() {
        return Vimglink;
    }

    public void setVimglink(String Vimglink) {
        this.Vimglink = Vimglink;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(Vcode);
        parcel.writeString(Vname);
        parcel.writeString(Vprice);
        parcel.writeString(Vcusprice);
        parcel.writeString(Vimglink);
    }
}
