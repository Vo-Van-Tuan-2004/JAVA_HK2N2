package OOP;

public class Vo extends SanPham{
    private String Loai_Vo;
    private String NhaSanXuat;
    private String ChatLieu;

    public Vo(){
        super();
        this.Loai_Vo="";
        this.NhaSanXuat="";
        this.ChatLieu="";
    }

    public Vo(String id, String ten, double gia, int soluong, String loai, String nsx, String chatlieu){
        super(id, ten, gia, soluong);
        this.Loai_Vo=loai;
        this.NhaSanXuat=nsx;
        this.ChatLieu=chatlieu;
    }

    // Getter và Setter cho Loai_Vo
    public String getLoai_Vo() {
        return Loai_Vo;
    }

    public void setLoai_Vo(String Loai_Vo) {
        this.Loai_Vo = Loai_Vo;
    }

    // Getter và Setter cho NhaSanXuat
    public String getNhaSanXuat() {
        return NhaSanXuat;
    }

    public void setNhaSanXuat(String NhaSanXuat) {
        this.NhaSanXuat = NhaSanXuat;
    }

    // Getter và Setter cho ChatLieu
    public String getChatLieu() {
        return ChatLieu;
    }

    public void setChatLieu(String ChatLieu) {
        this.ChatLieu = ChatLieu;
    }

    @Override
    public void hienThiThongTin() {
        System.out.println("Vo: " + super.toString() + ", " + this.toString());
    }

    public String hienThiThongTin1(){
        return super.getTen_SanPham() + ", " + super.getGia_SanPham() + ", " + this.toString();
    }

    @Override
    public String toString() {
        return "Loai_Vo = " + Loai_Vo + ", NhaSanXuat = " + NhaSanXuat + ", ChatLieu = " + ChatLieu;
    }
    
    
}