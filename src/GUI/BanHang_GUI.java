package GUI;
import java.awt.*;
import javax.swing.*;

public class BanHang_GUI {
    private JFrame frame ;
    public BanHang_GUI(){
       
        frame = new JFrame();
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
//menu panel
        JPanel menuPanel = new JPanel(new BorderLayout());
        menuPanel.setLayout(new GridLayout(10,1));
        menuPanel.setPreferredSize(new Dimension(150,700));
//main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(850,700));
//left panel
        JPanel leftJPanel = new JPanel(new BorderLayout());
        leftJPanel.setPreferredSize(new Dimension(600, 700));

        JPanel dsspPanel = new JPanel(new BorderLayout());
        dsspPanel.setPreferredSize(new Dimension(600,400));
        dsspPanel.setBackground(Color.BLUE);
        JPanel gioPanel = new JPanel(new BorderLayout());
        gioPanel.setPreferredSize(new Dimension(600, 300));
        gioPanel.setBackground(Color.GREEN);
//right panel        
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setPreferredSize(new Dimension(250,700));
       

        JPanel chitietPanel = new JPanel(new BorderLayout());
        chitietPanel.setPreferredSize(new Dimension(250, 400));
        chitietPanel.setBackground(Color.GREEN);
        JPanel chucnangPanel = new JPanel(new BorderLayout());
        chucnangPanel.setPreferredSize(new Dimension(250,300));
        chucnangPanel.setBackground(Color.BLUE);

        leftJPanel.add(dsspPanel, BorderLayout.NORTH);
        leftJPanel.add(gioPanel, BorderLayout.CENTER);

        rightPanel.add(chitietPanel, BorderLayout.NORTH);
        rightPanel.add(chucnangPanel, BorderLayout.CENTER);

        mainPanel.add(leftJPanel, BorderLayout.WEST);
        mainPanel.add(rightPanel, BorderLayout.CENTER);

        frame.add(menuPanel, BorderLayout.WEST);
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setVisible(true);

    }
    public static void main(String[] args) {
       new BanHang_GUI();
    }
}
