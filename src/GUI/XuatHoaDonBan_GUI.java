package GUI;

import DAO.ChiTietHoaDonBan_DAO;
import DAO.HoaDonBan_DAO;
import DTO.ChiTietHoaDonBan_DTO;
import DTO.HoaDonBan_DTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class XuatHoaDonBan_GUI extends JFrame {
    private HoaDonBan_DTO n_hoadon_DTO = new HoaDonBan_DTO();
    private HoaDonBan_DAO n_hoadon_DAO = new HoaDonBan_DAO();
    private ArrayList<ChiTietHoaDonBan_DTO> n_chitiet = new ArrayList<>(); 
    
    public XuatHoaDonBan_GUI(HoaDonBan_DTO hoadon, DefaultTableModel model){
        setSize(400,500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel tt_Panel = new JPanel();
        tt_Panel.setLayout(new BoxLayout(tt_Panel, BoxLayout.Y_AXIS));
        tt_Panel.setPreferredSize(new Dimension(350,200));
        tt_Panel.setBorder(new EmptyBorder(0,0,0,0));
        
        JPanel MaHD_Panel = new JPanel();
        MaHD_Panel.setPreferredSize(new Dimension(350,40));
        JLabel MaHD_Label = new JLabel("Mã hóa đơn");
        MaHD_Label.setPreferredSize(new Dimension(150,40));
        JTextField MaHD_Field = new JTextField();
        MaHD_Field.setPreferredSize(new Dimension(100,30));
        MaHD_Field.setEditable(false);
        MaHD_Field.setText(n_hoadon_DAO.TaoMaMoi());
        hoadon.setMa_hoa_don_ban(n_hoadon_DAO.TaoMaMoi());
        MaHD_Panel.add(MaHD_Label);
        MaHD_Panel.add(MaHD_Field);

        JPanel MaKH_Panel = new JPanel();
        MaKH_Panel.setPreferredSize(new Dimension(350,40));
        JLabel MaKH_Label = new JLabel("Mã khách hàng");
        MaKH_Label.setPreferredSize(new Dimension(150,40));
        JTextField MaKH_Field = new JTextField();
        MaKH_Field.setPreferredSize(new Dimension(100,30));
        MaKH_Panel.add(MaKH_Label);
        MaKH_Panel.add(MaKH_Field);

        JPanel MaNV_Panel = new JPanel();
        MaNV_Panel.setPreferredSize(new Dimension(350,40));
        JLabel MaNV_Label = new JLabel("Mã nhân viên");
        MaNV_Label.setPreferredSize(new Dimension(150,40));
        JTextField MaNV_Field = new JTextField();
        MaNV_Field.setPreferredSize(new Dimension(100,30));
        MaNV_Field.setEditable(false);
        MaNV_Field.setText(hoadon.getMa_nhan_vien());
        MaNV_Panel.add(MaNV_Label);
        MaNV_Panel.add(MaNV_Field);

        JPanel Ngay_Panel = new JPanel();
        Ngay_Panel.setPreferredSize(new Dimension(350,40));
        JLabel Ngay_Label = new JLabel("Ngày xuất");
        Ngay_Label.setPreferredSize(new Dimension(150,40));
        JTextField Ngay_Field = new JTextField();
        LocalDate today = LocalDate.now();
        int day = today.getDayOfMonth();   
        int month = today.getMonthValue(); 
        int year = today.getYear();
        String Ngay_Text = Integer.toString(day)+ " - " + Integer.toString(month)+ " - " + Integer.toString(year);
        Ngay_Field.setText(Ngay_Text);
        Ngay_Field.setEditable(false);
        Ngay_Field.setPreferredSize(new Dimension(100,30));
        Ngay_Panel.add(Ngay_Label);
        Ngay_Panel.add(Ngay_Field);

        JPanel Tongtien_Panel = new JPanel();
        Tongtien_Panel.setPreferredSize(new Dimension(350,40));
        JLabel Tongtien_Label = new JLabel("Tổng tiền");
        Tongtien_Label.setPreferredSize(new Dimension(150,40));
        JTextField Tongtien_Field = new JTextField();
        Tongtien_Field.setEditable(false);
        Tongtien_Field.setPreferredSize(new Dimension(100,30));
        Tongtien_Field.setText(Integer.toString(hoadon.getTong_tien()));
        Tongtien_Panel.add(Tongtien_Label);
        Tongtien_Panel.add(Tongtien_Field);

        tt_Panel.add(MaHD_Panel);
        tt_Panel.add(MaKH_Panel);
        tt_Panel.add(MaNV_Panel);
        tt_Panel.add(Ngay_Panel);
        tt_Panel.add(Tongtien_Panel);


        JPanel ds_Panel = new JPanel();
        ds_Panel.setPreferredSize(new Dimension(350,200));
        ds_Panel.setBorder(new EmptyBorder(0,0,0,0));
        JTable ds_Table = new JTable();
        ds_Table.setModel(model);
        ds_Panel.add(ds_Table);

        JButton Xacnhan_Button = new JButton("Xac nhan");
        Xacnhan_Button.setFont(new Font("Arial", Font.BOLD,20));
        Xacnhan_Button.setAlignmentX(Component.CENTER_ALIGNMENT);
        Xacnhan_Button.setMaximumSize(new Dimension(200, 40));
        Xacnhan_Button.setBackground(new Color(255, 128, 0));
        Xacnhan_Button.setForeground(Color.WHITE);

        add(tt_Panel, BorderLayout.NORTH);
        add(ds_Panel, BorderLayout.CENTER);
        add(Xacnhan_Button, BorderLayout.SOUTH);

        Xacnhan_Button.addActionListener(e->{
            hoadon.setMa_hoa_don_ban(MaHD_Field.getText());
            hoadon.setMa_khach_hang(MaKH_Field.getText());
            hoadon.setMa_nhan_vien(MaNV_Field.getText());
            n_hoadon_DAO.them(n_hoadon_DTO);

            for (int i = 0; i < model.getRowCount(); i++) {
                int columnCount = model.getColumnCount();
                String[] row = new String[columnCount];
                for (int j = 0; j < columnCount; j++) {
                    Object value = model.getValueAt(i, j);
                    row[j] = value != null ? value.toString() : "";
                
                ChiTietHoaDonBan_DTO tmp = new ChiTietHoaDonBan_DTO();
                tmp.setMa_hoa_don_ban(row[0]);
                tmp.setMa_san_pham(row[1]);
                tmp.setSo_luong(Integer.parseInt(row[2]));
                tmp.setDon_gia(Integer.parseInt(row[3]));

                ChiTietHoaDonBan_DAO n_chitiet_DAO = new ChiTietHoaDonBan_DAO();
                n_chitiet_DAO.themChiTietHoaDonBan(tmp);
                }
            }
        });
        setVisible(true);
    }
}
