package BLL;

import DAO.ChiTietPhieuNhap_DAO;
import DAO.PhieuNhap_DAO;
import DTO.ChiTietPhieuNhap_DTO;
import DTO.PhieuNhap_DTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class NhapHang_BLL {
    private PhieuNhap_DAO phieuNhapDAO;
    private ChiTietPhieuNhap_DAO chiTietPhieuNhapDAO;
    private Connection conn;

    public NhapHang_BLL(Connection conn) {
        this.conn = conn;
        this.phieuNhapDAO = new PhieuNhap_DAO(conn);
        this.chiTietPhieuNhapDAO = new ChiTietPhieuNhap_DAO(conn);
    }

    public NhapHang_BLL() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=CuaHangBanXeMay;encrypt=true;trustServerCertificate=true",
                "sa", "12345"
            );
            phieuNhapDAO = new PhieuNhap_DAO(conn);
            chiTietPhieuNhapDAO = new ChiTietPhieuNhap_DAO(conn);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void getConnection(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            conn = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=CuaHangBanXeMay;encrypt=true;trustServerCertificate=true",
                "sa", "12345"
            );
            phieuNhapDAO = new PhieuNhap_DAO(conn);
            chiTietPhieuNhapDAO = new ChiTietPhieuNhap_DAO(conn);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public boolean luuPhieuNhap(PhieuNhap_DTO phieuNhap, ArrayList<ChiTietPhieuNhap_DTO> dsChiTiet) {
        try {
            // Kiểm tra dữ liệu đầu vào
            if (phieuNhap.getMa_phieu_nhap() == null || phieuNhap.getMa_nha_cung_cap() == null) {
                System.err.println("Mã phiếu nhập hoặc mã nhà cung cấp không hợp lệ");
                return false;
            }
            for (ChiTietPhieuNhap_DTO chiTiet : dsChiTiet) {
                if (chiTiet.getMa_spham() == null || chiTiet.getSo_luong_nhap() <= 0) {
                    System.err.println("Mã sản phẩm hoặc số lượng nhập không hợp lệ: " + chiTiet);
                    return false;
                }
            }
    
            // Kiểm tra kết nối
            if (conn == null || conn.isClosed()) {
                getConnection();
            }
            conn.setAutoCommit(false); // Bắt đầu giao dịch
    
            // Lưu phiếu nhập
            boolean success = phieuNhapDAO.ThemPhieuNhap(phieuNhap);
            if (!success) {
                System.err.println("Lưu phiếu nhập thất bại: " + phieuNhap.getMa_phieu_nhap());
                conn.rollback();
                return false;
            }
    
            // Lưu chi tiết phiếu nhập và cập nhật số lượng tồn kho
            for (ChiTietPhieuNhap_DTO chiTiet : dsChiTiet) {
                success = chiTietPhieuNhapDAO.themChiTietPhieuNhap(chiTiet);
                if (!success) {
                    System.err.println("Lưu chi tiết phiếu nhập thất bại: " + chiTiet);
                    conn.rollback();
                    return false;
                }
                success = phieuNhapDAO.capNhatSoLuongTon(chiTiet.getMa_spham(), chiTiet.getSo_luong_nhap());
                if (!success) {
                    System.err.println("Cập nhật số lượng tồn thất bại: " + chiTiet.getMa_spham());
                    conn.rollback();
                    return false;
                }
            }
    
            conn.commit(); // Commit giao dịch
            System.out.println("Lưu phiếu nhập thành công: " + phieuNhap.getMa_phieu_nhap());
            return true;
        } catch (SQLException e) {
            System.err.println("Lỗi SQL: " + e.getMessage() + ", SQLState: " + e.getSQLState() + ", ErrorCode: " + e.getErrorCode());
            e.printStackTrace();
            try {
                if (conn != null) {
                    conn.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return false;
        } finally {
            try {
                if (conn != null) {
                    conn.setAutoCommit(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String taoMaPhieuNhapMoi() {
        return phieuNhapDAO.TaoMaNhapMoi();
    }
}