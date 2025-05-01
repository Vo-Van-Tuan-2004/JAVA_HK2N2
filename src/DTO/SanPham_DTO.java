package DTO;

public class SanPham_DTO {
    private String ma_spham;
    private String ten_spham;
    private String xuat_xu;
    private int so_luong_ton;
    private int gia_ban;
    private String trang_thai;
    private String ma_loai;


    public SanPham_DTO() {
    }

    public SanPham_DTO(String maSanPham, String tenSanPham, String xuatXu, int soLuongTon, int giaBan, int aInt2, String trangThai, String maLoai, String string5, String string6, String string7) {
        this.ma_spham = maSanPham;
        this.ten_spham = tenSanPham;
        this.xuat_xu = xuatXu;
        this.so_luong_ton = soLuongTon;
        this.gia_ban = giaBan;
        this.trang_thai = trangThai;
        this.ma_loai = maLoai;

    }

    // Getters and Setters
    public String getMa_san_pham() {
        return ma_spham;
    }

    public void setMa_san_pham(String maSanPham) {
        this.ma_spham = maSanPham;
    }

    public String getTen_san_pham() {
        return ten_spham;
    }

    public void setTen_san_pham(String tenSanPham) {
        this.ten_spham = tenSanPham;
    }

    public String getXuat_xu() {
        return xuat_xu;
    }

    public void setXuat_xu(String xuatXu) {
        this.xuat_xu = xuatXu;
    }

    public int getSo_luong_ton() {
        return so_luong_ton;
    }

    public void setSo_luong_ton(int soLuongTon) {
        this.so_luong_ton = soLuongTon;
    }

    public int getGia_ban() {
        return gia_ban;
    }

    public void setGia_ban(int giaBan) {
        this.gia_ban = giaBan;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(String trangThai) {
        this.trang_thai = trangThai;
    }

    public String getMa_loai() {
        return ma_loai;
    }

    public void setMa_loai(String maLoai) {
        this.ma_loai = maLoai;
    }


}