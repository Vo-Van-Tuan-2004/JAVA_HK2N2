package DTO;

public class PhuTung_DTO extends SanPham_DTO {
    String ma_phu_tung;
    String chat_lieu;
    public PhuTung_DTO(){}
    public PhuTung_DTO(String ma, String chl){
        this.ma_phu_tung=ma;
        this.chat_lieu=chl;
    }
    public String getma_phu_tung(){
        return this.ma_phu_tung;
    }
    public void setma_phu_tung(String x){
        this.ma_phu_tung=x;
    }
    public String getchat_lieu(){
        return this.chat_lieu;
    }
    public void setchat_lieu(String x){
        this.chat_lieu=x;
    }
}
