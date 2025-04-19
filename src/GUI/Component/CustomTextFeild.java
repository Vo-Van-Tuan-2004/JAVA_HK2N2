package GUI.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

public class CustomTextFeild {
    public void stypeTextFeild(JTextField x, String title){
        x.setBorder(BorderFactory.createTitledBorder(title) );
        x.setFont(new Font("Arial",Font.PLAIN, 15));
    } 
}
