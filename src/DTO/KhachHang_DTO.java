package DTO;

public class KhachHang_DTO {
    private String ma_khach_hang;
    private String ten;
    private String sdt;
    private String email;
    private String dia_chi;

    // Constructor mặc định
    public KhachHang_DTO() {
    }

    // Constructor đầy đủ tham số
    public KhachHang_DTO(String ma_khach_hang, String ten, String sdt, String email, String dia_chi) {
        this.ma_khach_hang = ma_khach_hang;
        this.ten = ten;
        this.sdt = sdt;
        this.email = email;
        this.dia_chi = dia_chi;
    }

    // Getter và Setter cho ma_khach_hang
    public String getMa_khach_hang() {
        return ma_khach_hang;
    }

    public void setMa_khach_hang(String ma_khach_hang) {
        this.ma_khach_hang = ma_khach_hang;
    }

    // Getter và Setter cho ten
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    // Getter và Setter cho sdt
    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    // Getter và Setter cho email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter và Setter cho dia_chi
    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }
}
