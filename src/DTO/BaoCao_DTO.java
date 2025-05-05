package DTO;

public class BaoCao_DTO {
    private int thang;
    private Long tongDoanhThu;

    public BaoCao_DTO(int thang, Long tongDoanhThu) {
        this.thang = thang;
        this.tongDoanhThu = tongDoanhThu;
    }

    public int getThang() {
        return thang;
    }

    public Long getTongDoanhThu() {
        return tongDoanhThu;
    }
}

