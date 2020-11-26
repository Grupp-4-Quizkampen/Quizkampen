package Client;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Simon Ekenberg
 * Date: 2020-11-18
 * Time: 11:48
 * Project: IntelliJ IDEA
 * Copyright: MIT
 */
public class StartPanel extends JPanel implements ActionListener {
    private JPanel namePanel = new JPanel();
    private JLabel userLabel = new JLabel("Skriv in ditt namn:");
    private JTextField enterYourName = new JTextField(20);
    private JButton startButton = new JButton("Starta spelet");
    private JPanel avatarPanel = new JPanel();
    Avatar avatar = new Avatar();
    private int chosenAvatarIndex = 0;
    List<JButton> buttonList = new ArrayList<>();
    Client client;

    private boolean validName, validAvatar;

    StartPanel(Client client) {
        this.client = client;
        namePanel.setLayout(new FlowLayout());
        namePanel.add(userLabel);
        namePanel.add(enterYourName);
        enterYourName.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                changed();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                changed();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                changed();
            }
        });
        setLayout(new BorderLayout());
        add(BorderLayout.NORTH, namePanel);
        avatarPanel.setLayout(new GridLayout(2, 4));
        for (int i = 0; i < avatar.avatars.length; i++) {
            JButton button = new JButton();
            buttonList.add(button);
            button.setIcon(avatar.avatars[i]);
            button.addActionListener(this);
            avatarPanel.add(button);
        }
        add(BorderLayout.CENTER, avatarPanel);
        add(BorderLayout.SOUTH, startButton);
        startButton.addActionListener(this);
        startButton.setEnabled(false);
    }

    public void changed() {
        validAvatar = enterYourName.getText().length() != 0 && enterYourName != null;
        enableStartButton();
    }

    public void enableStartButton(){
        startButton.setEnabled(validAvatar && validName);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(startButton)){
            client.connectToServer();
        }
        else {
            for (JButton chosenAvatar : buttonList) {
                if (e.getSource().equals(chosenAvatar)) {
                    for (JButton btn: buttonList) {
                        btn.setBackground(Color.WHITE);
                    }
                    chosenAvatar.setBackground(Color.GREEN);
                    chosenAvatarIndex = buttonList.indexOf(chosenAvatar);
                    validName = true;
                    enableStartButton();
                    System.out.println(chosenAvatarIndex);
                }
            }
        }

    }
}
