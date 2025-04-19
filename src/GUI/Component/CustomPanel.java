package GUI.Component;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class CustomPanel {
    public void stypeMenuPanel(JPanel x){
        x.setLayout(new BoxLayout(x, BoxLayout.Y_AXIS));
        x.setBackground(new Color(50, 60, 70));
    }
}
