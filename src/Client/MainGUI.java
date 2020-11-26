package Client;

import javax.swing.*;

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



