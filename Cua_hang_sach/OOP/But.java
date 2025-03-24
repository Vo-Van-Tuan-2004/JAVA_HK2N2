package OOP;

public class But extends SanPham{
    private String Mau;
    private String Loai_But;
    private String Hang;

    public But(){
        super();
        this.Mau="";
        this.Loai_But="";
        this.Hang="";
    }

    public But(String id, String ten, double gia, int soluong, String mau, String loai, String hang){
        super(id, ten, gia, soluong);
        this.Mau=mau;
        this.Loai_But=loai;
        this.Hang=hang;
    }

    // Getter và Setter cho Mau
    public String getMau() {
        return Mau;
    }

    public void setMau(String Mau) {
        this.Mau = Mau;
    }

    // Getter và Setter cho Loai_But
    public String getLoai_But() {
        return Loai_But;
    }

    public void setLoai_But(String Loai_But) {
        this.Loai_But = Loai_But;
    }

    // Getter và Setter cho Hang
    public String getHang() {
        return Hang;
    }

    public void setHang(String Hang) {
        this.Hang = Hang;
    }

    @Override
    public void hienThiThongTin() {
        System.out.println("But: " + super.toString() + ", " + this.toString());
    }
    
    public String hienThiThongTin1(){
        return super.getTen_SanPham() + ", " + super.getGia_SanPham() + ", " + this.toString();
    }

    @Override
    public String toString() {
        return "Mau = " + Mau + ", Loai_But = " + Loai_But + ", Hang = " + Hang;
    } 
}