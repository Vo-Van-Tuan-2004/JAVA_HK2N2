package DAO;

import DTO.NhanVien_DTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhanVien_DAO {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private static final String DATABASE = "CuaHangBanXeMay";
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=" + DATABASE + ";encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "123456789";

    public NhanVien_DAO() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Kết nối database thành công!");
        } catch (ClassNotFoundException e) {
            System.out.println("Không tìm thấy driver SQL Server JDBC!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Kết nối database thất bại!");
            System.out.println("URL: " + URL);
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<NhanVien_DTO> LayDanhSachNhanVien() {
        List<NhanVien_DTO> danhSach = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                NhanVien_DTO nv = new NhanVien_DTO();
                nv.setMa_nhan_vien(rs.getString("ma_nhan_vien"));
                nv.setTen_nhan_vien(rs.getString("ten"));
                nv.setSo_dien_thoai(rs.getString("so_dien_thoai"));
                nv.setDia_chi(rs.getString("dia_chi"));
                nv.setChuc_vu(rs.getString("chuc_vu"));
                nv.setLuong(rs.getDouble("muc_luong"));
                danhSach.add(nv);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return danhSach;
    }

    public NhanVien_DTO LayNhanVienTheoMa(String maNV) {
        String sql = "SELECT * FROM NhanVien WHERE ma_nhan_vien = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maNV);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    NhanVien_DTO nv = new NhanVien_DTO();
                    nv.setMa_nhan_vien(rs.getString("ma_nhan_vien"));
                    nv.setTen_nhan_vien(rs.getString("ten"));
                    nv.setSo_dien_thoai(rs.getString("so_dien_thoai"));
                    nv.setDia_chi(rs.getString("dia_chi"));
                    nv.setChuc_vu(rs.getString("chuc_vu"));
                    nv.setLuong(rs.getDouble("muc_luong"));
                    return nv;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    public boolean ThemNhanVien(NhanVien_DTO nv) {
        String sql = "INSERT INTO NhanVien (ma_nhan_vien, ten, so_dien_thoai, dia_chi, chuc_vu, muc_luong) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nv.getMa_nhan_vien());
            pstmt.setString(2, nv.getTen_nhan_vien());
            pstmt.setString(3, nv.getSo_dien_thoai());
            pstmt.setString(4, nv.getDia_chi());
            pstmt.setString(5, nv.getChuc_vu());
            pstmt.setDouble(6, nv.getLuong());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean XoaNhanVien(String maNV) {
        String sql = "DELETE FROM NhanVien WHERE ma_nhan_vien = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maNV);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<NhanVien_DTO> TimKiemNhanVien(String tuKhoa) {
        List<NhanVien_DTO> ketQua = new ArrayList<>();
        String sql = "SELECT * FROM NhanVien WHERE ma_nhan_vien LIKE ? OR ten LIKE ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String searchPattern = "%" + tuKhoa + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    NhanVien_DTO nv = new NhanVien_DTO();
                    nv.setMa_nhan_vien(rs.getString("ma_nhan_vien"));
                    nv.setTen_nhan_vien(rs.getString("ten"));
                    nv.setSo_dien_thoai(rs.getString("so_dien_thoai"));
                    nv.setDia_chi(rs.getString("dia_chi"));
                    nv.setChuc_vu(rs.getString("chuc_vu"));
                    nv.setLuong(rs.getDouble("muc_luong"));
                    ketQua.add(nv);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return ketQua;
    }

    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
