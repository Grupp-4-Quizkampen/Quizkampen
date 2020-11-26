package Client;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class StartPanel extends JPanel implements ActionListener {
    private final JTextField nameField = new JTextField(20);
    private final JButton startButton = new JButton("Starta spelet");
    private int chosenAvatarIndex = 0;
    private final List<JButton> buttonList = new ArrayList<>();
    Client client;
//    JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(this);

    public JTextField getNameField() {
        return nameField;
    }

    public int getChosenAvatarIndex() {
        return chosenAvatarIndex;
    }

    public List<JButton> getButtonList() {
        return buttonList;
    }

    public JButton getStartButton() {
        return startButton;
    }

    private boolean hasValidAvatar, hasValidName;

    StartPanel(Client client) {
        this.client = client;
        JPanel namePanel = new JPanel();
        namePanel.setLayout(new FlowLayout());
        JLabel enterNameLabel = new JLabel("Skriv in ditt namn:");
        namePanel.add(enterNameLabel);
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
        JPanel avatarPanel = new JPanel();
        avatarPanel.setLayout(new GridLayout(2, 4));
        ImageIcon[] avatars = AvatarDatabase.getAvatars();
        for (ImageIcon avatar : avatars) {
            JButton button = new JButton();
            buttonList.add(button);
            button.setIcon(avatar);
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

    public void tryEnableStartButton() {
        startButton.setEnabled(hasValidName && hasValidAvatar);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (JButton chosenAvatar : buttonList) {
            if (e.getSource().equals(chosenAvatar)) {
                for (JButton btn : buttonList) {
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
