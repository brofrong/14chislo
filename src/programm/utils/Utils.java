package programm.utils;
import javax.swing.*;
import java.awt.*;

/**
 * класс для вывода диалогового окна
 */

public class Utils {
    public static void showError(Component parentComponent, String text) {
        JOptionPane.showMessageDialog(parentComponent, text, " Ошибка", JOptionPane.ERROR_MESSAGE);
    }

    public static boolean showOption(Component parentComponent, String text) {
        String[] options = {"Да", "Нет"};
        return JOptionPane.showOptionDialog(parentComponent, text,
                "Подтвердите удаление", JOptionPane.YES_NO_OPTION,
                0,
                null, options, options[0]) == 0;
    }
}
