package OOP;

import java.util.ArrayList;

public class HoaDon {
    private String idNhanVien;
    private String idHoaDon;
    private String idKhachHang;
    private ArrayList<ChitietHoaDon> dsSanPhamMua;
    private double tongTien;
    private String ngayThanhToan;
    private String TinhTrangDonHang;

    
    public HoaDon(String idHoaDon, String idNhanVien, String idKhachHang, ArrayList<ChitietHoaDon> dsSanPhamMua, double tongTien, String ngayThanhToan, String TinhTrang) {
        this.idNhanVien = idNhanVien;
        this.idHoaDon = idHoaDon;
        this.idKhachHang = idKhachHang;
        this.dsSanPhamMua = new ArrayList<>(dsSanPhamMua);
        this.tongTien = tongTien;
        this.ngayThanhToan = ngayThanhToan;  // Ngày thanh toán là ngày hiện tại
        this.TinhTrangDonHang = TinhTrang;
    }

    // Getter cho các thuộc tính
    public String getIdNhanVien(){
        return idNhanVien;
    }

    public String getIdHoaDon() {
        return idHoaDon;
    }

    public String getIdKhachHang() {
        return idKhachHang;
    }

    public ArrayList<ChitietHoaDon> getDsSanPhamMua() {
        return dsSanPhamMua;
    }

    public double getTongTien() {
        return tongTien;
    }

    public String getNgayThanhToan() {
        return ngayThanhToan;
    }

    public String getTinhTrangDonHang(){
        return TinhTrangDonHang;
    }

    // setter cho tình trạng đơn hàng và id nhân viên
    public void setTinhTrangDonHang(String tinhtrang){
        this.TinhTrangDonHang = tinhtrang;
    }

    public void setIdNhanVien(String id){
        this.idNhanVien = id;
    }


    // Phương thức hiển thị thông tin hóa đơn
    public void hienThiHoaDon() {
        System.out.println("Danh sach san pham mua:");
        for (ChitietHoaDon spMua : dsSanPhamMua) {
            spMua.hienThiThongTinMua();
        }
        System.out.println("Ngay thanh toan: " + ngayThanhToan);
        System.out.println("Tong tien: " + tongTien);
    }

    @Override
    public String toString() {
        return idHoaDon + ", " + idNhanVien + ", " + idKhachHang + ", " + dsSanPhamMua
                + ", " + tongTien + ", " + ngayThanhToan + ", " + TinhTrangDonHang;
    }


    

    

}
