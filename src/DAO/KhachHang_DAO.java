package DAO;

import DTO.KhachHang_DTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhachHang_DAO {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;
    private static final String DATABASE = "CuaHangBanXeMay";
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=" + DATABASE + ";encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "123456789";

    public KhachHang_DAO(Connection connect){
        this.conn = connect;
    }
    public KhachHang_DAO() {
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

    public List<KhachHang_DTO> LayDanhSachKhachHang() {
        List<KhachHang_DTO> danhSach = new ArrayList<>();
        String sql = "SELECT * FROM KhachHang";
        
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                KhachHang_DTO kh = new KhachHang_DTO();
                kh.setMa_khach_hang(rs.getString("ma_khach_hang"));
                kh.setTen_khach_hang(rs.getString("ten"));
                kh.setSo_dien_thoai(rs.getString("sdt"));
                kh.setEmail(rs.getString("email"));
                kh.setDia_chi(rs.getString("dia_chi"));
                danhSach.add(kh);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy danh sách khách hàng: " + e.getMessage());
            e.printStackTrace();
        }
        
        return danhSach;
    }

    public KhachHang_DTO LayKhachHangTheoMa(String maKH) {
        String sql = "SELECT * FROM KhachHang WHERE ma_khach_hang = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maKH);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    KhachHang_DTO kh = new KhachHang_DTO();
                    kh.setMa_khach_hang(rs.getString("ma_khach_hang"));
                    kh.setTen_khach_hang(rs.getString("ten"));
                    kh.setSo_dien_thoai(rs.getString("sdt"));
                    kh.setEmail(rs.getString("email"));
                    kh.setDia_chi(rs.getString("dia_chi"));
                    return kh;
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi lấy thông tin khách hàng theo mã: " + e.getMessage());
            e.printStackTrace();
        }
        
        return null;
    }

    public boolean ThemKhachHang(KhachHang_DTO kh) {
        String sql = "INSERT INTO KhachHang (ma_khach_hang, ten, sdt, email, dia_chi) VALUES (?, ?, ?, ?, ?)";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, kh.getMa_khach_hang());
            pstmt.setString(2, kh.getTen_khach_hang());
            pstmt.setString(3, kh.getSo_dien_thoai());
            pstmt.setString(4, kh.getEmail());
            pstmt.setString(5, kh.getDia_chi());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi thêm khách hàng: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean XoaKhachHang(String maKH) {
        String sql = "DELETE FROM KhachHang WHERE ma_khach_hang = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maKH);
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa khách hàng: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public boolean CapNhatKhachHang(KhachHang_DTO kh) {
        String sql = "UPDATE KhachHang SET ten = ?, sdt = ?, email = ?, dia_chi = ? WHERE ma_khach_hang = ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, kh.getTen_khach_hang());
            pstmt.setString(2, kh.getSo_dien_thoai());
            pstmt.setString(3, kh.getEmail());
            pstmt.setString(4, kh.getDia_chi());
            pstmt.setString(5, kh.getMa_khach_hang());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật khách hàng: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public List<KhachHang_DTO> TimKiemKhachHang(String tuKhoa) {
        List<KhachHang_DTO> ketQua = new ArrayList<>();
        String sql = "SELECT * FROM KhachHang WHERE ma_khach_hang LIKE ? OR ten LIKE ? OR sdt LIKE ?";
        
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            String searchPattern = "%" + tuKhoa + "%";
            pstmt.setString(1, searchPattern);
            pstmt.setString(2, searchPattern);
            pstmt.setString(3, searchPattern);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    KhachHang_DTO kh = new KhachHang_DTO();
                    kh.setMa_khach_hang(rs.getString("ma_khach_hang"));
                    kh.setTen_khach_hang(rs.getString("ten"));
                    kh.setSo_dien_thoai(rs.getString("sdt"));
                    kh.setEmail(rs.getString("email"));
                    kh.setDia_chi(rs.getString("dia_chi"));
                    ketQua.add(kh);
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi tìm kiếm khách hàng: " + e.getMessage());
            e.printStackTrace();
        }
        
        return ketQua;
    }

    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("Đã đóng kết nối database!");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi đóng kết nối: " + e.getMessage());
            e.printStackTrace();
        }
    }
}