package DAO;

import DTO.KhachHang_DTO;
import java.sql.*;
import java.util.ArrayList;

public class KhachHang_DAO {
    private Connection conn;

    // Constructor để thiết lập kết nối
    public KhachHang_DAO(Connection connection) {
        this.conn = connection;
    }

    // Thêm khách hàng
    public boolean themKhachHang(KhachHang_DTO kh) {
        String sql = "INSERT INTO KhachHang (ma_khach_hang, ten, sdt, email, dia_chi) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kh.getMa_khach_hang());
            ps.setString(2, kh.getTen());
            ps.setString(3, kh.getSdt());
            ps.setString(4, kh.getEmail());
            ps.setString(5, kh.getDia_chi());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // hoặc ghi log
            return false;
        }
    }

    // Sửa thông tin khách hàng
    public boolean suaKhachHang(KhachHang_DTO kh) {
        String sql = "UPDATE KhachHang SET ten = ?, sdt = ?, email = ?, dia_chi = ? WHERE ma_khach_hang = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, kh.getTen());
            ps.setString(2, kh.getSdt());
            ps.setString(3, kh.getEmail());
            ps.setString(4, kh.getDia_chi());
            ps.setString(5, kh.getMa_khach_hang());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa khách hàng
    public boolean xoaKhachHang(String maKH) {
        String sql = "DELETE FROM KhachHang WHERE ma_khach_hang = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maKH);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy danh sách tất cả khách hàng
    public ArrayList<KhachHang_DTO> layDanhSachKhachHang() {
        ArrayList<KhachHang_DTO> ds = new ArrayList<>();
        String sql = "SELECT * FROM KhachHang";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                KhachHang_DTO kh = new KhachHang_DTO(
                        rs.getString("ma_khach_hang"),
                        rs.getString("ten"),
                        rs.getString("sdt"),
                        rs.getString("email"),
                        rs.getString("dia_chi")
                );
                ds.add(kh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }

    // Tìm khách hàng theo mã
    public KhachHang_DTO timKhachHangTheoMa(String maKH) {
        String sql = "SELECT * FROM KhachHang WHERE ma_khach_hang = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maKH);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new KhachHang_DTO(
                        rs.getString("ma_khach_hang"),
                        rs.getString("ten"),
                        rs.getString("sdt"),
                        rs.getString("email"),
                        rs.getString("dia_chi")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
