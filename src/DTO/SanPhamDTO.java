package DTO;

public class SanPhamDTO {
    private String maSanPham;
    private String tenSanPham;
    private String xuatXu;
    private int soLuongTon;
    private int giaBan;
    private String trangThai;
    private String maLoai;
    private String tenLoai;

    public SanPhamDTO() {
    }

    public SanPhamDTO(String maSanPham, String tenSanPham, String xuatXu, int soLuongTon, int giaBan, String trangThai, String maLoai, String tenLoai) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.xuatXu = xuatXu;
        this.soLuongTon = soLuongTon;
        this.giaBan = giaBan;
        this.trangThai = trangThai;
        this.maLoai = maLoai;
        this.tenLoai = tenLoai;
    }

    // Getters and Setters
    public String getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getXuatXu() {
        return xuatXu;
    }

    public void setXuatXu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public int getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(int giaBan) {
        this.giaBan = giaBan;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }
} 