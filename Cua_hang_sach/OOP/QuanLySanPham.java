package OOP;

import java.io.*;
import java.time.Year;
import java.util.ArrayList;
import java.util.Scanner;

public class QuanLySanPham implements QuanLyFile{
    private ArrayList<SanPham> danhSachSanPham;
    private static int sachCounter = 0;
    private static int voCounter = 0;
    private static int butCounter = 0;


    public QuanLySanPham(){
        danhSachSanPham = new ArrayList<SanPham>();
    }

    public ArrayList<SanPham> getDanhSachSanPham() {
        return danhSachSanPham;
    }

    private String taoID(String loai){
        String prefix;
        int counter;

        switch (loai) {
            case "Sach":
                prefix = "SA";
                counter = sachCounter++;
                break;
            case "Vo":
                prefix = "VO";
                counter = voCounter++;
                break;
            case "But":
                prefix = "BU";
                counter = butCounter++;
                break;
            default:
                throw new IllegalArgumentException("Loai san pham khong hop le.");
        }
        
        // Định dạng ID với prefix và 8 chữ số từ counter
        return String.format("%s%08d", prefix, counter);
    }

    @Override
    public void layDuLieuTuFile() {
        // Đọc dữ liệu từ file Sach.txt
        try (BufferedReader br = new BufferedReader(new FileReader("./file/Sach.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 8) {
                    String id = parts[0];
                    String ten = parts[1];
                    Double gia = Double.parseDouble(parts[2]);
                    int soLuong = Integer.parseInt(parts[3]);
                    String tacGia = parts[4];
                    String theLoai = parts[5];
                    String nhaXuatBan = parts[6];
                    int NamXuatBan = Integer.parseInt(parts[7]);
                    Sach sach = new Sach(id, ten, gia, soLuong, tacGia, theLoai, nhaXuatBan, NamXuatBan);
                    danhSachSanPham.add(sach);

                    int idNumber = Integer.parseInt(id.substring(2));
                    if (idNumber >= sachCounter) {
                        sachCounter = idNumber + 1;
                    }
                } else {
                    System.err.println("Du lieu khong hop le o dong: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Loi khi doc file Sach.txt: " + e.getMessage());
        }

        // Đọc dữ liệu từ file Vo.txt
        try (BufferedReader br = new BufferedReader(new FileReader("./file/Vo.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 7) {
                    String id = parts[0];
                    String ten = parts[1];
                    Double gia = Double.parseDouble(parts[2]);
                    int soLuong = Integer.parseInt(parts[3]);
                    String loaiVo = parts[4];
                    String nhaSX = parts[5];
                    String chatLieu = parts[6];
                    Vo vo = new Vo(id, ten, gia, soLuong, loaiVo, nhaSX, chatLieu);
                    danhSachSanPham.add(vo);

                    int idNumber = Integer.parseInt(id.substring(2));
                    if (idNumber >= voCounter) {
                        voCounter = idNumber + 1;
                    }
                } else {
                    System.err.println("Du lieu khong hop le o dong: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Loi khi doc file Vo.txt: " + e.getMessage());
        }

        // Đọc dữ liệu từ file But.txt
        try (BufferedReader br = new BufferedReader(new FileReader("./file/But.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 7) {
                    String id = parts[0];
                    String ten = parts[1];
                    Double gia = Double.parseDouble(parts[2]);
                    int soLuong = Integer.parseInt(parts[3]);
                    String mau = parts[4];
                    String loai = parts[5];
                    String hang = parts[6];
                    But but = new But(id, ten, gia, soLuong, mau, loai, hang);
                    danhSachSanPham.add(but);

                    int idNumber = Integer.parseInt(id.substring(2));
                    if (idNumber >= butCounter) {
                        butCounter = idNumber + 1;
                    }
                } else {
                    System.err.println("Du lieu khong hop le o dong: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Loi khi doc file But.txt: " + e.getMessage());
        }
    }

    public void menu(Scanner scanner){
        while (true) {
            System.out.println("=============================================");
            System.out.println("1. Them moi san pham");
            System.out.println("2. Xoa san pham");
            System.out.println("3. Tim kiem san pham");
            System.out.println("4. Hien thi danh sach san pham");
            System.out.println("5. Sua san pham");
            System.out.println("0. Thoat chuong trinh");
            System.out.print("Nhap lua chon cua ban: ");
            int luaChon = scanner.nextInt();
            scanner.nextLine();
            System.out.println("=============================================");
            switch (luaChon) {
                case 1:
                    themMoiSanPham(scanner);
                    break;
                case 2:
                    xoaSanPham(scanner);
                    luuDuLieuVaoFile();
                    break;
                case 3:
                    timKiemSanPhamTheoID(scanner);
                    break;
                case 4:
                    hienThiDanhSachSanPham();
                    break;
                case 5:
                    suaSanPham(scanner);
                    luuDuLieuVaoFile();
                    break;
                case 0:
                    System.out.println("Chuong trinh ket thuc.");
                    luuDuLieuVaoFile();
                    return;
                default:
                    System.out.println("Lua chon khong hop le. Vui long chon lai.");
                    break;
            }
        }
    }

    //  Them moi san pham
    public void themMoiSanPham(Scanner scanner) {
        while (true) {
            System.out.println("Chon loai san pham muon them:");
            System.out.println("1. Sach.");
            System.out.println("2. Vo.");
            System.out.println("3. But.");
            System.out.println("0. Thoat.");
            System.out.print("Nhap lua chon cua ban: ");
            int luaChon = scanner.nextInt();
            scanner.nextLine();
            String tenSanPham;
            int giaSanPham = 0; // khai báo int để cho vào hàm kiểm tra
            int soLuongSanPham = 0;
            String idSanPham;

            switch (luaChon) {
                case 1:
                    System.out.print("Nhap ten san pham: ");
                    tenSanPham = scanner.nextLine();
                    System.out.print("Nhap gia san pham: ");
                    giaSanPham = QuanLyNhanVien.kiemtraSoAm(giaSanPham, scanner);
                    
                    System.out.print("Nhap so luong san pham: ");
                    soLuongSanPham = QuanLyNhanVien.kiemtraSoAm(soLuongSanPham, scanner);
                
                    System.out.print("Nhap ten tac gia: ");
                    String tenTacGia = scanner.nextLine();
                    System.out.print("Nhap the loai: ");
                    String theLoai = scanner.nextLine();
                    System.out.print("Nhap nha san xuat: ");
                    String nhaSX = scanner.nextLine();
                    System.out.print("Nhap nam xuat ban: ");
                    
                    int NamXuatBan;
                    while(true){
                        NamXuatBan = scanner.nextInt();
                        if(NamXuatBan >= 0 && NamXuatBan <= (int)Year.now().getValue()){
                            break;
                        }
                        else
                            System.out.println("gia tri nhap sai, hay nhap lai: ");
                    }
                    idSanPham = taoID("Sach");

                    SanPham sach = new Sach(idSanPham, tenSanPham, giaSanPham, soLuongSanPham, tenTacGia, theLoai, nhaSX, NamXuatBan);

                    danhSachSanPham.add(sach);
                    break;
                case 3:
                    System.out.print("Nhap ten san pham: ");
                    tenSanPham = scanner.nextLine();
                    System.out.print("Nhap gia san pham: ");
                    giaSanPham = QuanLyNhanVien.kiemtraSoAm(giaSanPham, scanner);
                    
                    System.out.print("Nhap so luong san pham: ");
                    soLuongSanPham = QuanLyNhanVien.kiemtraSoAm(soLuongSanPham, scanner);
                    
                    System.out.print("Nhap mau: ");
                    String mau  = scanner.nextLine();
                    System.out.print("Nhap loai but: ");
                    String loaiBut = scanner.nextLine();
                    System.out.print("Nhap hang but: ");
                    String hang = scanner.nextLine();
                    idSanPham = taoID("But");

                    SanPham but = new But(idSanPham, tenSanPham, giaSanPham, soLuongSanPham, mau, loaiBut, hang);

                    danhSachSanPham.add(but);
                    break;
                case 2:
                    System.out.print("Nhap ten san pham: ");
                    tenSanPham = scanner.nextLine();
                    System.out.print("Nhap gia san pham: ");
                    giaSanPham = QuanLyNhanVien.kiemtraSoAm(giaSanPham, scanner);
                    
                    System.out.print("Nhap so luong san pham: ");
                    soLuongSanPham = QuanLyNhanVien.kiemtraSoAm(soLuongSanPham, scanner);
                    
                    System.out.print("Nhap loai vo: ");
                    String loaiVo = scanner.nextLine();
                    System.out.print("Nhap nha san xuat: ");
                    String nhaSanXuat = scanner.nextLine();
                    System.out.print("Nhap chat lieu: ");
                    String chatLieu = scanner.nextLine();
                    idSanPham = taoID("Vo");

                    SanPham vo = new Vo(idSanPham, tenSanPham, giaSanPham, soLuongSanPham, loaiVo, nhaSanXuat, chatLieu);

                    danhSachSanPham.add(vo);
                    break;
                case 0:
                    System.out.println("<<<Da thoat>>>");
                    luuDuLieuVaoFile();
                    return;
                default:
                    System.out.println("Chon sai, xin chon lai");
                    break;

            }
        }
        
    }

    // Tìm kiêms sản phẩm theo ID
    public void timKiemSanPhamTheoID(Scanner scanner){
        System.out.print("Nhap id san pham: ");
        String id = scanner.nextLine();

        boolean timThay = false;

        for (SanPham sp : danhSachSanPham) {
            if (sp.getID_SanPham().equalsIgnoreCase(id)) {
                sp.hienThiThongTin();
                timThay = true;
                break; 
            }
        }

        if (!timThay) {
            System.out.println("Khong tim thay san pham co id: " + id);
        }
    }

    // Phương thức tìm kiếm sản phẩm theo thuộc tính
    public void timKiemSanPhamTheoThuocTinh() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Chon loai san pham muon tim:");
        System.out.println("1. Sach.");
        System.out.println("2. Vo.");
        System.out.println("3. But.");
        System.out.print("0. Thoat. ");
        int luaChon = scanner.nextInt();
        scanner.nextLine(); 

        switch (luaChon) {
            case 1:
                timKiemSach(scanner);
                break;
            case 2:
                timKiemVo(scanner);
                break;
            case 3:
                timKiemBut(scanner);
                break;
            case 0:
                scanner.close();
                return;
            default:
                System.out.println("Lua chon khong hop le.");
                break;
        }
    }

    // Tìm kiếm sản phẩm là sách
    private void timKiemSach(Scanner scanner) {
        System.out.println("Chon thuoc tinh sach muon tim:");
        System.out.println("1. Ten sach.");
        System.out.println("2. Tac gia.");
        System.out.println("3. The loai.");
        System.out.println("4. Nha xuat ban.");
        System.out.println("5. Nam xuat ban.");
        int luaChon = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nhap gia tri tim kiem: ");
        String giaTri = scanner.nextLine();
        
        for (SanPham sp : danhSachSanPham) {
            if (sp instanceof Sach) {
                Sach sach = (Sach) sp;
                boolean found = false;
                switch (luaChon) {
                    case 1 : 
                        found = sach.getTen_SanPham().contains(giaTri); 
                        break;
                    case 2 : 
                        found = sach.getTenTacGia().contains(giaTri); 
                        break;
                    case 3 : 
                        found = sach.getTheLoai().contains(giaTri);
                        break;
                    case 4 : 
                        found = sach.getNhaXuatBan().contains(giaTri);
                        break;
                    case 5 : 
                        found = sach.getNamXuatBan() == (Integer.parseInt(giaTri));
                        break;
                    default : 
                        System.out.println("Lua chon khong hop le.");
                        break;
                }
                if (found) {
                    System.out.println("\n");
                    sach.hienThiThongTin();
                    System.out.println("\n");
                    return;
                }
            }
        }
        System.out.println("\n");
        System.out.println("Khong tim thay san pham");
        System.out.println("\n");
    }

    // Tìm kiếm sản phẩm là vở
    private void timKiemVo(Scanner scanner) {
        System.out.println("Chon thuoc tinh vo muon tim:");
        System.out.println("1. Ten vo.");
        System.out.println("2. Loai vo.");
        System.out.println("3. Nha san xuat.");
        System.out.println("4. Chat lieu.");
        int luaChon = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nhap gia tri tim kiem: ");
        String giaTri = scanner.nextLine();

        for (SanPham sp : danhSachSanPham) {
            if (sp instanceof Vo) {
                Vo vo = (Vo) sp;
                boolean found = false;
                switch (luaChon) {
                    case 1 : found = vo.getTen_SanPham().contains(giaTri); break;
                    case 2 : found = vo.getLoai_Vo().contains(giaTri); break;
                    case 3 : found = vo.getNhaSanXuat().contains(giaTri); break;
                    case 4 : found = vo.getChatLieu().contains(giaTri); break;
                    default : System.out.println("Lua chon khong hop le."); break;
                }
                if (found) {
                    System.out.println("\n");
                    vo.hienThiThongTin();
                    System.out.println("\n");
                    return;
                }
            }
        }
        
        System.out.println("\n");
        System.out.println("Khong tim thay san pham");
        System.out.println("\n");   
    }

    // Tìm kiếm sản phẩm là bút
    private void timKiemBut(Scanner scanner) {
        System.out.println("Chon thuoc tinh but muon tim:");
        System.out.println("1. Ten but.");
        System.out.println("2. Mau but.");
        System.out.println("3. Loai but.");
        System.out.println("4. Hang san xuat.");
        int luaChon = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nhap gia tri tim kiem: ");
        String giaTri = scanner.nextLine();

        for (SanPham sp : danhSachSanPham) {
            if (sp instanceof But) {
                But but = (But) sp;
                boolean found = false;
                switch (luaChon) {
                    case 1 : found = but.getTen_SanPham().contains(giaTri); break;
                    case 2 : found = but.getMau().contains(giaTri); break;
                    case 3 : found = but.getLoai_But().contains(giaTri); break;
                    case 4 : found = but.getHang().contains(giaTri); break;
                    default : System.out.println("Lua chon khong hop le."); break;
                }
                if (found) {
                    System.out.println("\n");
                    but.hienThiThongTin();
                    System.out.println("\n");
                    return;
                }
            }
        }
        System.out.println("\n");
        System.out.println("Khong tim thay san pham");
        System.out.println("\n");
    }


    // xoa san pham
    public void xoaSanPham(Scanner scanner) {
        System.out.print("Nhap ID san pham can xoa: ");
        String idSanPham = scanner.nextLine();

        boolean co = false;
        for (int i = 0; i < danhSachSanPham.size(); i++){
            if (danhSachSanPham.get(i).getID_SanPham().equals(idSanPham)){
                co = true;  
                danhSachSanPham.remove(i);
                break;
            }
            
            
        }
        if(co==false)
        {
            System.out.println("Khong tim thay san pham.");
        }
        else{
            System.out.println("Da xoa san pham.");
        }
        luuDuLieuVaoFile();
    }

    public void suaSanPham(Scanner scanner) {
        System.out.print("Nhap ID san pham can sua: ");
        String idSanPham = scanner.nextLine();
    
        // Tìm sản phẩm theo ID
        SanPham sanPham = null;
        for (SanPham sp : danhSachSanPham) {
            if (sp.getID_SanPham().equals(idSanPham)) {
                sanPham = sp;
                break;
            }
        }
    
        if (sanPham == null) {
            System.out.println("Khong tim thay san pham voi ID: " + idSanPham);
            return;
        }
    
        sanPham.hienThiThongTin();
        System.out.println("Bat dau sua thong tin san pham...");
    
        // Nếu là sách
        if (sanPham instanceof Sach) {
            Sach sach = (Sach) sanPham;
            suaSanPham2(sach, scanner);
        }
    
        // Nếu là vở
        else if (sanPham instanceof Vo) {
            Vo vo = (Vo) sanPham;
            suaSanPham2(vo, scanner);
        }
    
        // Nếu là bút
        else if (sanPham instanceof But) {
            But but = (But) sanPham;
            suaSanPham2(but, scanner);
        }
    
        System.out.println("Da cap nhat thong tin san pham thanh cong!");
    }

    public void hienThiDanhSachSanPham() {
        int i = 1;
        for (SanPham sp : danhSachSanPham) {
            System.out.print(i + ". ");
            sp.hienThiThongTin();
            i++;
        }
    }

    @Override
    public void luuDuLieuVaoFile() {
        try {
            // Ghi dữ liệu vào file Sach.txt
            BufferedWriter sachWriter = new BufferedWriter(new FileWriter("./file/Sach.txt"));
            for (SanPham sp : danhSachSanPham) {
                if (sp instanceof Sach) {
                    Sach sach = (Sach) sp;
                    sachWriter.write(sach.getID_SanPham() + ", " + 
                                     sach.getTen_SanPham() + ", " + 
                                     sach.getGia_SanPham() + ", " + 
                                     sach.getSoLuong_SanPham() + ", " + 
                                     sach.getTenTacGia() + ", " + 
                                     sach.getTheLoai() + ", " + 
                                     sach.getNhaXuatBan() + ", " + 
                                     sach.getNamXuatBan());
                    sachWriter.newLine();
                }
            }
            sachWriter.close();
    
            // Ghi dữ liệu vào file Vo.txt
            BufferedWriter voWriter = new BufferedWriter(new FileWriter("./file/Vo.txt"));
            for (SanPham sp : danhSachSanPham) {
                if (sp instanceof Vo) {
                    Vo vo = (Vo) sp;
                    voWriter.write(vo.getID_SanPham() + ", " + 
                                   vo.getTen_SanPham() + ", " + 
                                   vo.getGia_SanPham() + ", " + 
                                   vo.getSoLuong_SanPham() + ", " + 
                                   vo.getLoai_Vo() + ", " + 
                                   vo.getNhaSanXuat() + ", " + 
                                   vo.getChatLieu());
                    voWriter.newLine();
                }
            }
            voWriter.close();
    
            // Ghi dữ liệu vào file But.txt
            BufferedWriter butWriter = new BufferedWriter(new FileWriter("./file/But.txt"));
            for (SanPham sp : danhSachSanPham) {
                if (sp instanceof But) {
                    But but = (But) sp;
                    butWriter.write(but.getID_SanPham() + ", " + 
                                    but.getTen_SanPham() + ", " + 
                                    but.getGia_SanPham() + ", " + 
                                    but.getSoLuong_SanPham() + ", " + 
                                    but.getMau() + ", " + 
                                    but.getLoai_But() + ", " + 
                                    but.getHang());
                    butWriter.newLine();
                }
            }
            butWriter.close();
        } catch (IOException e) {
            System.err.println("Lỗi khi lưu dữ liệu vào file: " + e.getMessage());
        }
    }

    // Phương thức lấy sản phẩm theo chỉ số
    public SanPham get(int index) {
        return danhSachSanPham.get(index);
    }

    public SanPham sanPham(String idSanPham){
        SanPham sp = null;
        
        for (SanPham sP : danhSachSanPham) {
            if (sP.getID_SanPham().equals(idSanPham)) {
                sp = sP;
                break;
            }
        }
        if (sp instanceof Sach){
            Sach sPS = (Sach) sp;
            return sPS;
        }else if (sp instanceof Vo){
            Vo sPV = (Vo) sp;
            return sPV;
        }
        But sPB = (But) sp;
        return sPB;
    }

    public void suaSanPham2(Sach sp, Scanner scanner){
        while(true){
            System.out.println("============================================");
            System.out.println("1. Ten san pham.");
            System.out.println("2. Gia moi.");
            System.out.println("3. So luong.");
            System.out.println("4. Ten tac gia.");
            System.out.println("5. The loai.");
            System.out.println("6. Nha xuat ban.");
            System.out.println("7. Nam xuat ban.");
            System.out.println("0. Luu lai va thoat.");

            System.out.print("Nhap tuy chon: "); int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println("============================================");

            switch (choice) {
                case 1:
                    System.out.print("Nhap ten san pham moi: ");
                    sp.setTen_SanPham(scanner.nextLine());
                    break;

                case 2:
                    System.out.print("Nhap gia moi: ");
                    int giamoi = 0;
                    giamoi = QuanLyNhanVien.kiemtraSoAm(giamoi, scanner);
                    
                    sp.setGia_SanPham(giamoi);
                    break;

                case 3:
                    System.out.print("Nhap so luong moi: ");
                    int soluongmoi = 0;
                    soluongmoi = QuanLyNhanVien.kiemtraSoAm(soluongmoi, scanner);
                    
                    sp.setSoLuong_SanPham(soluongmoi); 
                    break;
                
                case 4:
                    System.out.print("Nhap ten tac gia moi: ");
                    sp.setTenTacGia(scanner.nextLine());
                    break;
                
                case 5:
                    System.out.print("Nhap the loai moi: ");
                    sp.setTheLoai(scanner.nextLine());
                    break;
                    
                case 6:
                    System.out.print("Nhap nha xuat ban moi: ");
                    sp.setNhaXuatBan(scanner.nextLine());
                    break;

                case 7:
                    System.out.print("Nhap nam xuat ban moi: ");
                    int NamXuatBan;
                    while (true) {
                        NamXuatBan = scanner.nextInt();
                        scanner.nextLine();
                        if(NamXuatBan >= 0 && NamXuatBan <= (int)Year.now().getValue()){
                            break;
                        }
                        else
                            System.out.println("gia tri nhap sai, hay nhap lai: ");
                    }
                    sp.setNamXuatBan(NamXuatBan);
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Lua chon khong hop le.");
                    break;
            }
        }
    }

    public void suaSanPham2(Vo sp, Scanner scanner){

        while(true){
            System.out.println("============================================");
            System.out.println("1. Ten san pham.");
            System.out.println("2. Gia moi.");
            System.out.println("3. So luong.");
            System.out.println("4. Loai.");
            System.out.println("5. Nha san xuat.");
            System.out.println("6. Chat lieu.");
            System.out.println("0. Luu lai va thoat.");

            System.out.print("Nhap tuy chon: "); int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println("============================================");

            switch (choice) {
                case 1:
                System.out.print("Nhap ten san pham moi: ");
                    sp.setTen_SanPham(scanner.nextLine());
                    break;

                case 2:
                    System.out.print("Nhap gia moi: ");
                    int giamoi = 0;
                    giamoi = QuanLyNhanVien.kiemtraSoAm(giamoi, scanner);
                    
                    sp.setGia_SanPham(giamoi);
                    break;

                case 3:
                    System.out.print("Nhap so luong moi: ");
                    int soluongmoi = 0;
                    soluongmoi = QuanLyNhanVien.kiemtraSoAm(soluongmoi, scanner);
                    
                    sp.setSoLuong_SanPham(soluongmoi); 
                    break;
                
                case 4:
                    System.out.print("Nhap loai vo moi: ");
                    sp.setLoai_Vo(scanner.nextLine());
                    break;
                
                case 5:
                    System.out.print("Nhap nha san xuat moi: ");
                    sp.setNhaSanXuat(scanner.nextLine());
                    break;
                    
                case 6:
                    System.out.print("Nhap chat lieu moi: ");
                    sp.setChatLieu(scanner.nextLine());
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Lua chon khong hop le.");
                    break;
            } 
        }
    }
    
    public void suaSanPham2(But sp, Scanner scanner){

        while(true){
            System.out.println("============================================");
            System.out.println("1. Ten san pham.");
            System.out.println("2. Gia moi.");
            System.out.println("3. So luong.");
            System.out.println("4. Mau.");
            System.out.println("5. Loai.");
            System.out.println("6. Nha san xuat.");
            System.out.println("0. Luu lai va thoat.");

            System.out.print("Nhap tuy chon: "); int choice = scanner.nextInt();
            scanner.nextLine();
            System.out.println("============================================");

            switch (choice) {
                case 1:
                    System.out.print("Nhap ten san pham moi: ");
                    sp.setTen_SanPham(scanner.nextLine());
                    break;

                case 2:
                    System.out.print("Nhap gia moi: ");
                    int giamoi = 0;
                    giamoi = QuanLyNhanVien.kiemtraSoAm(giamoi, scanner);
                    
                    sp.setGia_SanPham(giamoi);
                    break;

                case 3:
                    System.out.print("Nhap so luong moi: ");
                    int soluongmoi = 0;
                    soluongmoi = QuanLyNhanVien.kiemtraSoAm(soluongmoi, scanner);
                    
                    sp.setSoLuong_SanPham(soluongmoi); 
                    break;
                
                case 4:
                    System.out.print("Nhap mau moi: ");
                    sp.setMau(scanner.nextLine());
                    break;
                
                case 5:
                    System.out.print("Nhap loai but moi: ");
                    sp.setLoai_But(scanner.nextLine());
                    break;
                    
                case 6:
                    System.out.print("Nhap hang san xuat moi: ");
                    sp.setHang(scanner.nextLine());
                    break;

                case 0:
                    return;

                default:
                    System.out.println("Lua chon khong hop le.");
                    break;
            } 
        }
    }

    // Phương thức lấy kích thước của danh sách sản phẩm
    public int size() {
        return danhSachSanPham.size();
    }
    
}
