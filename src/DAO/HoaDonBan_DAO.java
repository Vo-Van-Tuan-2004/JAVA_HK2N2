package DAO;

import DTO.HoaDonBan_DTO;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class HoaDonBan_DAO {
    private Connection connection;

    public HoaDonBan_DAO(Connection connect) {
        this.connection = connect;
    }

    public HoaDonBan_DAO() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            connection = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=CuaHangBanXeMay;encrypt=true;trustServerCertificate=true",
                "sa", "12345"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean them(HoaDonBan_DTO hoaDonBan) {
        String query = "INSERT INTO HoaDonBan (ma_hoa_don_ban, ma_khach_hang, ma_nhan_vien, ngay_xuat, tong_tien, trang_thai) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, hoaDonBan.getMa_hoa_don_ban() );
            stmt.setString(2, hoaDonBan.getMa_khach_hang());
            stmt.setString(3, hoaDonBan.getMa_nhan_vien());
            stmt.setDate(4, hoaDonBan.getNgay_xuat() != null ? Date.valueOf(hoaDonBan.getNgay_xuat()) : null);
            stmt.setLong(5, hoaDonBan.getTong_tien());
            stmt.setString(6, hoaDonBan.getTrang_thai());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public HoaDonBan_DTO TimKiemTheoID(String maHoaDonBan) {
        String query = "SELECT * FROM HoaDonBan WHERE ma_hoa_don_ban = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maHoaDonBan);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                LocalDate ngayXuat = rs.getDate("ngay_xuat") != null ? rs.getDate("ngay_xuat").toLocalDate() : null;
                return new HoaDonBan_DTO(
                    rs.getString("ma_hoa_don_ban"),
                    rs.getString("ma_khach_hang"),
                    rs.getString("ma_nhan_vien"),
                    ngayXuat,
                    rs.getInt("tong_tien"),
                    rs.getString("trang_thai")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<HoaDonBan_DTO> LayDanhSachHoaDon() {
        ArrayList<HoaDonBan_DTO> hoaDonBanList = new ArrayList<>();
        String query = "SELECT * FROM HoaDonBan";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                LocalDate ngayXuat = rs.getDate("ngay_xuat") != null ? rs.getDate("ngay_xuat").toLocalDate() : null;
                HoaDonBan_DTO hoaDonBan = new HoaDonBan_DTO(
                    rs.getString("ma_hoa_don_ban"),
                    rs.getString("ma_khach_hang"),
                    rs.getString("ma_nhan_vien"),
                    ngayXuat,
                    rs.getInt("tong_tien"),
                    rs.getString("trang_thai")
                );
                hoaDonBanList.add(hoaDonBan);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hoaDonBanList;
    }

    public boolean CapNhatHoaDon(HoaDonBan_DTO hoaDonBan) {
        String query = "UPDATE HoaDonBan SET ma_khach_hang = ?, ma_nhan_vien = ?, ngay_xuat = ?, tong_tien = ?, trang_thai = ? WHERE ma_hoa_don_ban = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, hoaDonBan.getMa_khach_hang());
            stmt.setString(2, hoaDonBan.getMa_nhan_vien());
            stmt.setDate(3, hoaDonBan.getNgay_xuat() != null ? Date.valueOf(hoaDonBan.getNgay_xuat()) : null);
            stmt.setLong(4, hoaDonBan.getTong_tien());
            stmt.setString(5, hoaDonBan.getTrang_thai());
            stmt.setString(6, hoaDonBan.getMa_hoa_don_ban());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean XoaHoaDon(String maHoaDonBan) {
        String query = "DELETE FROM HoaDonBan WHERE ma_hoa_don_ban = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maHoaDonBan);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public String TaoMaMoi() {
        String query = "SELECT MAX(ma_hoa_don_ban) AS max_code FROM HoaDonBan";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                String maxCode = rs.getString("max_code");
                if (maxCode != null && maxCode.startsWith("HDB")) {
                    int currentNumber = Integer.parseInt(maxCode.substring(3));
                    int newNumber = currentNumber + 1;
                    return "HDB" + String.format("%02d", newNumber);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "HDB01";
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}