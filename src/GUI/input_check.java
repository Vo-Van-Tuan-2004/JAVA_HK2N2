package GUI;

import java.awt.Color;
import javax.swing.JTextField;

public class input_check{
    public void addLiveValidation(JTextField field, String type, Runnable onValid) {
    field.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
        public void changedUpdate(javax.swing.event.DocumentEvent e) {
            validateField();
        }
        public void removeUpdate(javax.swing.event.DocumentEvent e) {
            validateField();
        }
        public void insertUpdate(javax.swing.event.DocumentEvent e) {
            validateField();
        }

        private void validateField() {
            String text = field.getText().trim();
            boolean isValid = switch (type) {
                case "int" -> text.matches("\\d+");
                case "double" -> text.matches("\\d+(\\.\\d+)?");
                case "string" -> !text.isEmpty();
                case "date" -> text.matches("\\d{2}/\\d{2}/\\d{4}"); // ví dụ: dd/MM/yyyy
                default -> false;
            };
            if (isValid) {
                field.setBackground(Color.WHITE);
                onValid.run();  // Nếu hợp lệ thì làm gì đó (ví dụ: cập nhật thành tiền)
            } else {
                field.setBackground(Color.PINK);
            }
        }
    });
}


}