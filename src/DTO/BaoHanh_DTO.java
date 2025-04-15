package DTO;

public class BaoHanh_DTO {
    private String maBaoHanh;
    private String maXe;
    private String ngayBatDau;
    private String ngayKetThuc;

    // Constructor không tham số
    public BaoHanh_DTO() {
    }

    // Constructor đầy đủ tham số
    public BaoHanh_DTO(String maBaoHanh, String maXe, String ngayBatDau, String ngayKetThuc) {
        this.maBaoHanh = maBaoHanh;
        this.maXe = maXe;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
    }

    // Getters and Setters
    public String getMaBaoHanh() {
        return maBaoHanh;
    }

    public void setMaBaoHanh(String maBaoHanh) {
        this.maBaoHanh = maBaoHanh;
    }

    public String getMaXe() {
        return maXe;
    }

    public void setMaXe(String maXe) {
        this.maXe = maXe;
    }

    public String getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(String ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public String getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(String ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    
}
