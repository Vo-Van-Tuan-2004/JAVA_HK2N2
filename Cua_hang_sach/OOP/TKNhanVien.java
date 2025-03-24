package OOP;

public class TKNhanVien {
    private String Id_NhanVien;
    private String User_NhanVien;
    private String Pass_NhanVien;
    private String ChucVu;
    
    public TKNhanVien(String id_NhanVien, String user_NhanVien, String pass_NhanVien, String chucVu) {
        Id_NhanVien = id_NhanVien;
        User_NhanVien = user_NhanVien;
        Pass_NhanVien = pass_NhanVien;
        if (ktraChucVu(chucVu))
        ChucVu = chucVu;
        else
            System.out.println("Chuc vu khong hop le!");
    }

    public String getId_NhanVien() {
        return Id_NhanVien;
    }

    public void setId_NhanVien(String id_NhanVien) {
        Id_NhanVien = id_NhanVien;
    }

    public String getUser_NhanVien() {
        return User_NhanVien;
    }

    public void setUser_NhanVien(String user_NhanVien) {
        User_NhanVien = user_NhanVien;
    }

    public String getPass_NhanVien() {
        return Pass_NhanVien;
    }

    public void setPass_NhanVien(String pass_NhanVien) {
        Pass_NhanVien = pass_NhanVien;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String chucVu) {
        if (chucVu.equalsIgnoreCase("QLNV")||chucVu.equalsIgnoreCase("QLKH")||chucVu.equalsIgnoreCase("QLSP")||chucVu.equalsIgnoreCase("QLHD")||chucVu.equalsIgnoreCase("NV"))
        this.ChucVu = chucVu;
        else 
            System.out.println("Khong co chuc vu nay!");
        
        
    }

    public boolean ktraChucVu(String chucVu) {
        return chucVu.equalsIgnoreCase("QLNV")||chucVu.equalsIgnoreCase("QLKH")||chucVu.equalsIgnoreCase("QLSP")||chucVu.equalsIgnoreCase("QLHD")||chucVu.equalsIgnoreCase("NV");
    }


    @Override
    public String toString() {
        return  Id_NhanVien + ", " + User_NhanVien + ", "
                + Pass_NhanVien + ", " + ChucVu;
    }
    
}
