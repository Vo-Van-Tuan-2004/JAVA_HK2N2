// package GUI;

// import DAO.BaoCao_DAO;
// import DTO.BaoCao_DTO;
// import java.awt.*;
// import java.util.List;
// import javax.swing.*;
// import javax.swing.table.DefaultTableModel;

// public class BaoCao_GUI extends JPanel {
//     private JTable tblDoanhThu;
//     private BaoCao_DAO baoCaoDAO = new BaoCao_DAO();
//     public BaoCao_GUI() {
//         setLayout(new BorderLayout()); 
//         tblDoanhThu = new JTable();
//         add(new JScrollPane(tblDoanhThu), BorderLayout.CENTER);  
//         loadDoanhThu();  
//     }

//     private void loadDoanhThu() {
//         List<BaoCao_DTO> list = baoCaoDAO.getDoanhThuTheoThang();  

//         // Tạo DefaultTableModel để quản lý dữ liệu bảng
//         DefaultTableModel model = new DefaultTableModel();
//         model.addColumn("Tháng");
//         model.addColumn("Doanh thu");

//         // Thêm dữ liệu vào bảng
//         for (BaoCao_DTO dto : list) {
//             model.addRow(new Object[]{dto.getThang(), dto.getTongDoanhThu()});
//         }

//         tblDoanhThu.setModel(model); 
//     }
//     public JPanel getMainPanel(){
//         return this;
//     }
// }


package GUI;

import DAO.BaoCao_DAO;
import DTO.BaoCao_DTO;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BaoCao_GUI extends JPanel {
    private BaoCao_DAO baoCaoDAO = new BaoCao_DAO();
    private JLabel lblTotalRevenue;
    private JTable tblQuarterlyRevenue;
    private JComboBox<String> yearComboBox;
    private long totalRevenue;
    private long[] quarterlyRevenue = new long[4]; // Q1, Q2, Q3, Q4

    public BaoCao_GUI() {
        setPreferredSize(new Dimension(900,700));
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setBackground(new Color(40, 44, 52)); // Dark background like the image

        // Top panel for statistics
        JPanel statsPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        statsPanel.setOpaque(false);

        // Statistic boxes (we'll only use Total Revenue for now)
        lblTotalRevenue = createStatLabel("0", "Tổng doanh thu", new Color(67, 181, 129)); // Green
        statsPanel.add(createEmptyStatPanel()); // Placeholder for "Món trong thực đơn"
        statsPanel.add(createEmptyStatPanel()); // Placeholder for "Khách hàng"
        statsPanel.add(createEmptyStatPanel()); // Placeholder for "Nhân viên"
        statsPanel.add(createStatPanel(lblTotalRevenue));

        // Bottom panel for year selector and table
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));
        bottomPanel.setOpaque(false);

        // Year selector
        JPanel yearPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        yearPanel.setOpaque(false);
        yearComboBox = new JComboBox<>(new String[]{"2025"}); // Hardcoded year for now
        yearComboBox.setPreferredSize(new Dimension(100, 30));
        yearPanel.add(yearComboBox);
        bottomPanel.add(yearPanel, BorderLayout.NORTH);

        // Quarterly revenue table
        tblQuarterlyRevenue = new JTable();
        tblQuarterlyRevenue.setRowHeight(30);
        tblQuarterlyRevenue.setFont(new Font("Arial", Font.PLAIN, 14));
        bottomPanel.add(new JScrollPane(tblQuarterlyRevenue), BorderLayout.CENTER);

        // Add panels to main layout
        add(statsPanel, BorderLayout.NORTH);
        add(bottomPanel, BorderLayout.CENTER);

        // Load data
        loadDoanhThu();
    }

    private JPanel createEmptyStatPanel() {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        return panel;
    }

    private JLabel createStatLabel(String value, String description, Color bgColor) {
        JLabel label = new JLabel("<html><center>" + value + "<br>" + description + "</center></html>", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setForeground(Color.WHITE);
        label.setOpaque(true);
        label.setBackground(bgColor);
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return label;
    }

    private JPanel createStatPanel(JLabel label) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);
        panel.add(label, BorderLayout.CENTER);
        return panel;
    }

    private void loadDoanhThu() {
        List<BaoCao_DTO> list = baoCaoDAO.getDoanhThuTheoThang();

        // Reset quarterly data
        totalRevenue = 0;
        for (int i = 0; i < quarterlyRevenue.length; i++) {
            quarterlyRevenue[i] = 0;
        }

        // Process monthly data into quarters
        for (BaoCao_DTO dto : list) {
            int month = dto.getThang();
            long revenue = dto.getTongDoanhThu();
            totalRevenue += revenue;

            // Group into quarters
            if (month >= 1 && month <= 3) {
                quarterlyRevenue[0] += revenue; // Q1
            } else if (month >= 4 && month <= 6) {
                quarterlyRevenue[1] += revenue; // Q2
            } else if (month >= 7 && month <= 9) {
                quarterlyRevenue[2] += revenue; // Q3
            } else if (month >= 10 && month <= 12) {
                quarterlyRevenue[3] += revenue; // Q4
            }
        }

        // Format numbers with commas
        DecimalFormat df = new DecimalFormat("#,###");
        lblTotalRevenue.setText("<html><center>" + df.format(totalRevenue) + "<br>Tổng doanh thu</center></html>");

        // Populate the quarterly table
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Quý");
        model.addColumn("Quý 1");
        model.addColumn("Quý 2");
        model.addColumn("Quý 3");
        model.addColumn("Quý 4");

        model.addRow(new Object[]{"Doanh thu", df.format(quarterlyRevenue[0]), df.format(quarterlyRevenue[1]), df.format(quarterlyRevenue[2]), df.format(quarterlyRevenue[3])});
        model.addRow(new Object[]{"Tổng cộng", "", "", "", df.format(totalRevenue)});

        tblQuarterlyRevenue.setModel(model);

        if (list.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không có dữ liệu doanh thu để hiển thị.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public JPanel getMainPanel() {
        return this;
    }
}