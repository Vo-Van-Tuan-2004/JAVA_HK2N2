package DTO;

public class SanPham_DTO {
    private String ma_san_pham;
    private String ma_loai;
    private String ten_san_pham;
    private int so_luong_ton;
    private int gia_ban;         // đổi từ double sang int
    private int gia_nhap;        // đổi từ double sang int
    private String trang_thai;
    private String xuat_xu;
    private String bao_hanh;
    private String thuong_hieu;

    // Constructor mặc định
    public SanPham_DTO() {
    }

    // Constructor đầy đủ tham số
    public SanPham_DTO(String ma_san_pham, String ma_loai, String ten_san_pham, int so_luong_ton,
                       int gia_ban, int gia_nhap, String trang_thai, String xuat_xu,
                       String bao_hanh, String thuong_hieu) {
        this.ma_san_pham = ma_san_pham;
        this.ma_loai = ma_loai;
        this.ten_san_pham = ten_san_pham;
        this.so_luong_ton = so_luong_ton;
        this.gia_ban = gia_ban;
        this.gia_nhap = gia_nhap;
        this.trang_thai = trang_thai;
        this.xuat_xu = xuat_xu;
        this.bao_hanh = bao_hanh;
        this.thuong_hieu = thuong_hieu;
    }

    public String getMa_san_pham() {
        return ma_san_pham;
    }

    public void setMa_san_pham(String ma_san_pham) {
        this.ma_san_pham = ma_san_pham;
    }

    public String getMa_loai() {
        return ma_loai;
    }

    public void setMa_loai(String ma_loai) {
        this.ma_loai = ma_loai;
    }

    public String getTen_san_pham() {
        return ten_san_pham;
    }

    public void setTen_san_pham(String ten_san_pham) {
        this.ten_san_pham = ten_san_pham;
    }

    public int getSo_luong_ton() {
        return so_luong_ton;
    }

    public void setSo_luong_ton(int so_luong_ton) {
        this.so_luong_ton = so_luong_ton;
    }

    public int getGia_ban() {
        return gia_ban;
    }

    public void setGia_ban(int gia_ban) {
        this.gia_ban = gia_ban;
    }

    public int getGia_nhap() {
        return gia_nhap;
    }

    public void setGia_nhap(int gia_nhap) {
        this.gia_nhap = gia_nhap;
    }

    public String getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(String trang_thai) {
        this.trang_thai = trang_thai;
    }

    public String getXuat_xu() {
        return xuat_xu;
    }

    public void setXuat_xu(String xuat_xu) {
        this.xuat_xu = xuat_xu;
    }

    public String getBao_hanh() {
        return bao_hanh;
    }

    public void setBao_hanh(String bao_hanh) {
        this.bao_hanh = bao_hanh;
    }

    public String getThuong_hieu() {
        return thuong_hieu;
    }

    public void setThuong_hieu(String thuong_hieu) {
        this.thuong_hieu = thuong_hieu;
    }
}
