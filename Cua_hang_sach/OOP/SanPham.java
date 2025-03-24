package OOP;

abstract class SanPham{
    private String ID_SanPham;
    private String Ten_SanPham;
    private double Gia_SanPham;
    private int SoLuong_SanPham;

    public SanPham(){
        this.ID_SanPham="";
        this.Ten_SanPham="";
        this.Gia_SanPham=0.00;
        this.SoLuong_SanPham=0;
    }

    public SanPham(String id, String ten, double gia, int soluong){
        this.ID_SanPham = id;
        this.Ten_SanPham = ten;
        this.Gia_SanPham = gia;
        this.SoLuong_SanPham = soluong;
    }

    // Getter và Setter cho ID_SanPham
    public String getID_SanPham() {
        return ID_SanPham;
    }

    public void setID_SanPham(String ID_SanPham) {
        this.ID_SanPham = ID_SanPham;
    }

    // Getter và Setter cho Ten_SanPham
    public String getTen_SanPham() {
        return Ten_SanPham;
    }

    public void setTen_SanPham(String Ten_SanPham) {
        this.Ten_SanPham = Ten_SanPham;
    }

    // Getter và Setter cho Gia_SanPham
    public double getGia_SanPham() {
        return Gia_SanPham;
    }

    public void setGia_SanPham(double Gia_SanPham) {
        this.Gia_SanPham = Gia_SanPham;
    }

    // Getter và Setter cho SoLuong_SanPham
    public int getSoLuong_SanPham() {
        return SoLuong_SanPham;
    }

    public void setSoLuong_SanPham(int SoLuong_SanPham) {
        this.SoLuong_SanPham = SoLuong_SanPham;
    }

    public abstract void hienThiThongTin();

    @Override
    public String toString() {
        return "ID_SanPham = " + ID_SanPham +", Ten_SanPham = " + Ten_SanPham + ", Gia_SanPham = " + Gia_SanPham
                + ", SoLuong_SanPham_Con = " + SoLuong_SanPham;
    }

    
    public abstract String hienThiThongTin1();
    

    
}