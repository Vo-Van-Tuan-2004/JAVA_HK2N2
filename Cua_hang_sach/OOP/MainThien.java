package OOP;

import java.util.Scanner;

public class MainThien {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        QuanLyNhanVien qlnv = new QuanLyNhanVien(); // Quản lý nhân viên
        QuanLySanPham qlsp = new QuanLySanPham(); // Quản lý sản phẩm
        QuanLyHoaDon qlhd = new QuanLyHoaDon(); // Quản lý hóa đơn
        QuanLyKhachHang qlkh = new QuanLyKhachHang(); // Quản lý khách hàng
        
            // Tải dữ liệu từ file
            qlnv.layDuLieuTuFile();
            qlkh.layDuLieuTuFile();
            qlsp.layDuLieuTuFile();
            qlhd.layDuLieuTuFile();

        int choice = 0;
        String account = "none";
        while(true){
            do{
                System.out.println("============== MENU ==============");
                System.out.println("|1.Dang nhap                     |");
                System.out.println("|2.Dang ky                       |");
                System.out.println("|0.Thoat chuong trinh            |");
                System.out.println("==================================");
                choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1:
                        account = dangNhap(qlnv, qlkh, scanner);
                        break;
                    case 2:
                        account = dangky(qlkh, scanner);
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Nhap sai, vui long nhap lai");
                        break;
                }
            }while(account.equals("none")); //chưa có tài khoản thì sẽ loop lại menu này

            // Tiếp tục với các chức năng khác sau khi đăng nhập, đăng ký
            if(!account.substring(0,2).equals("NV")){
                // đăng nhập là khách hàng
                KhachHang khachhang = null; 
                for(KhachHang kh : qlkh.getDanhSachKhachHang()){
                    if(kh.getId_KhachHang().equals(account)){
                        khachhang = kh;
                        break;
                    }
                }

                chonChucNang(khachhang, qlkh, qlhd, qlsp, scanner);
                
            }
            else{
                // đăng nhập là nhân viên
                int chucvu = 0;
                TKNhanVien taikhoan = null;
                for(NhanVien nv : qlnv.getDanhSachNhanVien()){
                    TKNhanVien tk = nv.getTK_NhanVien();
                    if(tk.getId_NhanVien().equals(account)){
                        taikhoan = tk;
                        chucvu = ktraChucVu(tk.getChucVu());
                    }
                }

                switch (chucvu) {
                    case 1:
                        System.out.println("Ban da dang nhap voi quyen quan ly nhan vien!");
                        qlnv.menu(scanner); // gọi hàm quản lý nhân viên
                        break;

                    case 2:
                        System.out.println("Ban da dang nhap voi quyen quan ly khach hang!");
                        qlkh.menuAdmin(scanner);
                        break;

                    case 3:
                        System.out.println("Ban da dang nhap voi quyen quan ly san pham!");
                        qlsp.menu(scanner);
                        break;

                    case 4:
                        System.out.println("Ban da dang nhap voi quyen quan ly hoa don!");
                        qlhd.qlhdMenuAdmin(taikhoan.getId_NhanVien(), scanner);
                        break;
                    default:
                        break;
                }
            }

        }
        
    }

    public static void chonChucNang(KhachHang khachhang, QuanLyKhachHang qlkh, QuanLyHoaDon qlhd, QuanLySanPham qlsp,Scanner scanner){
        boolean exit = false;
        while (!exit){
            System.out.println("=========================================================");
            System.out.println("1.Thong tin tai khoan.");
            System.out.println("2.Mua hang.");
            System.out.println("0.Thoat");
            System.out.println("=========================================================");
            int number = scanner.nextInt();
            scanner.nextLine();
            switch(number){
                case 1:
                    qlkh.menuUser(khachhang, scanner);
                    break;
                case 2:
                    qlspMenuUser(qlsp, khachhang.getId_KhachHang(), qlhd, scanner);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.print("Vui long nhap lai: ");
                    break;
                }
            }
        }

    // đăng ký
    private static String dangky(QuanLyKhachHang qlkh, Scanner scanner){
        String username;
        String password = null;
        int check = 1;
        do{
            // tạo tài khoản
            System.out.print("Nhap ten dang nhap: ");
            username = scanner.nextLine();
            System.out.print("Nhap password: ");
            password = QuanLyNhanVien.kiemtraPass(password, scanner);

            for(KhachHang kh : qlkh.getDanhSachKhachHang()){
                if(kh.getUsername_KhachHang().equals(username)){ // nếu trùng với tài khoản có sẵn thì cho nhập lại
                    System.out.println("Ten dang nhap da ton tai");
                    check = 0;
                    break;
                }
                else{
                    check = 1;
                }
            }
        }while(check == 0);
        qlkh.themKhachHang(username, password); // cho nhập thông tin cá nhân rồi lưu vào file

        KhachHang khachhang = null;
        for(KhachHang kh : qlkh.getDanhSachKhachHang()){
            if(kh.getUsername_KhachHang().equals(username) && kh.getPass_KhachHang().equals(password)){
                khachhang = kh;
            }
        }
        return khachhang.getId_KhachHang(); //đăng ký xong thì trả về id
    }

    // Phương thức đăng nhập
    private static String dangNhap(QuanLyNhanVien qlnv, QuanLyKhachHang qlkh, Scanner scanner) {
        
        while (true) {
            System.out.print("Nhap ten dang nhap: ");
            String username = scanner.nextLine();
            
            System.out.print("Nhap mat khau: ");
            String password = scanner.nextLine();
            // Kiểm tra thông tin tài khoản
            // kiểm tra tài khoản nhân viên
            for (NhanVien nv : qlnv.getDanhSachNhanVien()) {
                TKNhanVien tk = nv.getTK_NhanVien();
                if (tk.getUser_NhanVien().equals(username) && tk.getPass_NhanVien().equals(password)) {
                    return tk.getId_NhanVien();
                }
            }
            // kiểm tra tài khoản khách hàng
            for(KhachHang kh : qlkh.getDanhSachKhachHang()){
                if (kh.getUsername_KhachHang().equals(username) && kh.getPass_KhachHang().equals(password)) {
                    return kh.getId_KhachHang();
                }
            }
            // không tìm thấy tài khoản
            System.out.println("Ten dang nhap hoat mat khau khong chinh xac.");
            System.out.print("Ban co muon thu lai? y/n ");
            String choice = scanner.nextLine();
            
            if (choice.equalsIgnoreCase("n")) {
                return "none"; // Thoát khỏi vòng lặp và kết thúc đăng nhập
            }
        }
    }

    private static int ktraChucVu(String cv){
        if (cv.equals("QLNV"))
        return 1;
        if (cv.equals("QLKH"))
        return 2;
        if (cv.equals("QLSP"))
        return 3;
        if (cv.equals("QLHD"))
        return 4;
        return 0;
    }

    //Menu cho user
    public static void qlspMenuUser(QuanLySanPham qlsp, String idKhachHang,QuanLyHoaDon qlhd, Scanner scanner) {
        GioHang gioHang = new GioHang(idKhachHang);
        boolean exit = false;
        while (!exit) {
            System.out.println("<<<<<<<Danh sach cac san pham>>>>>>>");
            qlsp.hienThiDanhSachSanPham();
            System.out.println("================ MENU ================");
            System.out.println("|1. Them san pham vao gio hang.       |");
            System.out.println("|2. Tim kiem san pham.                |");
            System.out.println("|3. Xoa san pham khoi gio hang.       |");
            System.out.println("|4. Hien thi gio hang.                |");
            System.out.println("|5. Them so luong san pham.           |");
            System.out.println("|6. Giam so luong san pham.           |");
            System.out.println("|7. Thanh toan.                       |");
            System.out.println("|0. Thoat                             |");
            System.out.println("======================================");
            System.out.print("Nhap tuy chon: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println("=========================================================");

            switch (choice) {
                case 1:
                System.out.print("Chon san pham muon them (1 - " + qlsp.size() + "): ");
                int productChoice = scanner.nextInt() - 1;
                if (productChoice < 0 || productChoice >= qlsp.size()) {
                    System.out.println("Lua chon khong hop le.");
                } else {
                    System.out.print("Nhap so luong mua: ");
                    int quantity = scanner.nextInt();
                    if (quantity > 0 && quantity <= qlsp.get(productChoice).getSoLuong_SanPham()) {
                        ChitietHoaDon muaSanPham = new ChitietHoaDon(qlsp.get(productChoice), quantity);
                        gioHang.themSanPham(muaSanPham);
                        System.out.println("Da them " + quantity + " san pham " + qlsp.get(productChoice).getTen_SanPham() + " vao gio hang.");
                    } else {
                        System.out.println("So luong khong hop le.");
                    }
                }
                break;

                case 2:
                    qlsp.timKiemSanPhamTheoThuocTinh();
                break;

                case 3: // Xóa san phẩm khỏi giỏ hàng
                    if (gioHang.getDsSanPhamMua().isEmpty()){
                        System.out.println("Gio hang rong.");
                        break;
                    }
                    System.out.println("Gio hang cua ban:");
                    gioHang.hienThiThongTinGioHang(); 
                    System.out.print("Chon san pham muon xoa (1 - " + gioHang.getDsSanPhamMua().size() + "): ");
                    int removeChoice = scanner.nextInt() - 1; 
                    if (removeChoice < 0 || removeChoice >= gioHang.getDsSanPhamMua().size()) {
                        System.out.println("Lua chon khong hop le.");
                    } else {
                        gioHang.xoaSanPham(removeChoice);
                        System.out.println("Xoa san pham khoi gio hang");
                    }
                    break;

                case 4: // Hiển thị giỏ hàng
                    if (gioHang.getDsSanPhamMua().isEmpty()){
                        System.out.println("Gio hang rong.");
                        break;
                    }
                    System.out.println("Thong tinh gio hang:");
                    gioHang.hienThiThongTinGioHang();
                    break;

                case 5: // Thêm số lượng sản phẩm
                    if (gioHang.getDsSanPhamMua().isEmpty()){
                        System.out.println("Gio hang rong.");
                        break;
                    }
                    System.out.println("Gio hang cua ban:");
                    gioHang.hienThiThongTinGioHang();
                    System.out.print("Chon san pham muon them so luong (1 - " + gioHang.getDsSanPhamMua().size() + "): ");
                    int increaseChoice = scanner.nextInt() - 1;
                    if (increaseChoice < 0 || increaseChoice >= gioHang.getDsSanPhamMua().size()) {
                        System.out.println("Lua chon khong hop le.");
                    } else {
                        System.out.print("Nhap so luong muon mua them: ");
                        int increaseQuantity = scanner.nextInt();
                        ChitietHoaDon muaSanPham = gioHang.getDsSanPhamMua().get(increaseChoice);
                        int soLuongConLai = muaSanPham.getThongTinSanPham().getSoLuong_SanPham() - muaSanPham.getSoLuongMua();
                        if (increaseQuantity > soLuongConLai) {
                            System.out.println("So luong vuot qua han muc san pham trong kho. Chi co the them toi da " + soLuongConLai);
                        } else {
                            gioHang.tangSoLuongSanPham(increaseChoice, increaseQuantity);
                        }
                    }
                    break;
                case 6: // Giảm số lượng sản phẩm
                    if (gioHang.getDsSanPhamMua().isEmpty()){
                        System.out.println("Gio hang rong.");
                        break;
                    }
                    System.out.println("Gio hang cua ban:");
                    gioHang.hienThiThongTinGioHang();
                    System.out.print("Chon san pham muon giam so luong (1 - " + gioHang.getDsSanPhamMua().size() + "): ");
                    int decreaseChoice = scanner.nextInt() - 1;
                    if (decreaseChoice < 0 || decreaseChoice >= gioHang.getDsSanPhamMua().size()) {
                        System.out.println("Lua chon khong hop le.");
                    } else {
                        System.out.print("Nhap so luong muon giam: ");
                        int decreaseQuantity = scanner.nextInt();
                        ChitietHoaDon muaSanPham = gioHang.getDsSanPhamMua().get(decreaseChoice);
                        int soLuongDaChon = muaSanPham.getSoLuongMua();
                        if (decreaseQuantity > soLuongDaChon) {
                            System.out.println("So luong vuot qua so luong da chon. Chi co the giam toi da " + soLuongDaChon);
                        } else {
                            if(decreaseQuantity < soLuongDaChon){
                                gioHang.giamSoLuongSanPham(decreaseChoice, decreaseQuantity);
                            }
                            else{ // số lượng giảm = số lượng đã chọn thì xóa luôn sản phẩm
                                gioHang.xoaSanPham(decreaseChoice);
                                System.out.println("Da xoa toan bo so luong san pham da chon");
                            }

                        }
                    }
                    break;

                case 7:  // Thanh toán
                    HoaDon hoaDon = gioHang.thanhToan(qlhd);
                    if (hoaDon != null) {
                        qlhd.themHoaDon(hoaDon);
                        for (SanPham sp : qlsp.getDanhSachSanPham()){
                            for (ChitietHoaDon muaSanPham : hoaDon.getDsSanPhamMua()){
                                if (sp.getID_SanPham().equals(muaSanPham.getThongTinSanPham().getID_SanPham())){
                                    sp.setSoLuong_SanPham(sp.getSoLuong_SanPham() - muaSanPham.getSoLuongMua());
                                }
                            }
                        }
                        qlsp.luuDuLieuVaoFile();
                        qlhd.luuDuLieuVaoFile();
                        System.out.println("Thanh toan thanh cong ting ting.");
                    } else {
                        System.out.println("Gio hang rong khong the thanh toan.");
                    }
                    break;

                case 0:
                    exit = true;
                    System.out.println("Cam on ban da su dung dinh vu.");
                    break;

                default:
                    System.out.println("Khong hop le.Moi chon lai.");
            }
        }
    }
}
