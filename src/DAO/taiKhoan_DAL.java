package DAO;

import DTO.taiKhoan_DTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class taiKhoan_DAL {

    public static void updatePassword(String taiKhoan, String matKhauMoi) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static void delete(String taiKhoan) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    private Connection conn;

    public taiKhoan_DAL() throws Exception {
        String url = "jdbc:mysql://localhost:3306/CuaHangBanXeMay?useSSL=false&serverTimezone=UTC";
        String user = "root";
        String password = "";
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection(url,user,password);
    }
    // public taiKhoan_DAL() throws Exception {
    //     String url = "jdbc:sqlserver://localhost:1433;databaseName=CuaHangBanXeMay;encrypt=false";
    //     String user = "sa";
    //     String password = "12345";
    //     Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
    //     conn = DriverManager.getConnection(url, user, password);
    // }

    public boolean maTaiKhoanExists(String maTaiKhoan) {
        String sql = "SELECT COUNT(*) FROM TaiKhoan WHERE ma_nhan_vien = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maTaiKhoan);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kiểm tra mã tài khoản: " + e.getMessage());
        }
        return false;
    }


    public boolean maNhanVienExists(String maNhanVien) {
        String sql = "SELECT COUNT(*) FROM NhanVien WHERE ma_nhan_vien = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maNhanVien);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kiểm tra mã nhân viên: " + e.getMessage());
        }
        return false;
    }

    public boolean register(taiKhoan_DTO user) {
        String insertTaiKhoanSql = "INSERT INTO TaiKhoan (ma_nhan_vien, ten_tai_khoan, mat_khau) VALUES (?, ?, ?)";
        try {
            conn.setAutoCommit(false);

            try (PreparedStatement stmtTaiKhoan = conn.prepareStatement(insertTaiKhoanSql)) {
                stmtTaiKhoan.setString(1, user.getMaTaiKhoan());
                stmtTaiKhoan.setString(2, user.getTenTaiKhoan());
                stmtTaiKhoan.setString(3, user.getMatKhau());
                stmtTaiKhoan.executeUpdate();
            }

            conn.commit();
            conn.setAutoCommit(true);
            return true;
        } catch (SQLException e) {
            try {
                conn.rollback();
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
                System.out.println("Lỗi rollback: " + ex.getMessage());
            }
            System.out.println("Đăng ký thất bại: " + e.getMessage());
            return false;
        }
    }

    public taiKhoan_DTO login(String tenTaiKhoan, String matKhau){
        String sql  = "SELECT * FROM TaiKhoan WHERE ten_tai_khoan = ? AND mat_khau = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)){
            stmt.setString(1, tenTaiKhoan);
            stmt.setString(2, matKhau);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                return new taiKhoan_DTO(rs.getString("ma_nhan_vien"), tenTaiKhoan, matKhau);
            }
        } catch (SQLException e){
            System.out.println("Lỗi đăng nhập: " + e.getMessage());
        }
        return null;
    }

    // New method to get all accounts
    public List<taiKhoan_DTO> getAllAccounts() {
        List<taiKhoan_DTO> accounts = new ArrayList<>();
        String sql = "SELECT * FROM TaiKhoan";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                taiKhoan_DTO account = new taiKhoan_DTO(
                    rs.getString("ma_nhan_vien"),
                    rs.getString("ten_tai_khoan"),
                    rs.getString("mat_khau")
                );
                accounts.add(account);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi lấy danh sách tài khoản: " + e.getMessage());
        }
        return accounts;
    }

    // New method to update an account
    public boolean updateAccount(taiKhoan_DTO account) {
        String sql = "UPDATE TaiKhoan SET ten_tai_khoan = ?, mat_khau = ? WHERE ma_nhan_vien = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, account.getTenTaiKhoan());
            stmt.setString(2, account.getMatKhau());
            stmt.setString(3, account.getMaTaiKhoan());
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi cập nhật tài khoản: " + e.getMessage());
            return false;
        }
    }

    // New method to delete an account
    public boolean deleteAccount(String maTaiKhoan) {
        String sql = "DELETE FROM TaiKhoan WHERE ma_nhan_vien = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maTaiKhoan);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi xóa tài khoản: " + e.getMessage());
            return false;
        }
    }

    // New method to search accounts by keyword
    public List<taiKhoan_DTO> searchAccounts(String keyword) {
        List<taiKhoan_DTO> accounts = new ArrayList<>();
        String sql = "SELECT * FROM TaiKhoan WHERE ten_tai_khoan LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                taiKhoan_DTO account = new taiKhoan_DTO(
                    rs.getString("ma_nhan_vien"),
                    rs.getString("ten_tai_khoan"),
                    rs.getString("mat_khau")
                );
                accounts.add(account);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi tìm kiếm tài khoản: " + e.getMessage());
        }
        return accounts;
    }
}
