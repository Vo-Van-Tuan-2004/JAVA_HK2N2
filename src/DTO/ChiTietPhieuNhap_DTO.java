package DTO;

public class ChiTietPhieuNhap_DTO {
    private String ma;
    private String ma_phieu_nhap;
    private String ma_san_pham;
    private int so_luong_nhap;
    private int thanh_tien;

    // Constructor mặc định
    public ChiTietPhieuNhap_DTO() {
    }

    // Constructor đầy đủ tham số
    public ChiTietPhieuNhap_DTO(String ma, String ma_phieu_nhap, String ma_san_pham, int so_luong_nhap, int thanh_tien) {
        this.ma = ma;
        this.ma_phieu_nhap = ma_phieu_nhap;
        this.ma_san_pham = ma_san_pham;
        this.so_luong_nhap = so_luong_nhap;
        this.thanh_tien = thanh_tien;
    }

    // Getter và Setter cho ma
    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    // Getter và Setter cho ma_phieu_nhap
    public String getMa_phieu_nhap() {
        return ma_phieu_nhap;
    }

    public void setMa_phieu_nhap(String ma_phieu_nhap) {
        this.ma_phieu_nhap = ma_phieu_nhap;
    }

    // Getter và Setter cho ma_san_pham
    public String getMa_san_pham() {
        return ma_san_pham;
    }

    public void setMa_san_pham(String ma_san_pham) {
        this.ma_san_pham = ma_san_pham;
    }

    // Getter và Setter cho so_luong_nhap
    public int getSo_luong_nhap() {
        return so_luong_nhap;
    }

    public void setSo_luong_nhap(int so_luong_nhap) {
        this.so_luong_nhap = so_luong_nhap;
    }

    // Getter và Setter cho thanh_tien
    public int getThanh_tien() {
        return thanh_tien;
    }

    public void setThanh_tien(int thanh_tien) {
        this.thanh_tien = thanh_tien;
    }
}

