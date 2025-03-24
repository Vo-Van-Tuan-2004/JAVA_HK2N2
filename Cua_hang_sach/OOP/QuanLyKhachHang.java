package OOP;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class QuanLyKhachHang implements TaoID, CheckDate, QuanLyFile{
    private static int counter = 0 ;
    private ArrayList<KhachHang> danhSachKhachHang;

    public QuanLyKhachHang() {
        danhSachKhachHang = new ArrayList<>();
    }
     
    @Override
    public String taoID(){
        counter++;
        return "KH" + String.format("%08d", counter);
    }
    // Lấy dữ liệu từ file KhachHang.txt
    @Override
    public void layDuLieuTuFile(){
        try (BufferedReader br = new BufferedReader(new FileReader("./file/KhachHang.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 8) {

                    String[] cns = parts[5].split("/");

                    if (cns.length == 3) {
                        int[] ns = new int[cns.length];
                            for (int i = 0; i < cns.length; i++) {  // Chỉ chạy từ 0 đến 2
                                ns[i] = Integer.parseInt(cns[i]);
                            }
                        int currentId = Integer.parseInt(parts[0].substring(2));
                        counter = Math.max(counter, currentId);

                        NgayThangNam  ngayThangNam = new NgayThangNam(ns[0], ns[1], ns[2]);
                        KhachHang khachHang = new KhachHang(parts[0], parts[1], parts[2], parts[3], parts[4], parts[6], parts[7], ngayThangNam);
                        
                        danhSachKhachHang.add(khachHang);
                    }
                }
                else{
                    System.out.println("               <<<Du lieu khong hop le!>>>");
                }
            }
        } catch (IOException e) {
            System.err.println("Loi khi doc file KhachHang.txt: " + e.getMessage());
        }
    }

    // Lưu sữ liệu vào file KhachHang.txt
    @Override
    public void luuDuLieuVaoFile(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("./file/KhachHang.txt"))){

            for (KhachHang khachHang : danhSachKhachHang){
                bw.write(khachHang.toString());
                bw.newLine();
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // Hiện thị danh sách khách hàng cho admin
    public void hienThiDanhSachKhachHang(){
        System.out.println("                DANH SACH KHACH HANG");
        for (KhachHang khachHang : danhSachKhachHang){
            System.out.println(khachHang.toString());
        }
    }

    // Hiện thị thông tin khách hàng cho user
    public void hienThiThongTinKhachHang(KhachHang khachHang) {
        System.out.println("         THONG TIN KHACH HANG");
        khachHang.Xuat();
    }

    // Thêm khách hàng
    public void themKhachHang(String userName, String passWord){
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap ten khach hang: ");
        String tenKhachHang = scanner.nextLine();
        System.out.print("So dien thoai: ");
        String soDienThoai = null;
        soDienThoai = QuanLyNhanVien.kiemtraSDT(soDienThoai, scanner);
        
        System.out.println("Chon gioi tinh: ");
        String gioiTinh;
        while (true){
            System.out.println("1. Nam");
            System.out.println("2. Nu");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1 || choice == 2){
                gioiTinh  = (choice == 1) ? "Nam" : "Nu";
                break;
            }
            else
                System.out.println("Chon sai, chon lai: ");
        }

        System.out.print("Dia chi: ");
        String diaChi = scanner.nextLine();
        System.out.print("Nhap ngay sinh(dd/mm/yyyy): ");
        NgayThangNam  ngayThangNam;
        while (true){
            
            String ngayThangNam1 = scanner.nextLine();
            String[] ns = ngayThangNam1.split("/");
            System.out.println(ns.length);
            if (ns.length == 3 && CheckDate.kiemTraNgayHopLe(Integer.parseInt(ns[0]), Integer.parseInt(ns[1]), Integer.parseInt(ns[2]))){
                ngayThangNam = new NgayThangNam(Integer.parseInt(ns[0]), Integer.parseInt(ns[1]), Integer.parseInt(ns[2]));
                break;
            }
            else
            System.out.print("Nhap sai, nhap lai: ");

        }
        String idKhachHang = taoID();
        KhachHang khachHang = new KhachHang(idKhachHang, tenKhachHang, soDienThoai, gioiTinh, diaChi, userName, passWord, ngayThangNam);

        danhSachKhachHang.add(khachHang);     
        luuDuLieuVaoFile();
    }

    // Xóa khách hàng
    public void xoaKhachHang(Scanner scanner){
        System.out.println("Nhap id khach hang can xoa: ");
        String idKhachHang = scanner.nextLine();
        boolean co = false;
        for (int i = 0; i < danhSachKhachHang.size(); i++){
            if (danhSachKhachHang.get(i).getId_KhachHang().equals(idKhachHang)){
                co = true;  
                danhSachKhachHang.remove(i);
                break;
            }
            
            
        }
        if(co==false)
        {
            System.out.println("Khong tim thay khach hang.");
        }
        else{
            System.out.println("Da xoa khach hang.");
        }
        luuDuLieuVaoFile();
        
    }

    // Tìm kiếm thông tin khách hàng bằng id khách hàng
    public void timKiemKhachHang(Scanner scanner){
        System.out.print("Nhap id khach hang can tim: ");
        String idKhachHang = scanner.nextLine();
        boolean timThay = false;
        for (KhachHang  khachHang : danhSachKhachHang){
            if (khachHang.getId_KhachHang().equals(idKhachHang)){
                khachHang.Xuat();
                timThay = true;
                break;
            }
        }

        if (timThay == false){
            System.out.println("Khong tim thay khach hang.");
        }

    }

    // Sửa thông tin khách hàng (Trừ id khách hàng, username, password)
    public void suaKhachHang(KhachHang khachHang, Scanner scanner){
        
        while (true){
            System.out.println("Chon thong tin can sua");
            System.out.println("1. Ten.");
            System.out.println("2. So dien thoai.");
            System.out.println("3. Dia chi."); 
            System.out.println("4. Gioi Tinh.");
            System.out.println("5. Ngay sinh.");
            System.out.println("0. Luu lai va thoat.");
            int chon = scanner.nextInt();
            scanner.nextLine();
            
            switch (chon){
                case 1:
                    System.out.print("Nhap ten khach hang: ");
                    String tenKhachHang = scanner.nextLine();
                    khachHang.setTen_KhachHang(tenKhachHang);
                    break;
                case 2:
                    System.out.print("Nhap so dien thoai khach hang: ");
                    String soDienThoai = null;
                    soDienThoai = QuanLyNhanVien.kiemtraSDT(soDienThoai, scanner);
                    
                    khachHang.setSdt_KhachHang(soDienThoai);
                    break;
                case 3:
                    System.out.print("Nhap dia chi khach hang: ");
                    String diaChi = scanner.nextLine();
                    khachHang.setDiaChi_KhachHang(diaChi);
                    break;
                case 4:
                    System.out.println("Chon gioi tinh khach hang: ");
                    String gioiTinh = null;
                    while (true){
                        System.out.println("1. Nam");
                        System.out.println("2. Nu");
                        int choice = scanner.nextInt();
                        scanner.nextLine();
                        if (choice == 1 || choice == 2){
                            gioiTinh  = (choice == 1) ? "Nam" : "Nu";
                            break;
                        }
                        else
                            System.out.print("Chon sai, chon lai: ");
                    }
                    khachHang.setGioiTinh_KhachHang(gioiTinh);
                    break;
                case 5:
                    System.out.print("Nhap ngay sinh khach hang(dd/mm/yyyy): ");
                    NgayThangNam  ngayThangNam;
                    while (true){
                        
                        String ngayThangNam1 = scanner.nextLine();
                        String[] ns = ngayThangNam1.split("/");
                        if (ns.length == 3){
                            ngayThangNam = new NgayThangNam(Integer.parseInt(ns[0]), Integer.parseInt(ns[1]), Integer.parseInt(ns[2]));
                            break;
                        }
                        else
                        System.out.print("Nhap sai, nhap lai: ");

                    }
                    khachHang.setNS_KhachHang(ngayThangNam);
                    break;
                case 0:
                    System.out.println("Da luu va thoat.");
                    return;
                default:
                    System.out.println("Nhap sai. Vui long nhap lai.");
                    break;
            }
        }
    }

    // Menu cho admin (Hiện danh sách khách hàng + Xóa khách hàng + Sửa thông tin khách hàng(nhập id))
    public void menuAdmin(Scanner scanner) {
        while (true) {
            System.out.println("=============================================");
            System.out.println("1. Hien danh sach khach hang.");
            System.out.println("2. Xoa khach hang.");
            System.out.println("3. Chinh sua thong tin khach hang.");
            System.out.println("4. Tim kiem khach hang.");
            System.out.println("0. Thoat");
            int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println("=============================================");
            switch (choice) {
                case 1:
                    hienThiDanhSachKhachHang();
                    break;
                case 2:
                    xoaKhachHang(scanner);
                    luuDuLieuVaoFile();
                    break;
                case 3:
                    System.out.print("Nhap id khach hanng can sua: ");
                    String id = scanner.nextLine();
                    boolean  check = false;
                    KhachHang khachHang = null;
                    for (KhachHang kh : danhSachKhachHang) {
                        if (kh.getId_KhachHang().equals(id)) {
                            khachHang  = kh;
                            check = true;
                        }
                    }
                    if (check == false){
                        System.out.println("Khong tim thay khach hang nay.");
                        break;
                    }
                    suaKhachHang(khachHang, scanner);
                    luuDuLieuVaoFile();
                    break;
                case 4:
                    timKiemKhachHang(scanner);
                    break;
                case 0:
                    System.out.println("Da thoat.");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
            }
        }
    }
    
    //Menu cho user (Hiện thi thông tin khách hàng + Sửa thông tin khách hàng(chỉ được sửa thông tin của bản thân))
    public void menuUser(KhachHang khachHang, Scanner scanner) {
        hienThiThongTinKhachHang(khachHang);
        while (true) {
            System.out.println("1. Sua thong tin ca nhan");
            System.out.println("0. Thoat");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    suaKhachHang(khachHang, scanner);
                    luuDuLieuVaoFile();
                    break;
                case 0:
                    System.out.println("Da thoat");
                    return;
                default:
                    System.out.println("Lua chon khong phu hop.");
                    break;
            }
        }
    }

    public ArrayList<KhachHang> getDanhSachKhachHang() {
        return danhSachKhachHang;
    }

    public void setDanhSachKhachHang(ArrayList<KhachHang> danhSachKhachHang) {
        this.danhSachKhachHang = danhSachKhachHang;
    }

    public int size(){
        return danhSachKhachHang.size();
    }
}
