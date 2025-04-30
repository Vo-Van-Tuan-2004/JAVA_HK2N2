package DAO;

import DTO.SanPham_DTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SanPham_DAO {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private static final String DATABASE = "CuaHangBanXeMay";
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=" + DATABASE + ";encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "123456789";

    public SanPham_DAO() {
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

    // Thêm sản phẩm mới
    public boolean ThemSanPham(SanPham_DTO sp) {
        String sql = "INSERT INTO SanPham(ma_spham, ten_spham, xuat_xu, so_luong_ton, gia_ban, trang_thai, ma_loai) VALUES(?,?,?,?,?,?,?)";
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            }
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sp.getMa_san_pham());
            ps.setString(2, sp.getTen_san_pham());
            ps.setString(3, sp.getXuat_xu());
            ps.setInt(4, sp.getSo_luong_ton());
            ps.setInt(5, sp.getGia_ban());
            ps.setString(6, sp.getTrang_thai());
            ps.setString(7, sp.getMa_loai());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm sản phẩm!");
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật thông tin sản phẩm
    public boolean CapNhatSanPham(SanPham_DTO sp) {
        String sql = "UPDATE SanPham SET ten_spham=?, xuat_xu=?, so_luong_ton=?, gia_ban=?, trang_thai=?, ma_loai=? WHERE ma_spham=?";
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            }
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, sp.getTen_san_pham());
            ps.setString(2, sp.getXuat_xu());
            ps.setInt(3, sp.getSo_luong_ton());
            ps.setInt(4, sp.getGia_ban());
            ps.setString(5, sp.getTrang_thai());
            ps.setString(6, sp.getMa_loai());
            ps.setString(7, sp.getMa_san_pham());
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật sản phẩm!");
            e.printStackTrace();
            return false;
        }
    }

    // Xóa sản phẩm
    public boolean XoaSanPham(String maSP) {
        String sql = "DELETE FROM SanPham WHERE ma_spham=?";
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            }
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maSP);
            
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa sản phẩm!");
            e.printStackTrace();
            return false;
        }
    }

    // Lấy thông tin sản phẩm theo mã
    public SanPham_DTO LaySanPhamTheoMa(String maSP) {
        String sql = "SELECT sp.*, lsp.ten_loai FROM SanPham sp JOIN LoaiSanPham lsp ON sp.ma_loai = lsp.ma_loai WHERE ma_spham=?";
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            }
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, maSP);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                SanPham_DTO sp = new SanPham_DTO();
                sp.setMa_san_pham(rs.getString("ma_spham"));
                sp.setTen_san_pham(rs.getString("ten_spham"));
                sp.setXuat_xu(rs.getString("xuat_xu"));
                sp.setSo_luong_ton(rs.getInt("so_luong_ton"));
                sp.setGia_ban(rs.getInt("gia_ban"));
                sp.setTrang_thai(rs.getString("trang_thai"));
                sp.setMa_loai(rs.getString("ma_loai"));
                return sp;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy thông tin sản phẩm!");
            e.printStackTrace();
        }
        return null;
    }

    // Lấy danh sách tất cả sản phẩm
    public List<SanPham_DTO> LayDanhSachSanPham() {
        List<SanPham_DTO> danhSach = new ArrayList<>();
        String sql = "SELECT sp.*, lsp.ten_loai FROM SanPham sp JOIN LoaiSanPham lsp ON sp.ma_loai = lsp.ma_loai";
        
        try {
            if (conn == null || conn.isClosed()) {
                // Thử kết nối lại nếu connection bị đóng
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            }
            
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                SanPham_DTO sp = new SanPham_DTO();
                sp.setMa_san_pham(rs.getString("ma_spham"));
                sp.setTen_san_pham(rs.getString("ten_spham"));
                sp.setXuat_xu(rs.getString("xuat_xu"));
                sp.setSo_luong_ton(rs.getInt("so_luong_ton"));
                sp.setGia_ban(rs.getInt("gia_ban"));
                sp.setTrang_thai(rs.getString("trang_thai"));
                sp.setMa_loai(rs.getString("ma_loai"));
                danhSach.add(sp);
            }
            
            rs.close();
            ps.close();
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách sản phẩm!");
            System.out.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
        
        return danhSach;
    }

    // Tìm kiếm sản phẩm theo tên hoặc mã
    public List<SanPham_DTO> TimKiemSanPham(String keyword) {
        List<SanPham_DTO> danhSach = new ArrayList<>();
        String sql = "SELECT * FROM SanPham WHERE ma_spham LIKE ? OR ten_spham LIKE ?";
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            }
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            rs = pstmt.executeQuery();
            
            while (rs.next()) {
                SanPham_DTO sp = new SanPham_DTO();
                sp.setMa_san_pham(rs.getString("ma_spham"));
                sp.setTen_san_pham(rs.getString("ten_spham"));
                sp.setXuat_xu(rs.getString("xuat_xu"));
                sp.setSo_luong_ton(rs.getInt("so_luong_ton"));
                sp.setGia_ban(rs.getInt("gia_ban"));
                sp.setTrang_thai(rs.getString("trang_thai"));
                sp.setMa_loai(rs.getString("ma_loai"));
                danhSach.add(sp);
                System.out.println("Tìm thấy sản phẩm: " + sp.getMa_san_pham() + " - " + sp.getTen_san_pham());
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm sản phẩm: " + e.getMessage());
            e.printStackTrace();
        }
        return danhSach;
    }

    // Lấy danh sách sản phẩm theo loại
    public List<SanPham_DTO> LaySanPhamTheoLoai(String maLoai) {
        List<SanPham_DTO> danhSach = new ArrayList<>();
        String sql = "SELECT * FROM SanPham WHERE ma_loai=?";
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            }
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maLoai);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                SanPham_DTO sp = new SanPham_DTO();
                sp.setMa_san_pham(rs.getString("ma_spham"));
                sp.setTen_san_pham(rs.getString("ten_spham"));
                sp.setXuat_xu(rs.getString("xuat_xu"));
                sp.setSo_luong_ton(rs.getInt("so_luong_ton"));
                sp.setGia_ban(rs.getInt("gia_ban"));
                sp.setTrang_thai(rs.getString("trang_thai"));
                sp.setMa_loai(rs.getString("ma_loai"));
                danhSach.add(sp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSach;
    }

    // Cập nhật số lượng tồn
    public boolean CapNhatSoLuongTon(String maSanPham, int soLuongMoi) {
        String sql = "UPDATE SanPham SET so_luong_ton=? WHERE ma_spham=?";
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            }
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, soLuongMoi);
            pstmt.setString(2, maSanPham);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Đóng kết nối
    public void DongKetNoi() {
        try {
            if (rs != null) rs.close();
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}