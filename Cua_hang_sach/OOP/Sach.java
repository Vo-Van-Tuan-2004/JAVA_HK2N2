package OOP;

public class Sach extends SanPham{
    private String TenTacGia;
    private String TheLoai;
    private String NhaXuatBan;
    private int NamXuatBan;


    public Sach(String tenTacGia, String theLoai, String nhaXuatBan, int namXuatBan) {
        TenTacGia = tenTacGia;
        TheLoai = theLoai;
        NhaXuatBan = nhaXuatBan;
        NamXuatBan = namXuatBan;
    }

    public Sach(String id, String ten, double gia, int soluong, String tenTacGia, String theLoai, String nhaXuatBan,
            int namXuatBan) {
        super(id, ten, gia, soluong);
        TenTacGia = tenTacGia;
        TheLoai = theLoai;
        NhaXuatBan = nhaXuatBan;
        NamXuatBan = namXuatBan;
    }

    public String getTenTacGia() {
        return TenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        TenTacGia = tenTacGia;
    }

    public String getTheLoai() {
        return TheLoai;
    }

    public void setTheLoai(String theLoai) {
        TheLoai = theLoai;
    }

    public String getNhaXuatBan() {
        return NhaXuatBan;
    }

    public void setNhaXuatBan(String nhaXuatBan) {
        NhaXuatBan = nhaXuatBan;
    }

    public int getNamXuatBan() {
        return NamXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        NamXuatBan = namXuatBan;
    }

    @Override
    public void hienThiThongTin() {
        System.out.println("Sach: " + super.toString() + ", " + this.toString());
    }

    public String hienThiThongTin1(){
        return super.getTen_SanPham() + ", " + super.getGia_SanPham() + ", " + this.toString();
    }

    @Override
    public String toString() {
        return "TenTacGia = " + TenTacGia + ", TheLoai = " + TheLoai + ", NhaXuatBan = " + NhaXuatBan + ", NamXuatBan = "
                + NamXuatBan;
    }

    

    
}