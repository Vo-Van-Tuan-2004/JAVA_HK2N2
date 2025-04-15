package DTO;

public class NhaCungCap_DTO {
    private String ma_nha_cung_cap;
    private String ten;
    private String dia_chi;
    private String email;
    private String quoc_gia;

    // Constructor mặc định
    public NhaCungCap_DTO() {
    }

    // Constructor đầy đủ tham số
    public NhaCungCap_DTO(String ma_nha_cung_cap, String ten, String dia_chi, String email, String quoc_gia) {
        this.ma_nha_cung_cap = ma_nha_cung_cap;
        this.ten = ten;
        this.dia_chi = dia_chi;
        this.email = email;
        this.quoc_gia = quoc_gia;
    }

    // Getter và Setter cho ma_nha_cung_cap
    public String getMa_nha_cung_cap() {
        return ma_nha_cung_cap;
    }

    public void setMa_nha_cung_cap(String ma_nha_cung_cap) {
        this.ma_nha_cung_cap = ma_nha_cung_cap;
    }

    // Getter và Setter cho ten
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    // Getter và Setter cho dia_chi
    public String getDia_chi() {
        return dia_chi;
    }

    public void setDia_chi(String dia_chi) {
        this.dia_chi = dia_chi;
    }

    // Getter và Setter cho email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    // Getter và Setter cho quoc_gia
    public String getQuoc_gia() {
        return quoc_gia;
    }

    public void setQuoc_gia(String quoc_gia) {
        this.quoc_gia = quoc_gia;
    }
}

