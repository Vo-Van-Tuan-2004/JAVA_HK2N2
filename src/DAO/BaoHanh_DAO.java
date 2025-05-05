
package DAO;

import DTO.BaoHanh_DTO;
import java.sql.*;
import java.util.ArrayList;

public class BaoHanh_DAO {
    private Connection conn; 
    private static final String DATABASE = "CuaHangBanXeMay";
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=" + DATABASE + ";encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "12345";
   

    private Connection getConnection() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }

    public boolean themPhieuBaoHanh(BaoHanh_DTO bh) {
        String sql = "INSERT INTO BaoHanh (ID_baohanh, ID_sanpham, ngaybatdau, ngayketthuc) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = getConnection().prepareStatement(sql);
            ps.setString(1, bh.getWarrantyId());
            ps.setString(2, bh.getProductId());
            ps.setString(3, bh.getStartDate());
            ps.setString(4, bh.getEndDate());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<BaoHanh_DTO> layDanhSachBaoHanh() {
        ArrayList<BaoHanh_DTO> ds = new ArrayList<>();
        String sql = "SELECT * FROM BaoHanh";
        try {
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                BaoHanh_DTO bh = new BaoHanh_DTO(
                    rs.getString("ID_baohanh"),
                    rs.getString("ID_sanpham"),
                    rs.getString("ngaybatdau"),
                    rs.getString("ngayketthuc")
                );
                ds.add(bh);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }
}

 
