package com.motorcycle.sales.dto;

import java.util.Date;

public class HoaDon {
    private String maHD;
    private Date ngayLap;
    private String maKH;
    private String maNV;

    public HoaDon() {}

    public HoaDon(String maHD, Date ngayLap, String maKH, String maNV) {
        this.maHD = maHD;
        this.ngayLap = ngayLap;
        this.maKH = maKH;
        this.maNV = maNV;
    }

    // Getter và Setter
    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    @Override
    public String toString() {
        return "HoaDon [maHD=" + maHD + ", ngayLap=" + ngayLap + ", maKH=" + maKH 
                + ", maNV=" + maNV + "]";
    }
}
