package OOP;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class QuanLyNhanVien implements TaoID, CheckDate, QuanLyFile{
    private ArrayList<NhanVien> danhSachNhanVien;
    private static int counter = 0;

    public QuanLyNhanVien() {
        danhSachNhanVien = new ArrayList<>();
    }

    // Phương thức lấy dữ liệu từ file "NhanVien.txt" và "TKNhanVien.txt"
    @Override
    public void layDuLieuTuFile() {

        try (BufferedReader brNhanVien = new BufferedReader(new FileReader("./file/NhanVien.txt"));
             BufferedReader brTKNhanVien = new BufferedReader(new FileReader("./file/TKNhanVien.txt"))) {

            String lineNhanVien, lineTKNhanVien;

            while ((lineNhanVien = brNhanVien.readLine()) != null && (lineTKNhanVien = brTKNhanVien.readLine()) != null) {
                String[] nvInfo = lineNhanVien.split(", ");
                String[] tkInfo = lineTKNhanVien.split(", ");
                
                if (nvInfo.length < 5 || tkInfo.length < 4) {
                    System.err.println("Du lieu ko hop le");
                    continue; // Bỏ qua dòng không hợp lệ
                }

                String[] cns = nvInfo[4].split("/");
                if (cns.length == 3) {
                int[] ns = new int[cns.length];
                    for (int i = 0; i < cns.length; i++) { 
                        ns[i] = Integer.parseInt(cns[i]);
                    }

                int currentId = Integer.parseInt(tkInfo[0].substring(2));
                counter = Math.max(counter, currentId);

                TKNhanVien tkNhanVien = new TKNhanVien(tkInfo[0], tkInfo[1], tkInfo[2], tkInfo[3]);
                NgayThangNam  ngayThangNam = new NgayThangNam(ns[0], ns[1], ns[2]);
                NhanVien nhanVien = new NhanVien(nvInfo[0], nvInfo[1], nvInfo[2], nvInfo[3], tkNhanVien, ngayThangNam , nvInfo[5] );

                danhSachNhanVien.add(nhanVien);
                }   
            }
        } catch (IOException e) {
            System.err.println("Xay ra loi khi doc file: " + e.getMessage());
        } catch (NumberFormatException e) {
        System.err.println("Du lieu trong file khong hop le: " + e.getMessage());
    }
    }

    // Luu thong tin vao file
    @Override
    public void luuDuLieuVaoFile() {

        try (BufferedWriter bwNhanVien = new BufferedWriter(new FileWriter("./file/NhanVien.txt"));
            BufferedWriter bwTKNhanVien = new BufferedWriter(new FileWriter("./file/TKNhanVien.txt"))) {

            for(NhanVien nhanVien : danhSachNhanVien){
                bwNhanVien.write(nhanVien.toString());
                bwNhanVien.newLine();

                TKNhanVien tkNhanVien = nhanVien.getTK_NhanVien();
                bwTKNhanVien.write(tkNhanVien.toString());
                bwTKNhanVien.newLine();
            }
        } catch (IOException e){
            e.printStackTrace(); // Xử lý lỗi nếu có
        }
    }

    @Override
    public String taoID() {
        counter++;
        return "NV" + String.format("%08d", counter);
    }

    public ArrayList<NhanVien> getDanhSachNhanVien() {
        return danhSachNhanVien;
    }

    public static String kiemtraSDT(String sdt, Scanner scanner){
        while(true){
            sdt = scanner.nextLine();
            if(sdt.length() == 10){
                break;
            }
            else
                System.out.print("so dien thoai phai gom 10 chu so, hay nhap lai: ");
        }
        return sdt;
    }

    public static String kiemtraPass(String pass, Scanner scanner){
        while(true){
            pass = scanner.nextLine();
            if(pass.length() >= 8){
                break;
            }
            else
                System.out.print("Mat khau phai dai it nhat 8 ky tu, hay nhap lai: ");
        }
        return pass;
    }
    
    //Them nhan vien moi
    public void themNhanVien(Scanner scanner) {
        System.out.print("Ten nhan vien: ");        //Ten
        String tenNhanVien = scanner.nextLine();
        System.out.print("So CMND: ");              //CMND
        String soCMND = scanner.nextLine();
        System.out.print("Dia chi: ");              //Dia chi
        String diaChi = scanner.nextLine();
        System.out.print("SDT: ");                  //So dien thoai
        String soDienThoai = null;
        soDienThoai = kiemtraSDT(soDienThoai, scanner);    
        System.out.print("Chon Chuc vu: \n");              //Chuc vu   
        String chucVu = null;
        System.out.println("1: Quan ly nhan vien" );
        System.out.println("2: Quan ly khach hang" );
        System.out.println("3: Quan ly san pham" );
        System.out.println("4: Quan ly hoa don" );
        System.out.println("5: Nhan vien" );
        while(chucVu==null){  
            int cv;
            cv = scanner.nextInt() ;
            scanner.nextLine();
            switch (cv) {
                case 1: chucVu = "QLNV";
                    break;
                case 2: chucVu = "QLKH";
                    break;
                case 3: chucVu = "QLSP";
                    break;
                case 4: chucVu = "QLHD";
                    break;
                case 5: chucVu ="NV";
                    break;
                default:
                    System.out.println("Nhap loi. Hay nhap lai: " ); 
            }
        }

        int check = 1;
        String username;
        String password= null;
        do{
            System.out.print("Cap Username nhan vien: ");    //user name
            username = scanner.nextLine();
            System.out.print("Cap Password nhan vien: ");    //pass
            password = kiemtraPass(password, scanner);

            for(NhanVien nv: danhSachNhanVien){
                TKNhanVien tk = nv.getTK_NhanVien();
                if(tk.getUser_NhanVien().equals(username)){ // trùng tk thì cho nhập lại
                    System.out.println("Ten dang nhap da ton tai");
                    check = 0;
                    break;
                }
                else{
                    check = 1;
                }
            }
        }while(check == 0);

        System.out.print("Nhap ngay sinh(dd/mm/yyyy): ");
        NgayThangNam  ngayThangNam;
        while (true){
            String ngayThangNam1 = scanner.nextLine();
            String[] ns = ngayThangNam1.split("/");
            if (ns.length == 3 && CheckDate.kiemTraNgayHopLe(Integer.parseInt(ns[0]), Integer.parseInt(ns[1]), Integer.parseInt(ns[2]))){
                ngayThangNam = new NgayThangNam(Integer.parseInt(ns[0]), Integer.parseInt(ns[1]), Integer.parseInt(ns[2]));
                break;
            }
            else
            System.out.print("Nhap sai, nhap lai: ");
        }
        String maNhanVien = taoID();

        System.out.println("Nhap so de Chon Gioi Tinh nhan vien: "); 
        System.out.println("============");
        System.out.println("|1: Nam    |");
        System.out.println("|2: Nu     |" );
        System.out.println("============");
        int gt;
        while(true){
            gt = scanner.nextInt() ;
            if(gt==1||gt==2){
                break;
            }
            else
                System.out.println("Chon sai hay nhap lai.");
        }
        String GTNhanVien= null;
        if(gt==1){
            GTNhanVien = "Nam" ;
        }else
            GTNhanVien = "Nu";

        TKNhanVien tkNhanVienMoi = new TKNhanVien(maNhanVien, username, password, chucVu);

        NhanVien nhanVienMoi = new NhanVien(tenNhanVien, soCMND, diaChi, soDienThoai, tkNhanVienMoi, ngayThangNam , GTNhanVien);
        
        danhSachNhanVien.add(nhanVienMoi) ;   
    }

    //Xoa nhan vien
    public void xoaNhanVien(Scanner scanner){
        System.out.print("Nhap ma nhan vien can xoa:");
        String maNhanVienXoa = scanner.nextLine();
        
        for (int i = danhSachNhanVien.size() - 1; i >= 0; i--){
            NhanVien nv = danhSachNhanVien.get(i);
            TKNhanVien tk =  nv.getTK_NhanVien();
            if  (tk.getId_NhanVien().equals(maNhanVienXoa)){
                danhSachNhanVien.remove(i);
                System.out.print("Da xoa nhan vien\n");
                return;
            }   
        }
        System.out.println("Khong tim thay nhan vien.");
    }

    //Sua thong tin nhan vien
    public void suaThongTinNhanVien(Scanner scanner){
        System.out.print("Nhap id nhan vien can sua: ");
        NhanVien nhanVienSua = null; 
        
        while(nhanVienSua==null)
        {
            String idSua = scanner.nextLine();

            for(NhanVien nhanVien : danhSachNhanVien){
                TKNhanVien tk = nhanVien.getTK_NhanVien();
                if(tk.getId_NhanVien().equals(idSua)){
                    nhanVienSua = nhanVien;
                    System.out.println(nhanVien.toString());
                    break;
                }
            }
            if(nhanVienSua==null)
                {
                    System.out.print("Khong tim thay nhan vien nay.Nhap Lai: ");  
                }
        }

        while (true) {
            System.out.println("================ MENU ================");
            System.out.println("|Chon thong tin can sua:              |");
            System.out.println("|1. Ho va ten.                        |");
            System.out.println("|2. Ngay sinh.                        |");
            System.out.println("|3. Dia chi.                          |");
            System.out.println("|4. SDT.                              |");
            System.out.println("|5. CCCD.                             |");
            System.out.println("|6. Chuc vu.                          |");
            System.out.println("|7. Gioi Tinh.                        |");
            System.out.println("|0. Luu lai va thoat.                 |");
            System.out.println("======================================");
            int choice = scanner.nextInt();
            scanner.nextLine();
                
            switch (choice) {
                case 1:
                    System.out.print("Nhap ten moi: ");
                    String tenMoi = scanner.nextLine();
                    nhanVienSua.setTen_NhanVien(tenMoi);
                    break;
                case 2:
                    System.out.print("Nhap ngay sinh(dd/mm/yyyy): ");
                    NgayThangNam  ngayThangNam;
                    while (true){
                        
                        String ngayThangNam1 = scanner.nextLine();
                        String[] ns = ngayThangNam1.split("/");
                        if (ns.length == 3 && CheckDate.kiemTraNgayHopLe(Integer.parseInt(ns[0]), Integer.parseInt(ns[1]), Integer.parseInt(ns[2]))){
                            ngayThangNam = new NgayThangNam(Integer.parseInt(ns[0]), Integer.parseInt(ns[1]), Integer.parseInt(ns[2]));
                            break;
                        }
                        else
                        System.out.print("Nhap sai, nhap lai: ");

                    }
                    nhanVienSua.setNgaySinh_NhanVien(ngayThangNam);
                    break;
                case 3:
                    System.out.print("Nhap dia chi: ");
                    String diaChi = scanner.nextLine();
                    nhanVienSua.setDiaChi_NhanVien(diaChi);
                    break;
                case 4:
                    System.out.print("Nhap So Dien Thoai: ");
                    String soDienThoai = null;
                    soDienThoai = kiemtraSDT(soDienThoai, scanner);

                    nhanVienSua.setSDT_NhanVien(soDienThoai);
                    break;
                case 5:
                    System.out.print("Nhap CCCD: ");
                    String cccd = scanner.nextLine();
                    nhanVienSua.setCCCD_NhanVien(cccd);
                    break;
                case 6:
                    System.out.println("Chon chuc vu: ");
                    String chucVu = null;
                    System.out.println("1: Quan ly nhan vien " );
                    System.out.println("2: Quan ly khach hang" );
                    System.out.println("3: Quan ly san pham" );
                    System.out.println("4: Quan ly hoa don" );
                    System.out.println("5: Nhan vien" );
                    while(chucVu==null)

                         {  int cv;
                            cv = scanner.nextInt() ;
                            scanner.nextLine();
                            switch (cv) {
                                case 1: chucVu = "QLNV";
                                    break;
                                case 2: chucVu = "QLKH";
                                    break;
                                case 3: chucVu = "QLSP";
                                    break;
                                case 4: chucVu = "QLHD";
                                    break;
                                case 5: chucVu ="NV";
                                    break;
                                default:
                                System.out.println("Nhap loi. Hay nhap lai: " );
                                
                            }
                         }

                    nhanVienSua.getTK_NhanVien().setChucVu(chucVu);
                    break;
                case 7:
                    System.out.println(" Chon gioi tinh: ");
                    int gt;
                    while(true){  
                        System.out.println("============");
                        System.out.println("|1: Nam    |");
                        System.out.println("|2: Nu     |" );
                        System.out.println("============");
                        gt = scanner.nextInt() ;
                        if(gt==1||gt==2){
                            break;
                        }
                        else 
                            System.out.println("Chon sai chon lai.");
                    }
                    String GTNhanVien= null;
                    if(gt==1){
                        GTNhanVien = "Nam" ;
                        System.out.print("da sua thanh nam \n" );
                    }
                    else{
                        GTNhanVien = "Nu";
                        System.out.print("da sua thanh nu \n" );
                    }
                    
                    nhanVienSua.setGioiTinh_NhanVien(GTNhanVien);
                    break;
                case 0:
                    System.out.println("Da luu va thoat.");
                    return;

                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai.");
                    break;
            }
        }
        
    }

    //Tim kiem nhan vien theo id
    public void  timKiemNhanVienTheoId(String idTimKiem){
        for(NhanVien nhanVien : danhSachNhanVien){
            TKNhanVien tk = nhanVien.getTK_NhanVien();
            if(tk.getId_NhanVien().equals(idTimKiem)){
                System.out.println(nhanVien.toString() +", "+ nhanVien.getTK_NhanVien().toString());
                return ;
            }
        }
        System.out.println("Khong tim thay nhan vien!");
        return ;
    }

    //Hien thi danh sach toan bo nhan vien
    public void hienThiDanhSachNhanVien() {
        for(NhanVien nhanVien : danhSachNhanVien){
            System.out.println(nhanVien.toString());
            TKNhanVien tk =  nhanVien.getTK_NhanVien();
            System.out.println(tk.toString());
        }
    }

     public void menu(Scanner scanner) {

         while (true) {
             System.out.println("================ MENU ================");
             System.out.println("|Cac hoat dong ban co the dung:      |");
             System.out.println("|1. Them moi nhan vien.              |");
             System.out.println("|2. Xoa nhan vien.                   |");
             System.out.println("|3. Sua thong tin nhan vien.         |");
             System.out.println("|4. Tim kiem nhan vien.              |");
             System.out.println("|5. Hien thi danh sach nhan vien.    |");
             System.out.println("|0. Thoat chuong trinh.              |");
             System.out.println("======================================");

             int choice = scanner.nextInt();
             scanner.nextLine();
            
             switch (choice) {
                 case 1:
                     themNhanVien(scanner);
                     luuDuLieuVaoFile();
                     System.out.println("<<<<Da them moi nhan vien thanh cong>>>>");
                     break;
                
                 case 2:
                     xoaNhanVien(scanner);
                     luuDuLieuVaoFile();
                     break;

                 case 3:
                     suaThongTinNhanVien(scanner);
                     luuDuLieuVaoFile();
                     break;

                 case 4:
                     System.out.println("Nhap id nhan vien can tim:");
                     String maNhanVienTimKiem = scanner.nextLine();
                     timKiemNhanVienTheoId(maNhanVienTimKiem);
                     break;

                 case 5:
                     System.out.println("Danh sach nhan vien:");
                     hienThiDanhSachNhanVien();
                     break;

                 case 0:
                     System.out.println("Thoat chuong trinh.");
                     return;

                 default:
                     System.out.println("Lua chon khong hop le. Vui long chon lai.");
                     break;
             }
         }
     }

    public int size(){
        return danhSachNhanVien.size();
    }

    public static int kiemtraSoAm(int number, Scanner scanner){
        while(true){
            number = scanner.nextInt();
            scanner.nextLine();
            if(number >= 0){
                break;
            }
            else
                System.out.println("gia tri khong duoc be hon 0, hay nhap lai: ");
        }
        return number;
    }

}
