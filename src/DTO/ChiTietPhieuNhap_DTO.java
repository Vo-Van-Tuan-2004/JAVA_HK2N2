package DTO;

public class ChiTietPhieuNhap_DTO {
    private String ma_phieu_nhap;
    private String ma_spham;
    private int gia_nhap;
    private int so_luong_nhap;
    private int thanh_tien;

    // Constructor mặc định
    public ChiTietPhieuNhap_DTO() {
    }

    // Constructor đầy đủ tham số
    public ChiTietPhieuNhap_DTO(String ma_phieu_nhap, String ma_san_pham,int gia_nhap, int so_luong_nhap, int thanh_tien) {
        this.ma_phieu_nhap = ma_phieu_nhap;
        this.ma_spham = ma_san_pham;
        this.gia_nhap = gia_nhap;
        this.so_luong_nhap = so_luong_nhap;
        this.thanh_tien = thanh_tien;
    }

    // Getter và Setter cho ma_phieu_nhap
    public String getMa_phieu_nhap() {
        return ma_phieu_nhap;
    }

    public void setMa_phieu_nhap(String ma_phieu_nhap) {
        this.ma_phieu_nhap = ma_phieu_nhap;
    }

    // Getter và Setter cho ma_san_pham
    public String getMa_spham() {
        return ma_spham;
    }

    public void setMa_san_pham(String ma_spham) {
        this.ma_spham = ma_spham;
    }

    // Getter và Setter cho gia nhap
    public int getGia_nhap() {
        return gia_nhap;
    }

    public void setGia_nhap(int gia_nhap) {
        this.gia_nhap = gia_nhap;
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

