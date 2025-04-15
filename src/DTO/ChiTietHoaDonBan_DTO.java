package DTO;

public class ChiTietHoaDonBan_DTO {
    private String ma;
    private String ma_hoa_don_ban;
    private String ma_san_pham;
    private int so_luong;
    private int don_gia;

    // Constructor mặc định
    public ChiTietHoaDonBan_DTO() {
    }

    // Constructor đầy đủ tham số
    public ChiTietHoaDonBan_DTO(String ma, String ma_hoa_don_ban, String ma_san_pham, int so_luong, int don_gia) {
        this.ma = ma;
        this.ma_hoa_don_ban = ma_hoa_don_ban;
        this.ma_san_pham = ma_san_pham;
        this.so_luong = so_luong;
        this.don_gia = don_gia;
    }

    // Getter và Setter cho ma
    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    // Getter và Setter cho ma_hoa_don_ban
    public String getMa_hoa_don_ban() {
        return ma_hoa_don_ban;
    }

    public void setMa_hoa_don_ban(String ma_hoa_don_ban) {
        this.ma_hoa_don_ban = ma_hoa_don_ban;
    }

    // Getter và Setter cho ma_san_pham
    public String getMa_san_pham() {
        return ma_san_pham;
    }

    public void setMa_san_pham(String ma_san_pham) {
        this.ma_san_pham = ma_san_pham;
    }

    // Getter và Setter cho so_luong
    public int getSo_luong() {
        return so_luong;
    }

    public void setSo_luong(int so_luong) {
        this.so_luong = so_luong;
    }

    // Getter và Setter cho don_gia
    public int getDon_gia() {
        return don_gia;
    }

    public void setDon_gia(int don_gia) {
        this.don_gia = don_gia;
    }
}

