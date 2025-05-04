package BLL;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class check {
    public static boolean checkSDT(String s) {
        return s.matches("0\\d{9}");
    }

    public static boolean checkNgay(String s) {
        if (!s.matches("\\d{2}/\\d{2}/\\d{4}")) {
            return false;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate.parse(s, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}