package com.example.fiftyproject;

public class CongDan {
    private String hoten;
    private String cccd;

    public CongDan(String hoten, String cccd) {
        this.hoten = hoten;
        this.cccd = cccd;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    @Override
    public String toString() {
        return hoten + " - " + cccd;
    }
}
