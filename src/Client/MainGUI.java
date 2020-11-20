package Client;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Simon Ekenberg
 * Date: 2020-11-18
 * Time: 20:29
 * Project: IntelliJ IDEA
 * Copyright: MIT
 */
public class MainGUI extends JFrame{

    private JPanel mainPanel = new JPanel();
    private JFrame mainFrame = new JFrame("Quizkampen");
    int width = 500;
    int height = 500;

    public MainGUI() {
        mainFrame.setPreferredSize(new Dimension(width, height));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }
    public static void main(String[] args) {
        new MainGUI();
    }

}



