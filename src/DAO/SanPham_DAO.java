package DAO;

import DTO.SanPham_DTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SanPham_DAO {
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public SanPham_DAO() {
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

    // Thêm sản phẩm mới
    public boolean ThemSanPham(SanPham_DTO sp) {
        String sql = "INSERT INTO sanpham(masanpham, maloai, tensanpham, soluongton, giaban, gianhap, trangthai, trongluong, baohanh, thuonghieu) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sp.getMa_san_pham());
            pstmt.setString(2, sp.getMa_loai());
            pstmt.setString(3, sp.getTen_san_pham());
            pstmt.setInt(4, sp.getSo_luong_ton());
            pstmt.setInt(5, sp.getGia_ban());
            pstmt.setInt(6, sp.getGia_nhap());
            pstmt.setString(7, sp.getTrang_thai());
            pstmt.setString(9, sp.getBao_hanh());
            pstmt.setString(10, sp.getThuong_hieu());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Cập nhật thông tin sản phẩm
    public boolean CapNhatSanPham(SanPham_DTO sp) {
        String sql = "UPDATE sanpham SET maloai=?, tensanpham=?, soluongton=?, giaban=?, gianhap=?, trangthai=?, trongluong=?, baohanh=?, thuonghieu=? WHERE masanpham=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, sp.getMa_loai());
            pstmt.setString(2, sp.getTen_san_pham());
            pstmt.setInt(3, sp.getSo_luong_ton());
            pstmt.setInt(4, sp.getGia_ban());
            pstmt.setInt(5, sp.getGia_nhap());
            pstmt.setString(6, sp.getTrang_thai());
            pstmt.setString(8, sp.getBao_hanh());
            pstmt.setString(9, sp.getThuong_hieu());
            pstmt.setString(10, sp.getMa_san_pham());
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Xóa sản phẩm
    public boolean XoaSanPham(String maSanPham) {
        String sql = "DELETE FROM sanpham WHERE masanpham=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maSanPham);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Lấy thông tin sản phẩm theo mã
    public SanPham_DTO LaySanPhamTheoMa(String maSanPham) {
        String sql = "SELECT * FROM sanpham WHERE masanpham=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maSanPham);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return new SanPham_DTO(
                    rs.getString("masanpham"),
                    rs.getString("maloai"),
                    rs.getString("tensanpham"),
                    rs.getInt("soluongton"),
                    rs.getInt("giaban"),
                    rs.getInt("gianhap"),
                    rs.getString("trangthai"),
                    rs.getString("trongluong"),
                    rs.getString("baohanh"),
                    rs.getString("thuonghieu")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Lấy danh sách tất cả sản phẩm
    public List<SanPham_DTO> LayDanhSachSanPham() {
        List<SanPham_DTO> danhSach = new ArrayList<>();
        String sql = "SELECT * FROM sanpham";
        try {
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                danhSach.add(new SanPham_DTO(
                    rs.getString("masanpham"),
                    rs.getString("maloai"),
                    rs.getString("tensanpham"),
                    rs.getInt("soluongton"),
                    rs.getInt("giaban"),
                    rs.getInt("gianhap"),
                    rs.getString("trangthai"),
                    rs.getString("trongluong"),
                    rs.getString("baohanh"),
                    rs.getString("thuonghieu")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSach;
    }

    // Tìm kiếm sản phẩm theo tên
    public List<SanPham_DTO> TimKiemSanPham(String tenSanPham) {
        List<SanPham_DTO> danhSach = new ArrayList<>();
        String sql = "SELECT * FROM sanpham WHERE tensanpham LIKE ?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "%" + tenSanPham + "%");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                danhSach.add(new SanPham_DTO(
                    rs.getString("masanpham"),
                    rs.getString("maloai"),
                    rs.getString("tensanpham"),
                    rs.getInt("soluongton"),
                    rs.getInt("giaban"),
                    rs.getInt("gianhap"),
                    rs.getString("trangthai"),
                    rs.getString("trongluong"),
                    rs.getString("baohanh"),
                    rs.getString("thuonghieu")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSach;
    }

    // Lấy danh sách sản phẩm theo loại
    public List<SanPham_DTO> LaySanPhamTheoLoai(String maLoai) {
        List<SanPham_DTO> danhSach = new ArrayList<>();
        String sql = "SELECT * FROM sanpham WHERE maloai=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, maLoai);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                danhSach.add(new SanPham_DTO(
                    rs.getString("masanpham"),
                    rs.getString("maloai"),
                    rs.getString("tensanpham"),
                    rs.getInt("soluongton"),
                    rs.getInt("giaban"),
                    rs.getInt("gianhap"),
                    rs.getString("trangthai"),
                    rs.getString("trongluong"),
                    rs.getString("baohanh"),
                    rs.getString("thuonghieu")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSach;
    }

    // Cập nhật số lượng tồn
    public boolean CapNhatSoLuongTon(String maSanPham, int soLuongMoi) {
        String sql = "UPDATE sanpham SET soluongton=? WHERE masanpham=?";
        try {
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