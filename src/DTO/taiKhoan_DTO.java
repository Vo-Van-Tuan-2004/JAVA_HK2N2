package DTO;

public class taiKhoan_DTO {
    private String maTaiKhoan;
    private String tenTaiKhoan;
    private String matKhau;

    public taiKhoan_DTO(){}

    public taiKhoan_DTO(String maTaiKhoan, String tenTaiKhoan, String matKhau){
        this.maTaiKhoan = maTaiKhoan;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
    }

    public String getMaTaiKhoan(){
        return maTaiKhoan;
    }
    public String getTenTaiKhoan(){
        return tenTaiKhoan;
    }
    public String getMatKhau(){
        return matKhau;
    }
    public void setMaTaiKhoan(String maTaiKhoan){
        this.maTaiKhoan = maTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan){
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public void setMatKhau(String matKhau){
        this.matKhau = matKhau;
    }
}

