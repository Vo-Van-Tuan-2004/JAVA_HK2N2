package BLL;

import DAO.ChiTietHoaDonBan_DAO;
import DAO.HoaDonBan_DAO;
import DTO.ChiTietHoaDonBan_DTO;
import DTO.HoaDonBan_DTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BanHang_BLL {
    private HoaDonBan_DAO hoaDonBanDAO;
    private ChiTietHoaDonBan_DAO chiTietHoaDonBanDAO;
    private Connection connection;

    public BanHang_BLL() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=CuaHangBanXeMay;encrypt=true;trustServerCertificate=true",
                "sa", "12345"
            );
            hoaDonBanDAO = new HoaDonBan_DAO(connection);
            chiTietHoaDonBanDAO = new ChiTietHoaDonBan_DAO(connection);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean TaoHoaDon(HoaDonBan_DTO hoaDon, List<ChiTietHoaDonBan_DTO> chiTietList) {
        try {
            connection.setAutoCommit(false);
            // Lưu hóa đơn
            boolean success = hoaDonBanDAO.them(hoaDon);
            if (!success) {
                connection.rollback();
                return false;
            }
            // Lưu chi tiết hóa đơn
            for (ChiTietHoaDonBan_DTO chiTiet : chiTietList) {
                chiTiet.setMa_hoa_don_ban(hoaDon.getMa_hoa_don_ban());
                success = chiTietHoaDonBanDAO.themChiTietHoaDonBan(chiTiet);
                if (!success) {
                    connection.rollback();
                    return false;
                }
                // Cập nhật số lượng tồn kho
                updateSoLuongTon(chiTiet.getMa_san_pham(), chiTiet.getSo_luong());
            }
            connection.commit();
            return true;
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public int TinhTongTien(List<ChiTietHoaDonBan_DTO> chiTietList) {
        int tongTien = 0;
        for (ChiTietHoaDonBan_DTO chiTiet : chiTietList) {
            tongTien += chiTiet.getSo_luong() * chiTiet.getDon_gia();
        }
        return tongTien;
    }

    public ArrayList<HoaDonBan_DTO> LayLichSuBanHang() {
        return hoaDonBanDAO.LayDanhSachHoaDon();
    }

    private void updateSoLuongTon(String maSanPham, int soLuong) throws SQLException {
        String sql = "UPDATE SanPham SET so_luong_ton = so_luong_ton - ? WHERE ma_spham = ?";
        try (var ps = connection.prepareStatement(sql)) {
            ps.setInt(1, soLuong);
            ps.setString(2, maSanPham);
            ps.executeUpdate();
        }
    }

    public void closeConnection() {
        hoaDonBanDAO.closeConnection();
    }
    public HoaDonBan_DAO getHoaDonBanDAO() {
        return hoaDonBanDAO;
    }
}