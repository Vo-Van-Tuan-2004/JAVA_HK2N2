package DAO;

import DTO.PhieuNhap_DTO;
import java.sql.*;
import java.util.ArrayList;

public class PhieuNhap_DAO {
    private Connection conn;

    public PhieuNhap_DAO(Connection connect) {
        this.conn = connect;
    }

    public PhieuNhap_DAO() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=CuaHangBanXeMay;encrypt=true;trustServerCertificate=true",
                    "sa",
                    "12345"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean ThemPhieuNhap(PhieuNhap_DTO sp) {
        String sql = "INSERT INTO PhieuNhap(ma_phieu_nhap, ma_nha_cung_cap, ngay_nhap, tong_tien) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, sp.getMa_phieu_nhap());
            pstmt.setString(2, sp.getMa_nha_cung_cap());
            pstmt.setDate(3, sp.getNgay_nhap());
            pstmt.setInt(4, sp.getTong_tien());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<PhieuNhap_DTO> LayDanhSachPhieuNhap() {
        ArrayList<PhieuNhap_DTO> danhSach = new ArrayList<>();
        String sql = "SELECT * FROM PhieuNhap";
        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                danhSach.add(new PhieuNhap_DTO(
                        rs.getString("ma_phieu_nhap"),
                        rs.getString("ma_nha_cung_cap"),
                        rs.getDate("ngay_nhap"),
                        rs.getInt("tong_tien")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSach;
    }

    public String TaoMaNhapMoi() {
        String sql = "SELECT TOP 1 ma_phieu_nhap FROM PhieuNhap ORDER BY ma_phieu_nhap DESC";
        try (PreparedStatement pstmt = conn.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                String ma_cuoi = rs.getString("ma_phieu_nhap");
                if (ma_cuoi.startsWith("PN") && ma_cuoi.length() >= 4) {
                    int so = Integer.parseInt(ma_cuoi.substring(2));
                    so++;
                    return String.format("PN%02d", so);
                } else {
                    throw new SQLException("Mã phiếu nhập không đúng định dạng: " + ma_cuoi);
                }
            }
        } catch (SQLException | NumberFormatException e) {
            System.err.println("Lỗi khi tạo mã phiếu nhập mới: " + e.getMessage());
            e.printStackTrace();
        }
        return "PN01"; // Chỉ trả về nếu bảng trống
    }

    public boolean capNhatSoLuongTon(String ma_spham, int so_luong_nhap) {
        String sql = "UPDATE SanPham SET so_luong_ton = so_luong_ton + ? WHERE ma_spham = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, so_luong_nhap);
            pstmt.setString(2, ma_spham);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}