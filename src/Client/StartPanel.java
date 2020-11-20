package Client;

import javax.swing.*;
import java.util.EventListener;

/**
 * Created by Simon Ekenberg
 * Date: 2020-11-18
 * Time: 11:48
 * Project: IntelliJ IDEA
 * Copyright: MIT
 */
public class StartPanel extends JPanel {
    private JLabel userLabel = new JLabel("Enter your name:");
    private JTextField enterYourName = new JTextField("");
    private String username;
    private JButton finished = new JButton("Finished");

    StartPanel(JFrame MainFrame){
        MainFrame.add(userLabel);
        MainFrame.add(enterYourName);

    }
}
