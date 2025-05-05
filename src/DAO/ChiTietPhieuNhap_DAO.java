package DAO;

import DTO.ChiTietPhieuNhap_DTO;
import java.sql.*;
import java.util.ArrayList;

public class ChiTietPhieuNhap_DAO {
    private Connection con;

    public ChiTietPhieuNhap_DAO(Connection connect) {
        this.con = connect;
    }

    public ChiTietPhieuNhap_DAO() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CuaHangBanXeMay;encrypt=true;trustServerCertificate=true",
                    "sa",
                    "12345"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean themChiTietPhieuNhap(ChiTietPhieuNhap_DTO x) {
        String sql = "INSERT INTO ChiTietPhieuNhap (ma_phieu_nhap, ma_spham, gia_nhap, so_luong_nhap, thanh_tien) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, x.getMa_phieu_nhap());
            ps.setString(2, x.getMa_spham());
            ps.setInt(3, x.getGia_nhap());
            ps.setInt(4, x.getSo_luong_nhap());
            ps.setInt(5, x.getThanh_tien());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<ChiTietPhieuNhap_DTO> layDanhSachChiTietPhieuNhap() {
        ArrayList<ChiTietPhieuNhap_DTO> ds = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietPhieuNhap";
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                ChiTietPhieuNhap_DTO tmp = new ChiTietPhieuNhap_DTO(
                        rs.getString("ma_phieu_nhap"),
                        rs.getString("ma_spham"),
                        rs.getInt("gia_nhap"),
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

    public ArrayList<ChiTietPhieuNhap_DTO> timTheoMa(String ma_phieu_nhap) {
        ArrayList<ChiTietPhieuNhap_DTO> res = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietPhieuNhap WHERE ma_phieu_nhap = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ma_phieu_nhap);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    res.add(new ChiTietPhieuNhap_DTO(
                            rs.getString("ma_phieu_nhap"),
                            rs.getString("ma_spham"),
                            rs.getInt("gia_nhap"),
                            rs.getInt("so_luong_nhap"),
                            rs.getInt("thanh_tien")
                    ));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return res;
    }
}