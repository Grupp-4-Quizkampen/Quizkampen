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
    private JTextField nameField = new JTextField(20);
    private JButton startButton = new JButton("Starta spelet");
    private JPanel avatarPanel = new JPanel();
    AvatarDatabase avatarDatabase = new AvatarDatabase();
    private int chosenAvatarIndex = 0;
    List<JButton> buttonList = new ArrayList<>();
    Client client;
//    JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

    public JButton getStartButton() {
        return startButton;
    }

    private boolean hasValidAvatar, hasValidName;

    StartPanel(Client client) {
        this.client = client;
        namePanel.setLayout(new FlowLayout());
        namePanel.add(userLabel);
        namePanel.add(nameField);
        nameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                textFieldChanged();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                textFieldChanged();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                textFieldChanged();
            }
        });
        setLayout(new BorderLayout());
        add(BorderLayout.NORTH, namePanel);
        avatarPanel.setLayout(new GridLayout(2, 4));
        for (int i = 0; i < avatarDatabase.avatars.length; i++) {
            JButton button = new JButton();
            buttonList.add(button);
            button.setIcon(avatarDatabase.avatars[i]);
            button.addActionListener(this);
            avatarPanel.add(button);
        }
        add(BorderLayout.CENTER, avatarPanel);
        add(BorderLayout.SOUTH, startButton);
        startButton.addActionListener(client);
        startButton.setEnabled(false);
    }

    public void textFieldChanged() {
        hasValidName = nameField.getText().length() != 0;
        tryEnableStartButton();
    }

    public void tryEnableStartButton(){
        startButton.setEnabled(hasValidName && hasValidAvatar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(startButton)){
//            JFrame topFrame = (JFrame) SwingUtilities.getRoot(this);
//            var parent = this.getParent();
//            this.getParent().remove(this);
//            parent.revalidate();
//            client.connectToServer();
        }
        else {
            for (JButton chosenAvatar : buttonList) {
                if (e.getSource().equals(chosenAvatar)) {
                    for (JButton btn: buttonList) {
                        btn.setBackground(Color.WHITE);
                    }
                    chosenAvatar.setBackground(Color.GREEN);
                    chosenAvatarIndex = buttonList.indexOf(chosenAvatar);
                    hasValidAvatar = true;
                    tryEnableStartButton();
                    System.out.println(chosenAvatarIndex);
                }
            }
        }
    }
}
