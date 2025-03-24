package OOP;

public class NhanVien {
    private String Ten_NhanVien;
    private String SDT_NhanVien;
    private String DiaChi_NhanVien;
    private String CCCD_NhanVien;
    private TKNhanVien TK_NhanVien;
    private NgayThangNam NgaySinh_NhanVien;
    private String GioiTinh_NhanVien ;

    public NhanVien(String ten_NhanVien, String sDT_NhanVien, String diaChi_NhanVien, String cCCD_NhanVien,
            TKNhanVien tK_NhanVien, NgayThangNam ngaySinh_NhanVien , String gioiTinh_NhanVien) {
        Ten_NhanVien = ten_NhanVien;
        SDT_NhanVien = sDT_NhanVien;
        DiaChi_NhanVien = diaChi_NhanVien;
        CCCD_NhanVien = cCCD_NhanVien;
        TK_NhanVien = tK_NhanVien;
        NgaySinh_NhanVien = ngaySinh_NhanVien;
        GioiTinh_NhanVien = gioiTinh_NhanVien ;
    }

    public String getTen_NhanVien() {
        return Ten_NhanVien;
    }

    public void setTen_NhanVien(String ten_NhanVien) {
        Ten_NhanVien = ten_NhanVien;
    }

    public String getSDT_NhanVien() {
        return SDT_NhanVien;
    }

    public void setSDT_NhanVien(String sDT_NhanVien) {
        if (sDT_NhanVien.matches("\\d{10}")) { // So dien thoai gioi han 10 so
            this.SDT_NhanVien = sDT_NhanVien;
        } else {
            System.out.println("So dien thoai khong phu hop!");
        }
    }

    public String getDiaChi_NhanVien() {
        return DiaChi_NhanVien;
    }

    public void setDiaChi_NhanVien(String diaChi_NhanVien) {
        DiaChi_NhanVien = diaChi_NhanVien;
    }

    public String getCCCD_NhanVien() {
        return CCCD_NhanVien;
    }

    public void setCCCD_NhanVien(String cCCD_NhanVien) {
        CCCD_NhanVien = cCCD_NhanVien;
    }

    public TKNhanVien getTK_NhanVien() {
        return TK_NhanVien;
    }

    public void setTK_NhanVien(TKNhanVien tK_NhanVien) {
        TK_NhanVien = tK_NhanVien;
    }

    public NgayThangNam getNgaySinh_NhanVien() {
        return NgaySinh_NhanVien;
    }

    public void setNgaySinh_NhanVien(NgayThangNam ngaySinh_NhanVien) {
        NgaySinh_NhanVien = ngaySinh_NhanVien;
    }

    @Override
    public String toString() {
        return Ten_NhanVien + ", " + SDT_NhanVien + ", " + DiaChi_NhanVien + ", " + CCCD_NhanVien  + ", " + NgaySinh_NhanVien + ", " + GioiTinh_NhanVien;
    }

    public String getGioiTinh_NhanVien() {
        return GioiTinh_NhanVien;
    }

    public void setGioiTinh_NhanVien(String GioiTinh_NhanVien) {
        this.GioiTinh_NhanVien = GioiTinh_NhanVien;
    }
    

}
