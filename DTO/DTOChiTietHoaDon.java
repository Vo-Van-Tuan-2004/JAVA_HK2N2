package com.motorcycle.sales.dto;

public class ChiTietHoaDon {
    private String maHD;
    private String maXe;
    private int soLuong;
    private double donGia;

    public ChiTietHoaDon() {}

    public ChiTietHoaDon(String maHD, String maXe, int soLuong, double donGia) {
        this.maHD = maHD;
        this.maXe = maXe;
        this.soLuong = soLuong;
        this.donGia = donGia;
    }

    // Getter và Setter
    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaXe() {
        return maXe;
    }

    public void setMaXe(String maXe) {
        this.maXe = maXe;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    @Override
    public String toString() {
        return "ChiTietHoaDon [maHD=" + maHD + ", maXe=" + maXe + ", soLuong=" + soLuong 
                + ", donGia=" + donGia + "]";
    }
}
