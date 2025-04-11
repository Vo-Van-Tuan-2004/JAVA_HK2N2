package com.motorcycle.sales.dto;

public class NhaSanXuat {
    private String maHang;
    private String tenHang;
    private String quocGia;

    public NhaSanXuat() {}

    public NhaSanXuat(String maHang, String tenHang, String quocGia) {
        this.maHang = maHang;
        this.tenHang = tenHang;
        this.quocGia = quocGia;
    }

    // Getter và Setter
    public String getMaHang() {
        return maHang;
    }

    public void setMaHang(String maHang) {
        this.maHang = maHang;
    }

    public String getTenHang() {
        return tenHang;
    }

    public void setTenHang(String tenHang) {
        this.tenHang = tenHang;
    }

    public String getQuocGia() {
        return quocGia;
    }

    public void setQuocGia(String quocGia) {
        this.quocGia = quocGia;
    }

    @Override
    public String toString() {
        return "NhaSanXuat [maHang=" + maHang + ", tenHang=" + tenHang + ", quocGia=" + quocGia + "]";
    }
}
