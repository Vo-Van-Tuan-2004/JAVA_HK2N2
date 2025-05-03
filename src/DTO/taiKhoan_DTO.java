package DTO;

public class taiKhoan_DTO {
    private String maTaiKhoan;
    private String tenTaiKhoan;
    private String matKhau;
    private String chucVu;

    public taiKhoan_DTO() {}

    public taiKhoan_DTO(String maTaiKhoan, String tenTaiKhoan, String matKhau, String chucVu) {
        this.maTaiKhoan = maTaiKhoan;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.chucVu = chucVu;
    }

    public String getMaTaiKhoan() {
        return maTaiKhoan;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setMaTaiKhoan(String maTaiKhoan) {
        this.maTaiKhoan = maTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }
}