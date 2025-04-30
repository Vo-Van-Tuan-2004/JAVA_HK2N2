package DTO;

public class KhachHang_DTO {
    private String ma_khach_hang;
    private String ten_khach_hang;
    private String so_dien_thoai;
    private String email;
    private String dia_chi;

    // Constructor mặc định
    public KhachHang_DTO() {
    }

    // Constructor đầy đủ tham số
    public KhachHang_DTO(String ma_khach_hang, String ten_khach_hang, String so_dien_thoai, String email, String dia_chi) {
        this.ma_khach_hang = ma_khach_hang;
        this.ten_khach_hang = ten_khach_hang;
        this.so_dien_thoai = so_dien_thoai;
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

    // Getter và Setter cho ten_khach_hang
    public String getTen_khach_hang() {
        return ten_khach_hang;
    }

    public void setTen_khach_hang(String ten_khach_hang) {
        this.ten_khach_hang = ten_khach_hang;
    }

    // Getter và Setter cho so_dien_thoai
    public String getSo_dien_thoai() {
        return so_dien_thoai;
    }

    public void setSo_dien_thoai(String so_dien_thoai) {
        this.so_dien_thoai = so_dien_thoai;
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

    @Override
    public String toString() {
        return "KhachHang_DTO{" +
                "ma_khach_hang='" + ma_khach_hang + '\'' +
                ", ten_khach_hang='" + ten_khach_hang + '\'' +
                ", so_dien_thoai='" + so_dien_thoai + '\'' +
                ", email='" + email + '\'' +
                ", dia_chi='" + dia_chi + '\'' +
                '}';
    }
}
