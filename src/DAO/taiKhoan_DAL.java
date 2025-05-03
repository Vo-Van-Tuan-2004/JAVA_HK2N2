package DAO;

import DTO.taiKhoan_DTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class taiKhoan_DAL {
    private Connection conn;

    public taiKhoan_DAL() throws Exception {
        String url = "jdbc:sqlserver://localhost:1433;databaseName=CuaHangBanXeMay;encrypt=false";
        String user = "sa";
        String password = "12345";
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conn = DriverManager.getConnection(url, user, password);
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

    public boolean tenTaiKhoanExists(String tenTaiKhoan) {
        String sql = "SELECT COUNT(*) FROM TaiKhoan WHERE ten_tai_khoan = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tenTaiKhoan);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi kiểm tra tên tài khoản: " + e.getMessage());
        }
        return false;
    }

    public boolean register(taiKhoan_DTO user) {
        String insertNhanVienSql = "INSERT INTO NhanVien (ma_nhan_vien, ten, chuc_vu, so_dien_thoai, muc_luong, gioi_tinh, dia_chi) VALUES (?, ?, ?, ?, ?, ?, ?)";
        String insertTaiKhoanSql = "INSERT INTO TaiKhoan (ma_nhan_vien, ten_tai_khoan, mat_khau) VALUES (?, ?, ?)";
        try {
            conn.setAutoCommit(false);
            // Generate unique ma_nhan_vien
            String maNhanVien = "NV" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            while (maNhanVienExists(maNhanVien)) {
                maNhanVien = "NV" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
            }
            // Insert into NhanVien
            try (PreparedStatement stmtNhanVien = conn.prepareStatement(insertNhanVienSql)) {
                stmtNhanVien.setString(1, maNhanVien);
                stmtNhanVien.setString(2, user.getTenTaiKhoan()); // Use username as default name
                stmtNhanVien.setString(3, "Nhân viên"); // Default role
                stmtNhanVien.setString(4, "");
                stmtNhanVien.setInt(5, 0);
                stmtNhanVien.setString(6, "");
                stmtNhanVien.setString(7, "");
                stmtNhanVien.executeUpdate();
            }
            // Insert into TaiKhoan
            try (PreparedStatement stmtTaiKhoan = conn.prepareStatement(insertTaiKhoanSql)) {
                stmtTaiKhoan.setString(1, maNhanVien);
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

    public taiKhoan_DTO login(String tenTaiKhoan, String matKhau) {
        String sql = "SELECT tk.ma_nhan_vien, tk.ten_tai_khoan, tk.mat_khau, nv.chuc_vu " +
                     "FROM TaiKhoan tk " +
                     "JOIN NhanVien nv ON tk.ma_nhan_vien = nv.ma_nhan_vien " +
                     "WHERE tk.ten_tai_khoan = ? AND tk.mat_khau = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tenTaiKhoan);
            stmt.setString(2, matKhau);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new taiKhoan_DTO(
                    rs.getString("ma_nhan_vien"),
                    rs.getString("ten_tai_khoan"),
                    rs.getString("mat_khau"),
                    rs.getString("chuc_vu")
                );
            }
        } catch (SQLException e) {
            System.out.println("Lỗi đăng nhập: " + e.getMessage());
        }
        return null;
    }

    public List<taiKhoan_DTO> getAllAccounts() {
        List<taiKhoan_DTO> accounts = new ArrayList<>();
        String sql = "SELECT tk.ma_nhan_vien, tk.ten_tai_khoan, tk.mat_khau, nv.chuc_vu " +
                     "FROM TaiKhoan tk " +
                     "JOIN NhanVien nv ON tk.ma_nhan_vien = nv.ma_nhan_vien";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                taiKhoan_DTO account = new taiKhoan_DTO(
                    rs.getString("ma_nhan_vien"),
                    rs.getString("ten_tai_khoan"),
                    rs.getString("mat_khau"),
                    rs.getString("chuc_vu")
                );
                accounts.add(account);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi lấy danh sách tài khoản: " + e.getMessage());
        }
        return accounts;
    }

    public taiKhoan_DTO getAccountByMaNhanVien(String maNhanVien) {
        String sql = "SELECT tk.ma_nhan_vien, tk.ten_tai_khoan, tk.mat_khau, nv.chuc_vu " +
                     "FROM TaiKhoan tk " +
                     "JOIN NhanVien nv ON tk.ma_nhan_vien = nv.ma_nhan_vien " +
                     "WHERE tk.ma_nhan_vien = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maNhanVien);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new taiKhoan_DTO(
                    rs.getString("ma_nhan_vien"),
                    rs.getString("ten_tai_khoan"),
                    rs.getString("mat_khau"),
                    rs.getString("chuc_vu")
                );
            }
        } catch (SQLException e) {
            System.out.println("Lỗi lấy tài khoản: " + e.getMessage());
        }
        return null;
    }

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

    public boolean deleteAccount(String maNhanVien) {
        String sql = "DELETE FROM TaiKhoan WHERE ma_nhan_vien = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, maNhanVien);
            int rowsDeleted = stmt.executeUpdate();
            return rowsDeleted > 0;
        } catch (SQLException e) {
            System.out.println("Lỗi xóa tài khoản: " + e.getMessage());
            return false;
        }
    }

    public List<taiKhoan_DTO> searchAccounts(String keyword) {
        List<taiKhoan_DTO> accounts = new ArrayList<>();
        String sql = "SELECT tk.ma_nhan_vien, tk.ten_tai_khoan, tk.mat_khau, nv.chuc_vu " +
                     "FROM TaiKhoan tk " +
                     "JOIN NhanVien nv ON tk.ma_nhan_vien = nv.ma_nhan_vien " +
                     "WHERE tk.ten_tai_khoan LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                taiKhoan_DTO account = new taiKhoan_DTO(
                    rs.getString("ma_nhan_vien"),
                    rs.getString("ten_tai_khoan"),
                    rs.getString("mat_khau"),
                    rs.getString("chuc_vu")
                );
                accounts.add(account);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi tìm kiếm tài khoản: " + e.getMessage());
        }
        return accounts;
    }
}