package DTO;

public class DTOBAOHANH {
    private String ID_baohanh; 
    private String ID_sanpham;  
    private String ngaybatdau; 
    private String ngayketthuc;    

    public DTOBAOHANH(String ID_baohanh, String ID_sanpham, String ngaybatdau, String ngayketthuc) {
        this.ID_baohanh = ID_baohanh;
        this.ID_sanpham = ID_sanpham;
        this.ngaybatdau = ngaybatdau;
        this.ngayketthuc = ngayketthuc;
    }

    public String getWarrantyId() {
        return ID_baohanh;
    }

    public void setWarrantyId(String warrantyId) {
        this.ID_baohanh = ID_baohanh;
    }

   public String getProductId() {
        return ID_sanpham;
    }

    public void setProductId(String productId) {
        this.ID_sanpham = ID_sanpham;
    }

    public String getStartDate() {
        return ngaybatdau;
    }

    public void setStartDate(String startDate) {
        this.ngaybatdau = ngaybatdau;
    }

    public String getEndDate() {
        return ngayketthuc;
    }

    public void setEndDate(String endDate) {
        this.ngayketthuc = ngayketthuc;
    }
}
