// package GUI;

// import BLL.BanHang_BLL;
// import BLL.check;
// import DTO.ChiTietHoaDonBan_DTO;
// import DTO.HoaDonBan_DTO;
// import java.awt.BorderLayout;
// import java.awt.Color;
// import java.awt.Component;
// import java.awt.Dimension;
// import java.awt.Font;
// import java.sql.*;
// import java.time.LocalDate;
// import java.time.format.DateTimeFormatter;
// import java.util.ArrayList;
// import java.util.List;
// import javax.swing.*;
// import javax.swing.table.DefaultTableModel;

// public class XuatHoaDonBan_GUI extends JFrame {
//     private BanHang_BLL banHangBLL;

//     public XuatHoaDonBan_GUI(HoaDonBan_DTO hoadon, DefaultTableModel model) {
//         banHangBLL = new BanHang_BLL();
//         setSize(400, 500);
//         setLocationRelativeTo(null);
//         setLayout(new BorderLayout());

//         JPanel tt_Panel = new JPanel();
//         tt_Panel.setLayout(new BoxLayout(tt_Panel, BoxLayout.Y_AXIS));
//         tt_Panel.setPreferredSize(new Dimension(350, 200));

//         JPanel MaHD_Panel = new JPanel();
//         MaHD_Panel.setPreferredSize(new Dimension(350, 40));
//         JLabel MaHD_Label = new JLabel("Mã hóa đơn");
//         MaHD_Label.setPreferredSize(new Dimension(150, 40));
//         JTextField MaHD_Field = new JTextField();
//         MaHD_Field.setPreferredSize(new Dimension(100, 30));
//         MaHD_Field.setEditable(false);
//         MaHD_Field.setText(banHangBLL.getHoaDonBanDAO().TaoMaMoi());
//         hoadon.setMa_hoa_don_ban(MaHD_Field.getText());
//         MaHD_Panel.add(MaHD_Label);
//         MaHD_Panel.add(MaHD_Field);

//         JPanel MaKH_Panel = new JPanel();
//         MaKH_Panel.setPreferredSize(new Dimension(350, 40));
//         JLabel MaKH_Label = new JLabel("SDT khách hàng");
//         MaKH_Label.setPreferredSize(new Dimension(150, 40));
//         JTextField MaKH_Field = new JTextField();
//         MaKH_Field.setPreferredSize(new Dimension(100, 30));
//         MaKH_Panel.add(MaKH_Label);
//         MaKH_Panel.add(MaKH_Field);

//         JPanel MaNV_Panel = new JPanel();
//         MaNV_Panel.setPreferredSize(new Dimension(350, 40));
//         JLabel MaNV_Label = new JLabel("Mã nhân viên");
//         MaNV_Label.setPreferredSize(new Dimension(150, 40));
//         JTextField MaNV_Field = new JTextField();
//         MaNV_Field.setPreferredSize(new Dimension(100, 30));
//         MaNV_Field.setEditable(false);
//         MaNV_Field.setText(hoadon.getMa_nhan_vien());
//         MaNV_Panel.add(MaNV_Label);
//         MaNV_Panel.add(MaNV_Field);

//         JPanel Ngay_Panel = new JPanel();
//         Ngay_Panel.setPreferredSize(new Dimension(350, 40));
//         JLabel Ngay_Label = new JLabel("Ngày xuất");
//         Ngay_Label.setPreferredSize(new Dimension(150, 40));
//         JTextField Ngay_Field = new JTextField();
//         LocalDate today = LocalDate.now();
//         DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//         Ngay_Field.setText(today.format(formatter));
//         Ngay_Field.setEditable(false);
//         Ngay_Field.setPreferredSize(new Dimension(100, 30));
//         Ngay_Panel.add(Ngay_Label);
//         Ngay_Panel.add(Ngay_Field);

//         JPanel Tongtien_Panel = new JPanel();
//         Tongtien_Panel.setPreferredSize(new Dimension(350, 40));
//         JLabel Tongtien_Label = new JLabel("Tổng tiền");
//         Tongtien_Label.setPreferredSize(new Dimension(150, 40));
//         JTextField Tongtien_Field = new JTextField();
//         Tongtien_Field.setEditable(false);
//         Tongtien_Field.setPreferredSize(new Dimension(100, 30));
//         Tongtien_Field.setText(Long.toString(hoadon.getTong_tien()));
//         Tongtien_Panel.add(Tongtien_Label);
//         Tongtien_Panel.add(Tongtien_Field);

//         tt_Panel.add(MaHD_Panel);
//         tt_Panel.add(MaKH_Panel);
//         tt_Panel.add(MaNV_Panel);
//         tt_Panel.add(Ngay_Panel);
//         tt_Panel.add(Tongtien_Panel);

//         JPanel ds_Panel = new JPanel();
//         ds_Panel.setPreferredSize(new Dimension(350, 200));
//         JTable ds_Table = new JTable();
//         ds_Table.setModel(model);
//         JScrollPane ds_ScrollPane = new JScrollPane(ds_Table);
//         ds_Panel.add(ds_ScrollPane);

//         JButton Xacnhan_Button = new JButton("Xác nhận");
//         Xacnhan_Button.setFont(new Font("Arial", Font.BOLD, 20));
//         Xacnhan_Button.setAlignmentX(Component.CENTER_ALIGNMENT);
//         Xacnhan_Button.setMaximumSize(new Dimension(200, 40));
//         Xacnhan_Button.setBackground(new Color(255, 128, 0));
//         Xacnhan_Button.setForeground(Color.WHITE);

//         add(tt_Panel, BorderLayout.NORTH);
//         add(ds_Panel, BorderLayout.CENTER);
//         add(Xacnhan_Button, BorderLayout.SOUTH);

//         Xacnhan_Button.addActionListener(e -> {
//             String sdt = MaKH_Field.getText();
//             if (!check.checkSDT(sdt)) {
//                 JOptionPane.showMessageDialog(null, "SĐT không hợp lệ!");
//                 return;
//             }

//             try (Connection con = DriverManager.getConnection(
//                 "jdbc:sqlserver://localhost:1433;databaseName=CuaHangBanXeMay;encrypt=true;trustServerCertificate=true",
//                 "sa", "12345")) {
//                 // Lấy hoặc tạo ma_khach_hang
//                 String maKhachHang = getOrCreateMaKhachHang(sdt, con);
//                 if (maKhachHang == null) {
//                     JOptionPane.showMessageDialog(null, "Lỗi khi xử lý thông tin khách hàng!");
//                     return;
//                 }
//                 hoadon.setMa_khach_hang(maKhachHang);

//                 // Tạo danh sách chi tiết hóa đơn
//                 List<ChiTietHoaDonBan_DTO> chiTietList = new ArrayList<>();
//                 for (int i = 0; i < model.getRowCount(); i++) {
//                     ChiTietHoaDonBan_DTO chiTiet = new ChiTietHoaDonBan_DTO();
//                     chiTiet.setMa_san_pham(model.getValueAt(i, 0).toString());
//                     chiTiet.setSo_luong(Integer.parseInt(model.getValueAt(i, 2).toString()));
//                     chiTiet.setDon_gia(Integer.parseInt(model.getValueAt(i, 3).toString()));
//                     chiTietList.add(chiTiet);
//                 }

//                 // Lưu hóa đơn qua BLL
//                 boolean success = banHangBLL.TaoHoaDon(hoadon, chiTietList);
//                 if (success) {
//                     JOptionPane.showMessageDialog(null, "Hóa đơn đã được lưu!");
//                     dispose();
//                 } else {
//                     JOptionPane.showMessageDialog(null, "Lỗi khi lưu hóa đơn!");
//                 }
//             } catch (SQLException ex) {
//                 ex.printStackTrace();
//                 JOptionPane.showMessageDialog(null, "Lỗi kết nối cơ sở dữ liệu: " + ex.getMessage());
//             }
//         });
//         setVisible(true);
//     }

//     private String getOrCreateMaKhachHang(String sdt, Connection con) throws SQLException {
//         // Kiểm tra khách hàng có tồn tại không dựa trên sdt
//         String sqlCheck = "SELECT ma_khach_hang FROM KhachHang WHERE sdt = ?";
//         try (PreparedStatement psCheck = con.prepareStatement(sqlCheck)) {
//             psCheck.setString(1, sdt);
//             ResultSet rs = psCheck.executeQuery();
//             if (rs.next()) {
//                 return rs.getString("ma_khach_hang");
//             }
//         }

//         // Tạo mã khách hàng mới
//         String maKhachHang;
//         String sqlMax = "SELECT MAX(ma_khach_hang) AS max_code FROM KhachHang";
//         try (Statement stmt = con.createStatement()) {
//             ResultSet rs = stmt.executeQuery(sqlMax);
//             if (rs.next()) {
//                 String maxCode = rs.getString("max_code");
//                 if (maxCode != null && maxCode.startsWith("KH")) {
//                     int currentNumber = Integer.parseInt(maxCode.substring(2));
//                     maKhachHang = "KH" + String.format("%02d", currentNumber + 1);
//                 } else {
//                     maKhachHang = "KH01";
//                 }
//             } else {
//                 maKhachHang = "KH01";
//             }
//         }

//         // Tạo khách hàng mới
//         String sqlInsert = "INSERT INTO KhachHang (ma_khach_hang, ten, sdt, email, dia_chi) VALUES (?, ?, ?, ?, ?)";
//         try (PreparedStatement psInsert = con.prepareStatement(sqlInsert)) {
//             psInsert.setString(1, maKhachHang);
//             psInsert.setString(2, "Khách lẻ");
//             psInsert.setString(3, sdt);
//             psInsert.setString(4, "");
//             psInsert.setString(5, "");
//             psInsert.executeUpdate();
//             return maKhachHang;
//         }
//     }
// }
package GUI;

import BLL.BanHang_BLL;
import BLL.check;
import DTO.ChiTietHoaDonBan_DTO;
import DTO.HoaDonBan_DTO;
import DTO.taiKhoan_DTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class XuatHoaDonBan_GUI extends JFrame {
    private BanHang_BLL banHangBLL;
    private List<ChiTietHoaDonBan_DTO> chiTietList;
    private BanHang_GUI banHangGUI;

    public XuatHoaDonBan_GUI(HoaDonBan_DTO hoadon, DefaultTableModel model, List<ChiTietHoaDonBan_DTO> chiTietList, taiKhoan_DTO user) {
        this.banHangBLL = new BanHang_BLL();
        this.chiTietList = chiTietList;
        this.banHangGUI = banHangGUI;

        setSize(400, 500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel tt_Panel = new JPanel();
        tt_Panel.setLayout(new BoxLayout(tt_Panel, BoxLayout.Y_AXIS));
        tt_Panel.setPreferredSize(new Dimension(350, 200));

        JPanel MaHD_Panel = new JPanel();
        MaHD_Panel.setPreferredSize(new Dimension(350, 40));
        JLabel MaHD_Label = new JLabel("Mã hóa đơn");
        MaHD_Label.setPreferredSize(new Dimension(150, 40));
        JTextField MaHD_Field = new JTextField();
        MaHD_Field.setPreferredSize(new Dimension(100, 30));
        MaHD_Field.setEditable(false);
        MaHD_Field.setText(banHangBLL.getHoaDonBanDAO().TaoMaMoi());
        hoadon.setMa_hoa_don_ban(MaHD_Field.getText());
        MaHD_Panel.add(MaHD_Label);
        MaHD_Panel.add(MaHD_Field);

        JPanel MaKH_Panel = new JPanel();
        MaKH_Panel.setPreferredSize(new Dimension(350, 40));
        JLabel MaKH_Label = new JLabel("SDT khách hàng");
        MaKH_Label.setPreferredSize(new Dimension(150, 40));
        JTextField MaKH_Field = new JTextField();
        MaKH_Field.setPreferredSize(new Dimension(100, 30));
        MaKH_Panel.add(MaKH_Label);
        MaKH_Panel.add(MaKH_Field);

        JPanel MaNV_Panel = new JPanel();
        MaNV_Panel.setPreferredSize(new Dimension(350, 40));
        JLabel MaNV_Label = new JLabel("Mã nhân viên");
        MaNV_Label.setPreferredSize(new Dimension(150, 40));
        JTextField MaNV_Field = new JTextField();
        MaNV_Field.setPreferredSize(new Dimension(100, 30));
        MaNV_Field.setEditable(false);
        MaNV_Field.setText(hoadon.getMa_nhan_vien());
        MaNV_Panel.add(MaNV_Label);
        MaNV_Panel.add(MaNV_Field);

        JPanel Ngay_Panel = new JPanel();
        Ngay_Panel.setPreferredSize(new Dimension(350, 40));
        JLabel Ngay_Label = new JLabel("Ngày xuất");
        Ngay_Label.setPreferredSize(new Dimension(150, 40));
        JTextField Ngay_Field = new JTextField();
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Ngay_Field.setText(today.format(formatter));
        Ngay_Field.setEditable(false);
        Ngay_Field.setPreferredSize(new Dimension(100, 30));
        Ngay_Panel.add(Ngay_Label);
        Ngay_Panel.add(Ngay_Field);

        JPanel Tongtien_Panel = new JPanel();
        Tongtien_Panel.setPreferredSize(new Dimension(350, 40));
        JLabel Tongtien_Label = new JLabel("Tổng tiền");
        Tongtien_Label.setPreferredSize(new Dimension(150, 40));
        JTextField Tongtien_Field = new JTextField();
        Tongtien_Field.setEditable(false);
        Tongtien_Field.setPreferredSize(new Dimension(100, 30));
        Tongtien_Field.setText(Long.toString(hoadon.getTong_tien()));
        Tongtien_Panel.add(Tongtien_Label);
        Tongtien_Panel.add(Tongtien_Field);

        tt_Panel.add(MaHD_Panel);
        tt_Panel.add(MaKH_Panel);
        tt_Panel.add(MaNV_Panel);
        tt_Panel.add(Ngay_Panel);
        tt_Panel.add(Tongtien_Panel);

        JPanel ds_Panel = new JPanel();
        ds_Panel.setPreferredSize(new Dimension(350, 200));
        JTable ds_Table = new JTable();
        ds_Table.setModel(model);
        JScrollPane ds_ScrollPane = new JScrollPane(ds_Table);
        ds_Panel.add(ds_ScrollPane);

        JButton Xacnhan_Button = new JButton("Xác nhận");
        Xacnhan_Button.setFont(new Font("Arial", Font.BOLD, 20));
        Xacnhan_Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        Xacnhan_Button.setMaximumSize(new Dimension(200, 40));
        Xacnhan_Button.setBackground(new Color(255, 128, 0));
        Xacnhan_Button.setForeground(Color.WHITE);

        add(tt_Panel, BorderLayout.NORTH);
        add(ds_Panel, BorderLayout.CENTER);
        add(Xacnhan_Button, BorderLayout.SOUTH);

        Xacnhan_Button.addActionListener(e -> {
            String sdt = MaKH_Field.getText();
            if (!check.checkSDT(sdt)) {
                JOptionPane.showMessageDialog(null, "SĐT không hợp lệ!");
                return;
            }

            try (Connection con = DriverManager.getConnection(
                "jdbc:sqlserver://localhost:1433;databaseName=CuaHangBanXeMay;encrypt=true;trustServerCertificate=true",
                "sa", "12345")) {
                // Lấy hoặc tạo ma_khach_hang
                String maKhachHang = getOrCreateMaKhachHang(sdt, con);
                if (maKhachHang == null) {
                    JOptionPane.showMessageDialog(null, "Lỗi khi xử lý thông tin khách hàng!");
                    return;
                }
                hoadon.setMa_khach_hang(maKhachHang);

                // Lưu hóa đơn qua BLL
                boolean success = banHangBLL.TaoHoaDon(hoadon, chiTietList);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Hóa đơn đã được lưu!");
                    // // Cập nhật giao diện BanHang_GUI
                    // banHangGUI.resetUIAfterInvoiceSaved();
                    // dispose();
                    dispose();
                } else {

                    JOptionPane.showMessageDialog(null, "Lỗi khi lưu hóa đơn!");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi kết nối cơ sở dữ liệu: " + ex.getMessage());
            }
        });
        setVisible(true);
    }

    private String getOrCreateMaKhachHang(String sdt, Connection con) throws SQLException {
        // Kiểm tra khách hàng có tồn tại không dựa trên sdt
        String sqlCheck = "SELECT ma_khach_hang FROM KhachHang WHERE sdt = ?";
        try (PreparedStatement psCheck = con.prepareStatement(sqlCheck)) {
            psCheck.setString(1, sdt);
            ResultSet rs = psCheck.executeQuery();
            if (rs.next()) {
                return rs.getString("ma_khach_hang");
            }
        }

        // Tạo mã khách hàng mới
        String maKhachHang;
        String sqlMax = "SELECT MAX(ma_khach_hang) AS max_code FROM KhachHang";
        try (Statement stmt = con.createStatement()) {
            ResultSet rs = stmt.executeQuery(sqlMax);
            if (rs.next()) {
                String maxCode = rs.getString("max_code");
                if (maxCode != null && maxCode.startsWith("KH")) {
                    int currentNumber = Integer.parseInt(maxCode.substring(2));
                    maKhachHang = "KH" + String.format("%02d", currentNumber + 1);
                } else {
                    maKhachHang = "KH01";
                }
            } else {
                maKhachHang = "KH01";
            }
        }

        // Tạo khách hàng mới
        String sqlInsert = "INSERT INTO KhachHang (ma_khach_hang, ten, sdt, email, dia_chi) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement psInsert = con.prepareStatement(sqlInsert)) {
            psInsert.setString(1, maKhachHang);
            psInsert.setString(2, "Khách lẻ");
            psInsert.setString(3, sdt);
            psInsert.setString(4, "");
            psInsert.setString(5, "");
            psInsert.executeUpdate();
            return maKhachHang;
        }
    }
}