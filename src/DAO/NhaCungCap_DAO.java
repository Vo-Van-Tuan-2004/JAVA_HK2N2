package DAO;
import DTO.NhaCungCap_DTO;
import java.sql.*;
import java.util.ArrayList;

public class NhaCungCap_DAO {
    //lay connect
    private Connection con;
    public NhaCungCap_DAO(Connection connection){
        this.con=connection;
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
        return null;
    }
    //Tim kiem doi tuong
    public NhaCungCap_DTO timNhaCungCap(String keyword){
        String sql = " SELECT * FROM NhaCungCap" +
                     "  WHERE ma_nha_cung_cap LIKE  ?" +
                      "  OR ten LIKE ? " +
                      "  OR dia_chi LIKE ? " + 
                      "  OR email LIKE ? " +
                      "  OR quoc_gia LIKE ?" ;
        try (PreparedStatement ps = con.prepareStatement(sql)){
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
