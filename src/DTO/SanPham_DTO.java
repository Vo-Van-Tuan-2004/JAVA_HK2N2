package DTO;

public class SanPham_DTO {
    private String maSanPham;
    private String tenSanPham;
    private String xuatXu;
    private int soLuongTon;
    private int giaBan;
    private String trangThai;
    private String maLoai;


    public SanPham_DTO() {
    }

    public SanPham_DTO(String maSanPham, String tenSanPham, String xuatXu, int soLuongTon, int giaBan, String trangThai, String maLoai) {
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.xuatXu = xuatXu;
        this.soLuongTon = soLuongTon;
        this.giaBan = giaBan;
        this.trangThai = trangThai;
        this.maLoai = maLoai;

    }

    // Getters and Setters
    public String getMa_san_pham() {
        return maSanPham;
    }

    public void setMa_san_pham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTen_san_pham() {
        return tenSanPham;
    }

    public void setTen_san_pham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getXuat_xu() {
        return xuatXu;
    }

    public void setXuat_xu(String xuatXu) {
        this.xuatXu = xuatXu;
    }

    public int getSo_luong_ton() {
        return soLuongTon;
    }

    public void setSo_luong_ton(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public int getGia_ban() {
        return giaBan;
    }

    public void setGia_ban(int giaBan) {
        this.giaBan = giaBan;
    }

    public String getTrang_thai() {
        return trangThai;
    }

    public void setTrang_thai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getMa_loai() {
        return maLoai;
    }

    public void setMa_loai(String maLoai) {
        this.maLoai = maLoai;
    }


}