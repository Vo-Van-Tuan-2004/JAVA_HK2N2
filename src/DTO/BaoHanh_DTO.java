package DTO;

public class BaoHanh_DTO {
    private String ID_baohanh; 
    private String ID_sanpham;  
    private String ngaybatdau; 
    private String ngayketthuc;    

    public BaoHanh_DTO(String ID_baohanh, String ID_sanpham, String ngaybatdau, String ngayketthuc) {
        this.ID_baohanh = ID_baohanh;
        this.ID_sanpham = ID_sanpham;
        this.ngaybatdau = ngaybatdau;
        this.ngayketthuc = ngayketthuc;
    }

    public String getWarrantyId() {
        return ID_baohanh;
    }

    public void setWarrantyId(String ID_baohanh) {
        this.ID_baohanh = ID_baohanh;
    }

   public String getProductId() {
        return ID_sanpham;
    }

    public void setProductId(String ID_sanpham) {
        this.ID_sanpham = ID_sanpham;
    }

    public String getStartDate() {
        return ngaybatdau;
    }

    public void setStartDate(String ngaybatdau) {
        this.ngaybatdau = ngaybatdau;
    }

    public String getEndDate() {
        return ngayketthuc;
    }

    public void setEndDate(String ngayketthuc) {
        this.ngayketthuc = ngayketthuc;
    }
}
