package OOP;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class QuanLyHoaDon implements TaoID, QuanLyFile{
    private ArrayList<HoaDon> danhSachHoaDon;
    private static int counter = 0;
    // Constructor
    public QuanLyHoaDon() {
        danhSachHoaDon = new ArrayList<>();
    }

    // Phương thức thêm hóa đơn vào danh sách
    public void themHoaDon(HoaDon hoaDon) {
        danhSachHoaDon.add(hoaDon);
    }

    // Phương thức ghi dữ liệu hóa đơn vào file
    @Override
    public void luuDuLieuVaoFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("./file/HoaDon.txt"))) {
            for (HoaDon hoaDon : danhSachHoaDon) {
                writer.write(hoaDon.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Loi khi ghi du lieu vao file: " + e.getMessage());
        }
    }

    // Phương thức đọc dữ liệu hóa đơn từ file
    @Override
    public void layDuLieuTuFile() {   
        danhSachHoaDon.clear();
        QuanLySanPham qlsp = new QuanLySanPham();
        qlsp.layDuLieuTuFile();
        try (BufferedReader reader = new BufferedReader(new FileReader("./file/HoaDon.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                
                String[] parts = line.replace("[", "").replace("]", "").split(", ");
                
                String idHoaDon = parts[0];

                int currentId = Integer.parseInt(idHoaDon.substring(2));
                counter = Math.max(counter, currentId);

                String idNhanVien = parts[1];
                String idKhachHang = parts[2];
                double tongTien = Double.parseDouble(parts[parts.length - 3]);
                String date = parts[parts.length - 2];
                String TinhTrang = parts[parts.length - 1];

                ArrayList<ChitietHoaDon> ds = new ArrayList<>();
                int i = 3 ;
                while (i < parts.length - 3) {
                   String idSanPham = parts[i];
                   int soLuong = Integer.parseInt(parts[i + 1]);
                   //Double tien = Double.parseDouble(parts[i + 2 ]);
                   ChitietHoaDon muaSanPham = new ChitietHoaDon(qlsp.sanPham(idSanPham), soLuong);
                   ds.add(muaSanPham);
                   i += 3; 
                }
               
                HoaDon hoaDon = new HoaDon(idHoaDon, idNhanVien, idKhachHang, ds, tongTien, date, TinhTrang);
                danhSachHoaDon.add(hoaDon);
            }
        } catch (IOException e) {
            System.out.println("Loi khi doc du lieu tu file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Loi xu ly du lieu: " + e.getMessage());
        }
    }

    // Phương thức xuất chi tiết hóa đơn
    public void xuatChiTietHoaDon() {
        for (HoaDon hoaDon : danhSachHoaDon) {
            hoaDon.hienThiHoaDon();
            System.out.println("---------------------------------");
        }
    }

    // Phương thức get
    public ArrayList<HoaDon> getDanhSachHoaDon() {
        return danhSachHoaDon;
    }

    // Phương thức Set
    public void setDanhSachHoaDon(ArrayList<HoaDon> danhSachHoaDon) {
        this.danhSachHoaDon = danhSachHoaDon;
    }

    public void XuLyHoaDon(String idNV){
        int count = 0;
        for(HoaDon hd : danhSachHoaDon){
            if(hd.getTinhTrangDonHang().equals("Chua xu ly")){
                count++;
                hd.setTinhTrangDonHang("Da xu ly");
                hd.setIdNhanVien(idNV);
            }
        }
        if(count != 0){
            System.out.println("Da xu ly " + count + " hoa don.");
            luuDuLieuVaoFile(); // xử lý xong thì lưu lại vào file
        }
        else{
            System.out.println("Khong co hoa don chua duoc xu ly.");
        }
    }

    public void hienThiDanhSachHoaDon(){
        System.out.println("                DANH SACH HOA DON");
        for (HoaDon hd : danhSachHoaDon){
            System.out.println("=====================");             
            hd.hienThiHoaDon();
        }
    }

    public void qlhdMenuAdmin(String idNhanVien, Scanner scanner) {
        while (true) {
            System.out.println("=========================================================");
            System.out.println("1.Tim kiem hoa don");
            System.out.println("2.Liet ke hoa don theo khach hang");
            System.out.println("3.Liet ke hoa don theo ngay thanh toan");
            System.out.println("4.Liet ke hoa don theo nhan vien");
            System.out.println("5.Xu ly hoa don");
            System.out.println("6.Hien thi toan bo hoa don");
            System.out.println("0.Thoat");
            boolean  check = false;
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Nhap id hoa don: ");
                    String idhd = scanner.nextLine();
                    HoaDon hoadon = null;
                    for (HoaDon hd : danhSachHoaDon) {
                        if (hd.getIdHoaDon().equals(idhd)) {
                            hoadon = hd;
                            check = true;
                        }
                    }
                    if (check == false){
                        System.out.println("Khong tim thay hoa don nay.");
                        break;
                    }

                    hoadon.hienThiHoaDon();
                    break;
                case 2:
                    System.out.print("Nhap id khach hang: ");
                    String idkh = scanner.nextLine();
                    for (HoaDon hd : danhSachHoaDon) {
                        if (hd.getIdKhachHang().equals(idkh)) {
                            System.out.println("=====================");
                            hd.hienThiHoaDon(); // 1 khách hàng có thể có nhiều hoá đơn
                            check = true;
                        }
                    }
                    if (check == false){
                        System.out.println("Khong tim thay khach hang nay.");
                        break;
                    }
                    break;
                case 3:
                    String date;
                    while(true){
                        System.out.print("Nhap ngay thanh toan(dd/mm/yyyy): ");
                        date = scanner.nextLine();
                        String[] parts = date.split("/");
                        if(parts.length == 3 && CheckDate.kiemTraNgayHopLe(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), Integer.parseInt(parts[2]))){  
                            break;
                        }
                        else{
                            System.out.println("Nhap sai, vui long nhap lai");
                        }
                    }

                    for (HoaDon hd : danhSachHoaDon) {
                        if (hd.getNgayThanhToan().equals(date)) {   
                            System.out.println("=====================");             
                            hd.hienThiHoaDon(); // 1 ngày có thể có nhiều hóa đơn
                            check = true;
                        }
                    }
                    if (check == false){
                        System.out.println("Khong tim thay hoa don trong ngay.");
                        break;
                    }
                    break;
                case 4:
                    System.out.print("Nhap id nhan vien: ");
                    String idnv = scanner.nextLine();
                    for (HoaDon hd : danhSachHoaDon) {
                        if (hd.getIdNhanVien().equals(idnv)) {
                            System.out.println("=====================");
                            hd.hienThiHoaDon(); // 1 nhân viên có thể xử lý nhiều hoá đơn
                            check = true;
                        }
                    }
                    if (check == false){
                        System.out.println("Khong tim thay nhan vien nay.");
                        break;
                    }
                    break;
                case 5:
                    XuLyHoaDon(idNhanVien);
                    break;
                case 6:
                    hienThiDanhSachHoaDon();
                    break;
                case 0:
                    return;
                default:
                    System.out.print("Nhap sai, vui long nhap lai: ");
                    break;
            }
        }
    }

    @Override
    public String taoID() {
        counter++;
        return "HD" + String.format("%08d", counter);
    }

    public int size() {
        return danhSachHoaDon.size();
    }
    
}
