package programm;

import programm.ui.MainFrame;

import javax.swing.*;
import java.awt.*;


/**
 * Входная точка программы
 */


public class Application {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        mainFrame.setMinimumSize(new Dimension(1200, 600));
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}