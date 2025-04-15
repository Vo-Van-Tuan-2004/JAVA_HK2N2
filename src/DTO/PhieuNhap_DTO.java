package DTO;

import java.sql.Date;

public class PhieuNhap_DTO {
    private String ma_phieu_nhap;
    private String ma_nha_cung_cap;
    private Date ngay_nhap;
    private int tong_tien;

    // Constructor mặc định
    public PhieuNhap_DTO() {
    }

    // Constructor đầy đủ tham số
    public PhieuNhap_DTO(String ma_phieu_nhap, String ma_nha_cung_cap, Date ngay_nhap, int tong_tien) {
        this.ma_phieu_nhap = ma_phieu_nhap;
        this.ma_nha_cung_cap = ma_nha_cung_cap;
        this.ngay_nhap = ngay_nhap;
        this.tong_tien = tong_tien;
    }

    // Getter và Setter cho ma_phieu_nhap
    public String getMa_phieu_nhap() {
        return ma_phieu_nhap;
    }

    public void setMa_phieu_nhap(String ma_phieu_nhap) {
        this.ma_phieu_nhap = ma_phieu_nhap;
    }

    // Getter và Setter cho ma_nha_cung_cap
    public String getMa_nha_cung_cap() {
        return ma_nha_cung_cap;
    }

    public void setMa_nha_cung_cap(String ma_nha_cung_cap) {
        this.ma_nha_cung_cap = ma_nha_cung_cap;
    }

    // Getter và Setter cho ngay_nhap
    public Date getNgay_nhap() {
        return ngay_nhap;
    }

    public void setNgay_nhap(Date ngay_nhap) {
        this.ngay_nhap = ngay_nhap;
    }

    // Getter và Setter cho tong_tien
    public int getTong_tien() {
        return tong_tien;
    }

    public void setTong_tien(int tong_tien) {
        this.tong_tien = tong_tien;
    }
}

