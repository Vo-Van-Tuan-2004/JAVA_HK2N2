package OOP;

public class ChitietHoaDon {
    private SanPham thongTinSanPham;
    private int soLuongMua;
    private double giaTien;

    // Constructor không tham số
    public ChitietHoaDon() {
        this.thongTinSanPham = null;
        this.soLuongMua = 0;
        this.giaTien = 0.0;
    }

    // Constructor có tham số
    public ChitietHoaDon(SanPham thongTinSanPham, int soLuongMua) {
        this.thongTinSanPham = thongTinSanPham;
        this.soLuongMua = soLuongMua;
        this.giaTien = tinhGiaTien();
    }

    // Getter và Setter cho thongTinSanPham
    public SanPham getThongTinSanPham() {
        return thongTinSanPham;
    }

    public void setThongTinSanPham(SanPham thongTinSanPham) {
        this.thongTinSanPham = thongTinSanPham;
        this.giaTien = tinhGiaTien();  // Cập nhật lại giá tiền sau khi thay đổi sản phẩm
    }

    // Getter và Setter cho soLuongMua
    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
        this.giaTien = tinhGiaTien();  // Cập nhật lại giá tiền sau khi thay đổi số lượng mua
    }

    // Getter cho giaTien
    public double getGiaTien() {
        return giaTien;
    }

    // Phương thức tính giá tiền
    private double tinhGiaTien() {
        return thongTinSanPham != null ? thongTinSanPham.getGia_SanPham() * soLuongMua : 0.0;
    }

    // Phương thức hiển thị thông tin mua sản phẩm
    public void hienThiThongTinMua() {
        System.out.println(thongTinSanPham.hienThiThongTin1());
        System.out.println("So luong mua: " + soLuongMua);
        System.out.println("Gia tien: " + giaTien);
    }

    @Override
    public String toString() {
        return thongTinSanPham.getID_SanPham() + ", " + soLuongMua + ", " + giaTien;
    }
    
}
