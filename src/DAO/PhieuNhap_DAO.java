package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.PhieuNhap_DTO;
import DTO.SanPham_DTO;

public class PhieuNhap_DAO {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public PhieuNhap_DAO() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CuaHangBanXeMay;encrypt=true;trustServerCertificate=true",
                    "sa",
                    "12345"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//Them phieu nhap moi 
    public boolean ThemPhieuNhap(PhieuNhap_DTO sp) {
        String sql = "INSERT INTO sanpham(ma_phieu_nhap, ma_nha_cung_cap, ngay_nhap, tong_tien) VALUES (?, ?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sp.getMa_phieu_nhap());
            pstmt.setString(2, sp.getMa_nha_cung_cap());
            pstmt.setDate(3, sp.getNgay_nhap());
            pstmt.setInt(4, sp.getTong_tien());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
//xoa phieu nhap 
//lay danh sach tat ca phieu nhap
    public ArrayList<PhieuNhap_DTO> LayDanhSachPhieuNhap() {
            ArrayList<PhieuNhap_DTO> danhSach = new ArrayList<>();
            String sql = "SELECT * FROM PhieuNhap";
            try {
                this.pstmt = this.conn.prepareStatement(sql);
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    danhSach.add(new PhieuNhap_DTO(
                        rs.getString("ma_phieu_nhap"),
                        rs.getString("ma_nha_cung_cap"),
                        rs.getDate("ngay_nhap"),
                        rs.getInt("tong_tien")
                    ));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return danhSach;
        }

 //tao ma san pham moi
    public String TaoMaMoi(){
        String sql = "SELECT TOP 1 ma_phieu_nhap FROM PhieuNhap ORDER BY ma_phieu_nhap DESC";
        String ma_moi = null;
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()){
                String ma_cuoi = rs.getString("ma_phieu_nhap");
                int so =Integer.parseInt(ma_cuoi.substring(2));
                so++;
                ma_moi = String.format("SP%03d", so);
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return ma_moi;
    }
}
