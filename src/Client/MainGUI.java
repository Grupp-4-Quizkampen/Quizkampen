package Client;

import Server.Question;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Simon Ekenberg
 * Date: 2020-11-18
 * Time: 20:29
 * Project: IntelliJ IDEA
 * Copyright: MIT
 */
public class MainGUI extends JFrame{

    String[] answers = {
            "Blå",
            "Röd",
            "Gul",
            "Grön"
    };

    private JPanel mainPanel = new JPanel();
    int width = 500;
    int height = 500;

    public MainGUI(Client client) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        mainPanel.setLayout(new BorderLayout());
     //   mainPanel.add(BorderLayout.CENTER, new StartPanel(client));
        mainPanel.add(BorderLayout.CENTER, new ResultPanel());
        add(mainPanel);
        setSize(width, height);
    }
    public static void main(String[] args) {
       new Client();


    }

}



