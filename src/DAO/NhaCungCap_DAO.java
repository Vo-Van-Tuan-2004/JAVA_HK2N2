package DAO;
import DTO.NhaCungCap_DTO;
import DTO.SanPham_DTO;

import java.sql.*;
import java.util.ArrayList;

public class NhaCungCap_DAO {
    //lay connect
    private Connection conn;
    private PreparedStatement pstmt;
    private ResultSet rs;

    public NhaCungCap_DAO(Connection connection){
        this.conn=connection;
    }
    public NhaCungCap_DAO(){
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
    //them doi tuong
    public boolean themNhaCungCap(NhaCungCap_DTO ncc){
        return true;
    }
    //sua doi tuong
    public boolean suaNhaCungCap(NhaCungCap_DTO ncc){
        return true;
    }
    //xoa doi tuong
    public boolean xoaNhaCungCap(NhaCungCap_DTO ncc){
        return true;
    }
    //lay danh sach doi tuong
    public ArrayList<NhaCungCap_DTO> layDanhSachNhaCungCap(){
        ArrayList<NhaCungCap_DTO> danhSach = new ArrayList<>();
        String sql = "SELECT * FROM NhaCungCap";
        try {
            this.pstmt = this.conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                danhSach.add(new NhaCungCap_DTO(
                    rs.getString("ma_nha_cung_cap"),
                    rs.getString("ten"),
                    rs.getString("dia_chi"),
                    rs.getString("email"),
                    rs.getString("quoc_gia")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSach;
    }
    //Tim kiem doi tuong
    public NhaCungCap_DTO timNhaCungCap(String keyword){
        String sql = " SELECT * FROM NhaCungCap" +
                     "  WHERE ma_nha_cung_cap LIKE  ?" +
                      "  OR ten LIKE ? " +
                      "  OR dia_chi LIKE ? " + 
                      "  OR email LIKE ? " +
                      "  OR quoc_gia LIKE ?" ;
        try (PreparedStatement ps = conn.prepareStatement(sql)){
            for (int i=1; i<=5; i++){
                ps.setString(i, keyword);
            }
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                //tao table roi hien thi table lenh find area.
                //khi nhan vao khung find thi hien thi ra mot khung ket qua tim kiem o duoi thanh tim kiem (find area)
                //nguoi dung co the chon hang tu find area khi chon thi doi tuong nay duoc them vao vung chi tiet san pham va co the them vao gio hang nhu thong thuong.
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }              
        return null;
    }
}
