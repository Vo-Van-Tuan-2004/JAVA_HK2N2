package OOP;

public class KhachHang {
   private String Id_KhachHang ;
   private String Ten_KhachHang ;
   private String Sdt_KhachHang ;
   private String GioiTinh_KhachHang;
   private String DiaChi_KhachHang;
   private String Username_KhachHang;
   private String Pass_KhachHang;
   private NgayThangNam NgaySinh_KhachHang;
   public KhachHang ()
   {
      this.Id_KhachHang = "";
      this.Ten_KhachHang = "";
      this.Sdt_KhachHang = "";
      this.GioiTinh_KhachHang = "";
      this.DiaChi_KhachHang = "";
      this.Username_KhachHang = "";
      this.Pass_KhachHang = "";
   }
   public KhachHang(String Id,String Ten, String Sdt,String gioiTinh, String DiaChi, String User , String Pass, NgayThangNam NgaySinh)

   {
      this.Id_KhachHang = Id;
      this.Ten_KhachHang = Ten;
      this.Sdt_KhachHang = Sdt;
      this.GioiTinh_KhachHang = gioiTinh;
      this.DiaChi_KhachHang = DiaChi;
      this.Username_KhachHang = User;
      this.Pass_KhachHang = Pass;
      this.NgaySinh_KhachHang = NgaySinh;
   }

   // Getter and Setter Id_KhachHang
   public String getId_KhachHang() {
      return Id_KhachHang;
   }

   public void setId_KhachHang(String id_KhachHang) {
      Id_KhachHang = id_KhachHang;
   }
   
   // Getter and Setter for GioTinh_KhachHang
   public String getGioiTinh_KhachHang() {
      return GioiTinh_KhachHang;
   }

   public void setGioiTinh_KhachHang(String gioiTinh_KhachHang) {
      GioiTinh_KhachHang = gioiTinh_KhachHang;
   }

   // Getter and Setter for NgaySinh_KhachHang
   public NgayThangNam getNgaySinh_KhachHang() {
      return NgaySinh_KhachHang;
   }

   public void setNgaySinh_KhachHang(NgayThangNam ngaySinh_KhachHang) {
      NgaySinh_KhachHang = ngaySinh_KhachHang;
   }

   // Getter and Setter for Ten_KhachHang
   public String getTen_KhachHang() {
      return Ten_KhachHang;
   }

   public void setTen_KhachHang(String ten_KhachHang) {
      Ten_KhachHang = ten_KhachHang;
   }

   // Getter and Setter for Sdt_KhachHang
   public String getSdt_KhachHang() {
      return Sdt_KhachHang;
   }

   public void setSdt_KhachHang(String sdt_KhachHang) {
      Sdt_KhachHang = sdt_KhachHang;
   }

   // Getter and Setter for DiaChi_KhachHang
   public String getDiaChi_KhachHang() {
      return DiaChi_KhachHang;
   }

   public void setDiaChi_KhachHang(String diaChi_KhachHang) {
      DiaChi_KhachHang = diaChi_KhachHang;
   }

   // Getter and Setter for Username_KhachHang
   public String getUsername_KhachHang() {
      return Username_KhachHang;
   }

   public void setUsername_KhachHang(String username_KhachHang) {
      Username_KhachHang = username_KhachHang;
   }

   // Getter and Setter for Pass_KhachHang
   public String getPass_KhachHang() {
      return Pass_KhachHang;
   }

   public void setPass_KhachHang(String pass_KhachHang) {
      Pass_KhachHang = pass_KhachHang;
   }
   //get và set ngày sinh khách hàng
   public NgayThangNam getNS_KhachHang() {
      return NgaySinh_KhachHang;
   }

   public void setNS_KhachHang(NgayThangNam NS_KhachHang) {
      NgaySinh_KhachHang = NS_KhachHang;}

   public void Xuat()
   {
      System.out.println("Id khach hang: " + this.Id_KhachHang);
      System.out.println("Ten: " + this.Ten_KhachHang);
      System.out.println("So dien thoai: " + this.Sdt_KhachHang);
      System.out.println("Gioi tinh: " + this.GioiTinh_KhachHang);
      System.out.println("Dia chi: " + this.DiaChi_KhachHang);
      System.out.println("Ngay Sinh: " + this.NgaySinh_KhachHang);
   }

   @Override
   public String toString(){
      return Id_KhachHang + ", " + Ten_KhachHang + ", " + Sdt_KhachHang + ", " + GioiTinh_KhachHang + ", " +  DiaChi_KhachHang + ", " + NgaySinh_KhachHang  + ", " +  Username_KhachHang + ", " + Pass_KhachHang ;
   }
}