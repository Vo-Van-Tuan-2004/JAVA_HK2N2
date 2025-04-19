package GUI.Component;

import java.awt.*;
import javax.swing.*;

public class CustomButton {
    // Phương thức áp dụng định dạng cho menu JButton
    public void styleMenuButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(70, 80, 90));
        button.setForeground(Color.WHITE);
        button.setMaximumSize(new Dimension(120, 40));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    //Phuong thucap dung dinh dang cho cac button khac
    public void stypeNomalNutton(JButton button){

    }
    //Phuong thuc ap dung dinh dang cho cac button la hinhf anh
    public void stypeImageButton(JButton button){
        
    }
}
