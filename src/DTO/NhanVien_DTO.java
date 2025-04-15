package DTO;

public class NhanVien_DTO {
    private String maNhanVien;
    private String ten;
    private String chucVu;
    private String soDienThoai;
    private String username;
    private String password;
    private String mucLuong;
    private String gioiTinh;
    private String diaChi;

        // Constructor không tham số
    public NhanVien_DTO() {
    }
    
        // Constructor đầy đủ tham số
    public NhanVien_DTO(String maNhanVien, String ten, String chucVu, String soDienThoai, String username, String password, String mucLuong, String gioiTinh, String diaChi) {
            this.maNhanVien = maNhanVien;
            this.ten = ten;
            this.chucVu = chucVu;
            this.soDienThoai = soDienThoai;
            this.username = username;
            this.password = password;
            this.mucLuong = mucLuong;
            this.gioiTinh = gioiTinh;
            this.diaChi = diaChi;
        }
    // Getters and Setters
    public String getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(String maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMucLuong() {
        return mucLuong;
    }

    public void setMucLuong(String mucLuong) {
        this.mucLuong = mucLuong;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

   
}
