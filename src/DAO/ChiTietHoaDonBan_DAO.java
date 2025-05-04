package DAO;

import DTO.ChiTietHoaDonBan_DTO;
import java.sql.*;
import java.util.ArrayList;

public class ChiTietHoaDonBan_DAO {
    private Connection con;

    public ChiTietHoaDonBan_DAO(Connection connect) {
        this.con = connect;
    }

    public ChiTietHoaDonBan_DAO() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=CuaHangBanXeMay;encrypt=true;trustServerCertificate=true",
                "sa", "12345"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean themChiTietHoaDonBan(ChiTietHoaDonBan_DTO x) {
        String sql = "INSERT INTO ChiTietHoaDonBan (ma_hoa_don_ban, ma_spham, so_luong, don_gia) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, x.getMa_hoa_don_ban());
            ps.setString(2, x.getMa_san_pham());
            ps.setInt(3, x.getSo_luong());
            ps.setInt(4, x.getDon_gia());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean suaChiTietHoaDonBan(ChiTietHoaDonBan_DTO x) {
        String sql = "UPDATE ChiTietHoaDonBan SET so_luong = ?, don_gia = ? WHERE ma_hoa_don_ban = ? AND ma_spham = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, x.getSo_luong());
            ps.setInt(2, x.getDon_gia());
            ps.setString(3, x.getMa_hoa_don_ban());
            ps.setString(4, x.getMa_san_pham());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean xoaChiTietHoaDonBan(String ma_hoa_don_ban, String ma_spham) {
        String sql = "DELETE FROM ChiTietHoaDonBan WHERE ma_hoa_don_ban = ? AND ma_spham = ?";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ma_hoa_don_ban);
            ps.setString(2, ma_spham);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<ChiTietHoaDonBan_DTO> layDanhSachChiTietHoaDonBan() {
        ArrayList<ChiTietHoaDonBan_DTO> ds = new ArrayList<>();
        String sql = "SELECT * FROM ChiTietHoaDonBan";
        try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                ChiTietHoaDonBan_DTO tmp = new ChiTietHoaDonBan_DTO(
                    rs.getString("ma_hoa_don_ban"),
                    rs.getString("ma_spham"),
                    rs.getInt("so_luong"),
                    rs.getInt("don_gia")
                );
                ds.add(tmp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }

    public ArrayList<ChiTietHoaDonBan_DTO> timTheoMa(String ma_hoa_don_ban) {
        String sql = "SELECT * FROM ChiTietHoaDonBan WHERE ma_hoa_don_ban = ?";
        ArrayList<ChiTietHoaDonBan_DTO> res = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ma_hoa_don_ban);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                res.add(new ChiTietHoaDonBan_DTO(
                    rs.getString("ma_hoa_don_ban"),
                    rs.getString("ma_spham"),
                    rs.getInt("so_luong"),
                    rs.getInt("don_gia")
                ));
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}