package DTO;

public class XeMay_DTO extends SanPham_DTO{
    private String ma_xe;
    private int dung_tich_xi_lanh;
    private int nam_san_xuat;

    // Constructor mặc định
    public XeMay_DTO() {
    }

    // Constructor đầy đủ tham số
    public XeMay_DTO(String ma_xe, int dung_tich_xi_lanh, int nam_san_xuat) {
        this.ma_xe = ma_xe;
        this.dung_tich_xi_lanh = dung_tich_xi_lanh;
        this.nam_san_xuat = nam_san_xuat;
    }

    // Getter và Setter cho ma_xe
    public String getMa_xe() {
        return ma_xe;
    }

    public void setMa_xe(String ma_xe) {
        this.ma_xe = ma_xe;
    }

    // Getter và Setter cho dung_tich_xi_lanh
    public int getDung_tich_xi_lanh() {
        return dung_tich_xi_lanh;
    }

    public void setDung_tich_xi_lanh(int dung_tich_xi_lanh) {
        this.dung_tich_xi_lanh = dung_tich_xi_lanh;
    }

    // Getter và Setter cho nam_san_xuat
    public int getNam_san_xuat() {
        return nam_san_xuat;
    }

    public void setNam_san_xuat(int nam_san_xuat) {
        this.nam_san_xuat = nam_san_xuat;
    }
}

