package OOP;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class GioHang {
    private String idKhachHang;
    private ArrayList<ChitietHoaDon> dsSanPhamMua;
    private double tongTien;

    // Constructor không tham số
    public GioHang() {
        this.idKhachHang = "";
        this.dsSanPhamMua = new ArrayList<>();
        this.tongTien = 0.0;
    }

    // Constructor có tham số
    public GioHang(String idKhachHang) {
        this.idKhachHang = idKhachHang;
        this.dsSanPhamMua = new ArrayList<>();
        this.tongTien = 0.0;
    }

    // Getter và Setter cho idKhachHang
    public String getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(String idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    // Getter cho dsSanPhamMua
    public ArrayList<ChitietHoaDon> getDsSanPhamMua() {
        return dsSanPhamMua;
    }

    // Getter cho tongTien
    public double getTongTien() {
        return tongTien;
    }

    // Phương thức thêm sản phẩm vào giỏ hàng
    public void themSanPham(ChitietHoaDon spMua) {
        boolean i = true;
        for (ChitietHoaDon hd :dsSanPhamMua ){
            if (hd.getThongTinSanPham().getID_SanPham().equals(spMua.getThongTinSanPham().getID_SanPham())){
                i = false;
                hd.setSoLuongMua(hd.getSoLuongMua() + spMua.getSoLuongMua());
                break;
            }
        }
        if (i == true)
        dsSanPhamMua.add(spMua);
        tinhTongTien();  // Cập nhật lại tổng tiền sau khi thêm sản phẩm
    }

    // Phương thức tính tổng tiền của giỏ hàng
    private void tinhTongTien() {
        tongTien = 0.0;
        for (ChitietHoaDon spMua : dsSanPhamMua) {
            tongTien += spMua.getGiaTien();
        }
    }

    // Phương thức hiển thị thông tin giỏ hàng
    public void hienThiThongTinGioHang() {
        int i = 1;
        System.out.println("Gio hang cua khach hang: " + idKhachHang);
        for (ChitietHoaDon spMua : dsSanPhamMua) {
            System.out.print(i + ". ");
            spMua.hienThiThongTinMua();
            i++;
        }
        System.out.println("Tong tien gio hang: " + tongTien);
    }

    @Override
    public String toString() {
        return "GioHang [idKhachHang=" + idKhachHang + ", dsSanPhamMua=" + dsSanPhamMua + ", tongTien=" + tongTien + "]";
    }

    // Phương thức xóa sản phẩm khỏi giỏ hàng
    public void xoaSanPham(int index) {
        if (index >= 0 && index < dsSanPhamMua.size()) {
            dsSanPhamMua.remove(index);
            tinhTongTien(); // Cập nhật lại tổng tiền
            System.out.println("Da xoa san pham khoi gio hang.");
        } else {
            System.out.println("Lua chon khong hop le. Khong the xoa san pham.");
        }
    }

    // Phương thức tăng số lượng sản phẩm
    public void tangSoLuongSanPham(int index, int soLuong) {
        if (soLuong >= 0) {
            ChitietHoaDon spMua = dsSanPhamMua.get(index);
            spMua.setSoLuongMua(spMua.getSoLuongMua() + soLuong); // Tăng số lượng
            tinhTongTien(); // Cập nhật lại tổng tiền
            System.out.println("Da them " + soLuong + " san pham vao gio hang.");
        } else {
            System.out.println("Lua chon so luong khong hop le.");
        }
    }

    // Phương thức giảm số lượng sản phẩm
    public void giamSoLuongSanPham(int index, int soLuong) {
        if (soLuong >= 0) {
            ChitietHoaDon spMua = dsSanPhamMua.get(index);
            spMua.setSoLuongMua(spMua.getSoLuongMua() - soLuong); // giảm số lượng
            tinhTongTien(); // Cập nhật lại tổng tiền
            System.out.println("Da giam " + soLuong + " so luong san pham da chon.");
        } else {
            System.out.println("Lua chon so luong khong hop le.");
        }
    }

    public HoaDon thanhToan(QuanLyHoaDon qlhd) {
        if (dsSanPhamMua.isEmpty()) {
            
            return null;
        }

        // Tạo ID hóa đơn duy nhất
        String idHoaDon = qlhd.taoID();
        Date date = new Date();
        DateFormat dFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strdate = dFormat.format(date);
        String idNhanVien = "Chua co nhan vien xu ly"; // Chưa có nhân viên xử lý
        String TinhTrangDonHang = "Chua xu ly"; // Tình trạng hóa đơn sẽ là chưa xử lý khi mới thanh toán
        // Tạo hóa đơn từ thông tin giỏ hàng
        HoaDon hoaDon = new HoaDon(idHoaDon, idNhanVien, idKhachHang, dsSanPhamMua, tongTien, strdate, TinhTrangDonHang);

        // Xóa giỏ hàng sau khi thanh toán
        dsSanPhamMua.clear();
        tongTien = 0.0;

        System.out.println("Thanh toan thanh cong! Hoa don da duoc tao.");
        hoaDon.hienThiHoaDon();

        return hoaDon;
    }
}
