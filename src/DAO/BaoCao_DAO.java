package DAO;

import DTO.BaoCao_DTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BaoCao_DAO {
    private static final String DATABASE = "CuaHangBanXeMay";
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=" + DATABASE + ";encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "12345";

    private Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<BaoCao_DTO> getDoanhThuTheoThang() {
        List<BaoCao_DTO> list = new ArrayList<>();
        String sql = "SELECT MONTH(ngay_xuat) AS thang, SUM(tong_tien) AS doanh_thu " +
                     "FROM HoaDonBan GROUP BY MONTH(ngay_xuat) ORDER BY thang ASC, doanh_thu DESC";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (conn == null) {
                throw new SQLException("Failed to establish database connection");
            }
            while (rs.next()) {
                int thang = rs.getInt("thang");
                long doanhThu = rs.getLong("doanh_thu");
                list.add(new BaoCao_DTO(thang, doanhThu));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}