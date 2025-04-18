package DAO;

import DTO.ChiTietPhieuNhap_DTO;
import java.sql.*;
import java.util.ArrayList;

public class ChiTietPhieuNhap_DAO{
    private Connection con;
    //lay connect
    public ChiTietPhieuNhap_DAO(Connection connect){
        this.con = connect;
    }
    //them 
    public boolean themChiTietPhieuNhap(ChiTietPhieuNhap_DTO x){
        String sql = "INSET INTO ChiTietHoaDonBan (ma, ma_phieu_nhap, ma_san_pham, so_luong_nhap, thanh_tien) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, x.getMa());
            ps.setString(2, x.getMa_phieu_nhap());
            ps.setString(3, x.getMa_san_pham());
            ps.setInt(4, x.getSo_luong_nhap());
            ps.setInt(5, x.getThanh_tien());
            return ps.executeUpdate() > 0;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    //sua
    public boolean suaChiTietPhieuNhap(ChiTietPhieuNhap_DTO x){
        String sql = "UPDATE ChiTietHoaDonBan SET ma_phieu_nhap = ?, ma_san_pham = ?, so_luong_nhap = ?, thanh_tien = ? WHERE ma = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)){
            ps.setString(1, x.getMa_phieu_nhap());
            ps.setString(2, x.getMa_san_pham());
            ps.setInt(3, x.getSo_luong_nhap());
            ps.setInt(4, x.getThanh_tien());
            ps.setString(5, x.getMa());
            return ps.executeUpdate() > 0;
        } catch (SQLException e){
            e.printStackTrace();
            return false;
        }
    }
    //xoa
    public boolean xoaChiTietPhieuNhap(String ma_x){
        String sql = "DELETE FROM ChiTietPhieuNhap WHERE ma = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ma_x);
            return ps.executeUpdate() > 0 ;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    //lay danh sach
    public ArrayList<ChiTietPhieuNhap_DTO> layDanhSachChiTietPhieuNhap() {
        ArrayList<ChiTietPhieuNhap_DTO> ds = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietPhieuNhap";
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                ChiTietPhieuNhap_DTO tmp = new ChiTietPhieuNhap_DTO(
                        rs.getString("ma"),
                        rs.getString("ma_phieu_nhap"),
                        rs.getString("ma_san_pham"),
                        rs.getInt("so_luong_nhap"),
                        rs.getInt("thanh_tien")
                );
                ds.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }
    //tim theo ma 
    public ChiTietPhieuNhap_DTO timTheoMa(String ma_x) {
        String sql = "SELECT * FROM ChiTietPhieuNhap WHERE ma = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ma_x);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new ChiTietPhieuNhap_DTO(
                        rs.getString("ma"),
                        rs.getString("ma_phieu_nhap"),
                        rs.getString("ma_san_pham"),
                        rs.getInt("so_luong_nhap"),
                        rs.getInt("thanh_tien")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}