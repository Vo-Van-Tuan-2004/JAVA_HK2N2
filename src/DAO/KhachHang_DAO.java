package DAO;

import DTO.KhachHang_DTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KhachHang_DAO {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public KhachHang_DAO() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=dataBanXe;encrypt=true;trustServerCertificate=true",
                    "sa",
                    "123456789"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Thêm khách hàng mới
    public boolean ThemKhachHang(KhachHang_DTO kh) {
        String sql = "INSERT INTO khachhang(makhachhang, ten, sdt, email, diachi) VALUES (?, ?, ?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, kh.getMa_khach_hang());
            pstmt.setString(2, kh.getTen());
            pstmt.setString(3, kh.getSdt());
            pstmt.setString(4, kh.getEmail());
            pstmt.setString(5, kh.getDia_chi());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật thông tin khách hàng
    public boolean CapNhatKhachHang(KhachHang_DTO kh) {
        String sql = "UPDATE khachhang SET ten=?, sdt=?, email=?, diachi=? WHERE makhachhang=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, kh.getTen());
            pstmt.setString(2, kh.getSdt());
            pstmt.setString(3, kh.getEmail());
            pstmt.setString(4, kh.getDia_chi());
            pstmt.setString(5, kh.getMa_khach_hang());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa khách hàng
    public boolean XoaKhachHang(String maKH) {
        String sql = "DELETE FROM khachhang WHERE makhachhang=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maKH);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy thông tin khách hàng theo mã
    public KhachHang_DTO LayKhachHangTheoMa(String maKH) {
        String sql = "SELECT * FROM khachhang WHERE makhachhang=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maKH);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new KhachHang_DTO(
                    rs.getString("makhachhang"),
                    rs.getString("ten"),
                    rs.getString("sdt"),
                    rs.getString("email"),
                    rs.getString("diachi")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Lấy danh sách tất cả khách hàng
    public List<KhachHang_DTO> LayDanhSachKhachHang() {
        List<KhachHang_DTO> danhSach = new ArrayList<>();
        String sql = "SELECT * FROM khachhang";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                danhSach.add(new KhachHang_DTO(
                    rs.getString("makhachhang"),
                    rs.getString("ten"),
                    rs.getString("sdt"),
                    rs.getString("email"),
                    rs.getString("diachi")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSach;
    }

    // Tìm kiếm khách hàng theo tên
    public List<KhachHang_DTO> TimKiemKhachHang(String ten) {
        List<KhachHang_DTO> danhSach = new ArrayList<>();
        String sql = "SELECT * FROM khachhang WHERE ten LIKE ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + ten + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                danhSach.add(new KhachHang_DTO(
                    rs.getString("makhachhang"),
                    rs.getString("ten"),
                    rs.getString("sdt"),
                    rs.getString("email"),
                    rs.getString("diachi")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSach;
    }

    // Tìm kiếm khách hàng theo số điện thoại
    public KhachHang_DTO TimKiemKhachHangTheoSDT(String sdt) {
        String sql = "SELECT * FROM khachhang WHERE sdt=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sdt);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new KhachHang_DTO(
                    rs.getString("makhachhang"),
                    rs.getString("ten"),
                    rs.getString("sdt"),
                    rs.getString("email"),
                    rs.getString("diachi")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Kiểm tra khách hàng đã tồn tại chưa
    public boolean KiemTraKhachHangTonTai(String maKH) {
        String sql = "SELECT COUNT(*) FROM khachhang WHERE makhachhang=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maKH);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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