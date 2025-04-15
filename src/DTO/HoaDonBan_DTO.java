package DTO;

import java.sql.Date;

public class HoaDonBan_DTO {
    private String ma_hoa_don_ban;
    private String ma_khach_hang;
    private String ma_nhan_vien;
    private Date ngay_xuat;
    private int tong_tien;
    private String trang_thai;

    // Constructor mặc định
    public HoaDonBan_DTO() {
    }

    // Constructor đầy đủ tham số
    public HoaDonBan_DTO(String ma_hoa_don_ban, String ma_khach_hang, String ma_nhan_vien, Date ngay_xuat, int tong_tien, String trang_thai) {
        this.ma_hoa_don_ban = ma_hoa_don_ban;
        this.ma_khach_hang = ma_khach_hang;
        this.ma_nhan_vien = ma_nhan_vien;
        this.ngay_xuat = ngay_xuat;
        this.tong_tien = tong_tien;
        this.trang_thai = trang_thai;
    }

    // Getter và Setter cho ma_hoa_don_ban
    public String getMa_hoa_don_ban() {
        return ma_hoa_don_ban;
    }

    public void setMa_hoa_don_ban(String ma_hoa_don_ban) {
        this.ma_hoa_don_ban = ma_hoa_don_ban;
    }

    // Getter và Setter cho ma_khach_hang
    public String getMa_khach_hang() {
        return ma_khach_hang;
    }

    public void setMa_khach_hang(String ma_khach_hang) {
        this.ma_khach_hang = ma_khach_hang;
    }

    // Getter và Setter cho ma_nhan_vien
    public String getMa_nhan_vien() {
        return ma_nhan_vien;
    }

    public void setMa_nhan_vien(String ma_nhan_vien) {
        this.ma_nhan_vien = ma_nhan_vien;
    }

    // Getter và Setter cho ngay_xuat
    public Date getNgay_xuat() {
        return ngay_xuat;
    }

    public void setNgay_xuat(Date ngay_xuat) {
        this.ngay_xuat = ngay_xuat;
    }

    // Getter và Setter cho tong_tien
    public int getTong_tien() {
        return tong_tien;
    }

    public void setTong_tien(int tong_tien) {
        this.tong_tien = tong_tien;
    }

    // Getter và Setter cho trang_thai
    public String getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }
}
