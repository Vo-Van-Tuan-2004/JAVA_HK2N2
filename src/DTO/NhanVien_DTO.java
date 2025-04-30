package DTO;

public class NhanVien_DTO {
    private String ma_nhan_vien;
    private String ten_nhan_vien;
    private String so_dien_thoai;
    private String dia_chi;
    private String chuc_vu;
    private double luong;

    public NhanVien_DTO() {
    }

    public NhanVien_DTO(String ma_nhan_vien, String ten_nhan_vien, String so_dien_thoai, 
                       String dia_chi, String chuc_vu, double luong) {
        this.ma_nhan_vien = ma_nhan_vien;
        this.ten_nhan_vien = ten_nhan_vien;
        this.so_dien_thoai = so_dien_thoai;
        this.dia_chi = dia_chi;
        this.chuc_vu = chuc_vu;
        this.luong = luong;
    }

    public String getMa_nhan_vien() {
        return ma_nhan_vien;
    }

    public void setMa_nhan_vien(String ma_nhan_vien) {
        this.ma_nhan_vien = ma_nhan_vien;
    }

    public String getTen_nhan_vien() {
        return ten_nhan_vien;
    }

    public void setTen_nhan_vien(String ten_nhan_vien) {
        this.ten_nhan_vien = ten_nhan_vien;
    }

    public String getSo_dien_thoai() {
        return so_dien_thoai;
    }

    public void setSo_dien_thoai(String so_dien_thoai) {
        this.so_dien_thoai = so_dien_thoai;
    }

    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }

    public String getChuc_vu() {
        return chuc_vu;
    }

    public void setChuc_vu(String chuc_vu) {
        this.chuc_vu = chuc_vu;
    }

    public double getLuong() {
        return luong;
    }

    public void setLuong(double luong) {
        this.luong = luong;
    }
}
