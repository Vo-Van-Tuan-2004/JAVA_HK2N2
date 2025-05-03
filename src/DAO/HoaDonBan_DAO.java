package DAO;

import DTO.HoaDonBan_DTO;
import java.sql.*;
import java.util.ArrayList;

public class HoaDonBan_DAO {
     private Connection connection;
     //lay connect
     public HoaDonBan_DAO(){
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                connection = DriverManager.getConnection(
                        "jdbc:sqlserver://localhost:1433;databaseName=CuaHangBanXeMay;encrypt=true;trustServerCertificate=true",
                        "sa",
                        "12345"
                );
            } catch (Exception e) {
                e.printStackTrace();
            }
     }

    // Create a new invoice record
    public boolean them(HoaDonBan_DTO hoaDonBan) {
        String query = "INSERT INTO hoa_don_ban (ma_hoa_don_ban, ma_khach_hang, ma_nhan_vien, ngay_xuat, tong_tien, trang_thai) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, hoaDonBan.getMa_hoa_don_ban());
            stmt.setString(2, hoaDonBan.getMa_khach_hang());
            stmt.setString(3, hoaDonBan.getMa_nhan_vien());
            stmt.setDate(4, hoaDonBan.getNgay_xuat());
            stmt.setInt(5, hoaDonBan.getTong_tien());
            stmt.setString(6, hoaDonBan.getTrang_thai());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Read an invoice record by ID
    public HoaDonBan_DTO TimKiemTheoID(String maHoaDonBan) {
        String query = "SELECT * FROM hoa_don_ban WHERE ma_hoa_don_ban = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maHoaDonBan);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new HoaDonBan_DTO(
                    rs.getString("ma_hoa_don_ban"),
                    rs.getString("ma_khach_hang"),
                    rs.getString("ma_nhan_vien"),
                    rs.getDate("ngay_xuat"),
                    rs.getInt("tong_tien"),
                    rs.getString("trang_thai")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Read all invoice records
    public ArrayList<HoaDonBan_DTO> LayDanhSachHoaDon() {
        ArrayList<HoaDonBan_DTO> hoaDonBanList = new ArrayList<>();
        String query = "SELECT * FROM hoa_don_ban";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                HoaDonBan_DTO hoaDonBan = new HoaDonBan_DTO(
                    rs.getString("ma_hoa_don_ban"),
                    rs.getString("ma_khach_hang"),
                    rs.getString("ma_nhan_vien"),
                    rs.getDate("ngay_xuat"),
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

    // Update an invoice record
    public boolean CapNhatHoaDon(HoaDonBan_DTO hoaDonBan) {
        String query = "UPDATE hoa_don_ban SET ma_khach_hang = ?, ma_nhan_vien = ?, ngay_xuat = ?, tong_tien = ?, trang_thai = ? WHERE ma_hoa_don_ban = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, hoaDonBan.getMa_khach_hang());
            stmt.setString(2, hoaDonBan.getMa_nhan_vien());
            stmt.setDate(3, hoaDonBan.getNgay_xuat());
            stmt.setInt(4, hoaDonBan.getTong_tien());
            stmt.setString(5, hoaDonBan.getTrang_thai());
            stmt.setString(6, hoaDonBan.getMa_hoa_don_ban());
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete an invoice record by ID
    public boolean XoaHoaDon(String maHoaDonBan) {
        String query = "DELETE FROM hoa_don_ban WHERE ma_hoa_don_ban = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, maHoaDonBan);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    //Tao ma hoa don moi
    public String TaoMaMoi() {
        String query = "SELECT MAX(ma_hoa_don_ban) AS max_code FROM HoaDonBan";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
                String maxCode = rs.getString("max_code");
                if (maxCode != null && maxCode.startsWith("HDB")) {
                    // Extract the numeric part and increment it
                    int currentNumber = Integer.parseInt(maxCode.substring(3));
                    int newNumber = currentNumber + 1;
                    return "HDB" + String.format("%d", newNumber);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // Default to HDB1 if no records exist
        return "HD1";
    }

    // Close the database connection
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
