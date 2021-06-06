package programm.ui.abstractFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Шаблон формы
 */

public class BaseFrame extends JFrame {
    public BaseFrame(){
        setTitle("Солнышки(-_-)Котятки");
        setMinimumSize(new Dimension(600,600));
        setLocationRelativeTo(null);
        setIconImage(new ImageIcon(Objects.requireNonNull(getClass().getResource("/programm/ui/abstractFrame/logo.png"))).getImage());
    }
}
