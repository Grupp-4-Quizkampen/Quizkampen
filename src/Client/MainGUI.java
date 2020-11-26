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

    int width = 500;
    int height = 500;

    public MainGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setSize(width, height);
    }
    public static void main(String[] args) {
       new MainGUI();


    }

}



