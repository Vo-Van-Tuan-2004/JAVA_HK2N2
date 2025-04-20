package DAO;

import DTO.NhanVien_DTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhanVien_DAO {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public NhanVien_DAO() {
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

    // Thêm nhân viên mới
    public boolean ThemNhanVien(NhanVien_DTO nv) {
        String sql = "INSERT INTO nhanvien(manhanvien, ten, chucvu, sodienthoai, username, password, mucluong, gioitinh, diachi) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nv.getMaNhanVien());
            pstmt.setString(2, nv.getTen());
            pstmt.setString(3, nv.getChucVu());
            pstmt.setString(4, nv.getSoDienThoai());
            pstmt.setString(5, nv.getUsername());
            pstmt.setString(6, nv.getPassword());
            pstmt.setString(7, nv.getMucLuong());
            pstmt.setString(8, nv.getGioiTinh());
            pstmt.setString(9, nv.getDiaChi());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật thông tin nhân viên
    public boolean CapNhatNhanVien(NhanVien_DTO nv) {
        String sql = "UPDATE nhanvien SET ten=?, chucvu=?, sodienthoai=?, username=?, password=?, mucluong=?, gioitinh=?, diachi=? WHERE manhanvien=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nv.getTen());
            pstmt.setString(2, nv.getChucVu());
            pstmt.setString(3, nv.getSoDienThoai());
            pstmt.setString(4, nv.getUsername());
            pstmt.setString(5, nv.getPassword());
            pstmt.setString(6, nv.getMucLuong());
            pstmt.setString(7, nv.getGioiTinh());
            pstmt.setString(8, nv.getDiaChi());
            pstmt.setString(9, nv.getMaNhanVien());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa nhân viên
    public boolean XoaNhanVien(String maNhanVien) {
        String sql = "DELETE FROM nhanvien WHERE manhanvien=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maNhanVien);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy thông tin một nhân viên
    public NhanVien_DTO LayThongTinNhanVien(String maNhanVien) {
        String sql = "SELECT * FROM nhanvien WHERE manhanvien=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maNhanVien);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new NhanVien_DTO(
                    rs.getString("manhanvien"),
                    rs.getString("ten"),
                    rs.getString("chucvu"),
                    rs.getString("sodienthoai"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("mucluong"),
                    rs.getString("gioitinh"),
                    rs.getString("diachi")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Lấy danh sách tất cả nhân viên
    public List<NhanVien_DTO> LayDanhSachNhanVien() {
        List<NhanVien_DTO> danhSach = new ArrayList<>();
        String sql = "SELECT * FROM nhanvien";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                danhSach.add(new NhanVien_DTO(
                    rs.getString("manhanvien"),
                    rs.getString("ten"),
                    rs.getString("chucvu"),
                    rs.getString("sodienthoai"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("mucluong"),
                    rs.getString("gioitinh"),
                    rs.getString("diachi")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSach;
    }

    // Xác thực đăng nhập
    public boolean XacThuc(String username, String password) {
        String sql = "SELECT * FROM nhanvien WHERE username=? AND password=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Tìm kiếm nhân viên theo tên
    public List<NhanVien_DTO> TimKiemNhanVien(String ten) {
        List<NhanVien_DTO> danhSach = new ArrayList<>();
        String sql = "SELECT * FROM nhanvien WHERE ten LIKE ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + ten + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                danhSach.add(new NhanVien_DTO(
                    rs.getString("manhanvien"),
                    rs.getString("ten"),
                    rs.getString("chucvu"),
                    rs.getString("sodienthoai"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("mucluong"),
                    rs.getString("gioitinh"),
                    rs.getString("diachi")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSach;
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
