package Client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Created by Simon Ekenberg
 * Date: 2020-11-18
 * Time: 11:48
 * Project: IntelliJ IDEA
 * Copyright: MIT
 */
public class StartPanel extends JPanel implements ActionListener {
    private JPanel namePanel = new JPanel();
    private JLabel userLabel = new JLabel("Enter your name:");
    private JTextField enterYourName = new JTextField(20);
    private JButton finished = new JButton("Finished");
    private JPanel avatarPanel = new JPanel();
    private JButton avatarPictures = new JButton();
    Avatar avatar = new Avatar();

    StartPanel(){
        namePanel.setLayout(new FlowLayout());
        namePanel.add(userLabel);
        namePanel.add(enterYourName);
       setLayout(new BorderLayout());
       add(BorderLayout.NORTH, namePanel);
       avatarPanel.setLayout(new GridLayout(2,4));
        for (int i = 0; i < avatar.avatars.length; i++) {
            JButton button = new JButton();
            button.setIcon(avatar.avatars[i]);
            button.addActionListener(this);
            avatarPanel.add(button);
        }
        add(BorderLayout.CENTER, avatarPanel);
        add(BorderLayout.SOUTH, finished);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}

