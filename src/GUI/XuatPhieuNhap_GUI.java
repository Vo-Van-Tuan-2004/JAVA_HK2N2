package GUI;

import DAO.ChiTietPhieuNhap_DAO;
import DAO.PhieuNhap_DAO;
import DTO.ChiTietPhieuNhap_DTO;
import DTO.PhieuNhap_DTO;
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

public class XuatPhieuNhap_GUI extends JFrame {
    private PhieuNhap_DTO n_hoadon_DTO = new PhieuNhap_DTO();
    private PhieuNhap_DAO n_hoadon_DAO = new PhieuNhap_DAO();
    private ArrayList<ChiTietPhieuNhap_DTO> n_chitiet = new ArrayList<>(); 
    
    public XuatPhieuNhap_GUI(PhieuNhap_DTO hoadon, DefaultTableModel model){
        setSize(400,500);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel tt_Panel = new JPanel();
        tt_Panel.setLayout(new BoxLayout(tt_Panel, BoxLayout.Y_AXIS));
        tt_Panel.setPreferredSize(new Dimension(350,200));
        tt_Panel.setBorder(new EmptyBorder(0,0,0,0));
        
        JPanel MaPN_Panel = new JPanel();
        MaPN_Panel.setPreferredSize(new Dimension(350,40));
        JLabel MaPN_Label = new JLabel("Mã phiếu nhập");
        MaPN_Label.setPreferredSize(new Dimension(150,40));
        JTextField MaPN_Field = new JTextField();
        MaPN_Field.setPreferredSize(new Dimension(100,30));
        MaPN_Field.setEditable(false);
        MaPN_Field.setText(n_hoadon_DAO.TaoMaNhapMoi());
        hoadon.setMa_phieu_nhap(n_hoadon_DAO.TaoMaNhapMoi());
        MaPN_Panel.add(MaPN_Label);
        MaPN_Panel.add(MaPN_Field);

        JPanel MaNCC_Panel = new JPanel();
        MaNCC_Panel.setPreferredSize(new Dimension(350,40));
        JLabel MaNCC_Label = new JLabel("Mã nhà cung cấp");
        MaNCC_Label.setPreferredSize(new Dimension(150,40));
        JTextField MaNCC_Field = new JTextField();
        MaNCC_Field.setPreferredSize(new Dimension(100,30));
        MaNCC_Field.setEditable(false);
        MaNCC_Field.setText(hoadon.getMa_nha_cung_cap());
        MaNCC_Panel.add(MaNCC_Label);
        MaNCC_Panel.add(MaNCC_Field);

        // JPanel TenNCC_Panel = new JPanel();
        // TenNCC_Panel.setPreferredSize(new Dimension(350,40));
        // JLabel TenNCC_Label = new JLabel("Tên nhà cung cấp");
        // TenNCC_Label.setPreferredSize(new Dimension(150,40));
        // JTextField TenNCC_Field = new JTextField();
        // TenNCC_Field.setPreferredSize(new Dimension(100,30));
        // NhaCungCap_DAO ncc_DAO = new NhaCungCap_DAO();
        // NhaCungCap_DTO ncc_DTO = ncc_DAO.timNhaCungCap(hoadon.getMa_nha_cung_cap());
        // TenNCC_Field.setText(ncc_DTO.getTen());

        // TenNCC_Panel.add(TenNCC_Label);
        // TenNCC_Panel.add(TenNCC_Field);

        JPanel Ngay_Panel = new JPanel();
        Ngay_Panel.setPreferredSize(new Dimension(350,40));
        JLabel Ngay_Label = new JLabel("Ngày nhập");
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
        Tongtien_Field.setPreferredSize(new Dimension(100,30)); 
        Tongtien_Field.setText(Integer.toString(hoadon.getTong_tien()));
        Tongtien_Panel.add(Tongtien_Label);
        Tongtien_Panel.add(Tongtien_Field);

        tt_Panel.add(MaPN_Panel);
        tt_Panel.add(MaNCC_Panel);
        // tt_Panel.add(TenNCC_Panel);
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
            hoadon.setMa_phieu_nhap(MaPN_Field.getText());
            hoadon.setMa_nha_cung_cap(MaNCC_Field.getText());
            n_hoadon_DAO.ThemPhieuNhap(n_hoadon_DTO);

            for (int i = 0; i < model.getRowCount(); i++) {
                int columnCount = model.getColumnCount();
                String[] row = new String[columnCount];
                for (int j = 0; j < columnCount; j++) {
                    Object value = model.getValueAt(i, j);
                    row[j] = value != null ? value.toString() : "";
                
                ChiTietPhieuNhap_DTO tmp = new ChiTietPhieuNhap_DTO();
                tmp.setMa_phieu_nhap(row[0]);
                tmp.setMa_san_pham(row[1]);
                tmp.setSo_luong_nhap(Integer.parseInt(row[2]));
                tmp.setGia_nhap(Integer.parseInt(row[3]));

                ChiTietPhieuNhap_DAO n_chitiet_DAO = new ChiTietPhieuNhap_DAO();
                n_chitiet_DAO.themChiTietPhieuNhap(tmp);
                }
            }
            dispose();
        });
        setVisible(true);
    }
}
