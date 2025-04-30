package DAO;

import DTO.SanPhamDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=CuaHangBanXeMay;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "123456789";

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            throw new SQLException("SQL Server JDBC Driver not found", e);
        }
    }

    public List<SanPhamDTO> getAllSanPham() {
        List<SanPhamDTO> list = new ArrayList<>();
        String sql = "SELECT sp.*, lsp.ten_loai FROM SanPham sp JOIN LoaiSanPham lsp ON sp.ma_loai = lsp.ma_loai";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                SanPhamDTO sp = new SanPhamDTO();
                sp.setMaSanPham(rs.getString("ma_spham"));
                sp.setTenSanPham(rs.getString("ten_spham"));
                sp.setXuatXu(rs.getString("xuat_xu"));
                sp.setSoLuongTon(rs.getInt("so_luong_ton"));
                sp.setGiaBan(rs.getInt("gia_ban"));
                sp.setTrangThai(rs.getString("trang_thai"));
                sp.setMaLoai(rs.getString("ma_loai"));
                sp.setTenLoai(rs.getString("ten_loai"));
                list.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addSanPham(SanPhamDTO sp) {
        String sql = "INSERT INTO SanPham(ma_spham, ten_spham, xuat_xu, so_luong_ton, gia_ban, trang_thai, ma_loai) VALUES(?,?,?,?,?,?,?)";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, sp.getMaSanPham());
            ps.setString(2, sp.getTenSanPham());
            ps.setString(3, sp.getXuatXu());
            ps.setInt(4, sp.getSoLuongTon());
            ps.setInt(5, sp.getGiaBan());
            ps.setString(6, sp.getTrangThai());
            ps.setString(7, sp.getMaLoai());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSanPham(SanPhamDTO sp) {
        String sql = "UPDATE SanPham SET ten_spham=?, xuat_xu=?, so_luong_ton=?, gia_ban=?, trang_thai=?, ma_loai=? WHERE ma_spham=?";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, sp.getTenSanPham());
            ps.setString(2, sp.getXuatXu());
            ps.setInt(3, sp.getSoLuongTon());
            ps.setInt(4, sp.getGiaBan());
            ps.setString(5, sp.getTrangThai());
            ps.setString(6, sp.getMaLoai());
            ps.setString(7, sp.getMaSanPham());
            
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSanPham(String maSanPham) {
        String sql = "DELETE FROM SanPham WHERE ma_spham=?";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, maSanPham);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public SanPhamDTO getSanPhamByMa(String maSanPham) {
        String sql = "SELECT sp.*, lsp.ten_loai FROM SanPham sp JOIN LoaiSanPham lsp ON sp.ma_loai = lsp.ma_loai WHERE ma_spham=?";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, maSanPham);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    SanPhamDTO sp = new SanPhamDTO();
                    sp.setMaSanPham(rs.getString("ma_spham"));
                    sp.setTenSanPham(rs.getString("ten_spham"));
                    sp.setXuatXu(rs.getString("xuat_xu"));
                    sp.setSoLuongTon(rs.getInt("so_luong_ton"));
                    sp.setGiaBan(rs.getInt("gia_ban"));
                    sp.setTrangThai(rs.getString("trang_thai"));
                    sp.setMaLoai(rs.getString("ma_loai"));
                    sp.setTenLoai(rs.getString("ten_loai"));
                    return sp;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<SanPhamDTO> searchSanPham(String keyword) {
        List<SanPhamDTO> list = new ArrayList<>();
        String sql = "SELECT sp.*, lsp.ten_loai FROM SanPham sp JOIN LoaiSanPham lsp ON sp.ma_loai = lsp.ma_loai " +
                    "WHERE sp.ma_spham LIKE ? OR sp.ten_spham LIKE ? OR sp.xuat_xu LIKE ? OR lsp.ten_loai LIKE ?";
        
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            String searchPattern = "%" + keyword + "%";
            ps.setString(1, searchPattern);
            ps.setString(2, searchPattern);
            ps.setString(3, searchPattern);
            ps.setString(4, searchPattern);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    SanPhamDTO sp = new SanPhamDTO();
                    sp.setMaSanPham(rs.getString("ma_spham"));
                    sp.setTenSanPham(rs.getString("ten_spham"));
                    sp.setXuatXu(rs.getString("xuat_xu"));
                    sp.setSoLuongTon(rs.getInt("so_luong_ton"));
                    sp.setGiaBan(rs.getInt("gia_ban"));
                    sp.setTrangThai(rs.getString("trang_thai"));
                    sp.setMaLoai(rs.getString("ma_loai"));
                    sp.setTenLoai(rs.getString("ten_loai"));
                    list.add(sp);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
} 