package Client;

import Server.GameRound;
import Server.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GamePanel extends JPanel implements ActionListener {
    private JPanel questionPanel = new JPanel();
    private JPanel answerGrid = new JPanel();
    private JPanel informationPanel = new JPanel();
    private JButton nextQuestionButton = new JButton("Nästa fråga");
    private JLabel promptLabel = new JLabel();
    private JLabel categoryLabel = new JLabel();
    private Client client;
    private List<JButton> buttonList = new ArrayList<>();
    private Question question;

    public List<JButton> getButtonList() {
        return buttonList;
    }

    GamePanel(Client client) {
        this.client = client;
        setLayout(new BorderLayout());

        questionPanel.setLayout(new FlowLayout());
        questionPanel.add(promptLabel);
        add(BorderLayout.NORTH, questionPanel);

        answerGrid.setLayout(new GridLayout(2, 2));
        for (int i = 0; i < 4; i++) {
            JButton button = new JButton();
            button.addActionListener(this);
            buttonList.add(button);
            answerGrid.add(button);
        }
        add(BorderLayout.CENTER, answerGrid);

        informationPanel.setLayout(new FlowLayout());
        informationPanel.add(categoryLabel);
        add(BorderLayout.SOUTH, informationPanel);

        nextQuestionButton.addActionListener(this);
    }

    public void nextRound(GameRound nextRound) {
        for (Question question : nextRound.getRoundQuestionList()) {
            this.question = question;
            ask();
        }
    }

    private void ask() {
        categoryLabel.setText(question.getCategory());
        promptLabel.setText(question.getPrompt());
        for (int i = 0; i < 4; i++) {
            buttonList.get(i).setText(question.getOptions()[i]);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(nextQuestionButton)) {
            setVisible(false);
        } else {
            revealAnswer();
            informationPanel.remove(categoryLabel);
            informationPanel.add(nextQuestionButton);
            revalidate();

            //Check if the clicked button is in the correct index of the buttonList
            client.answeredCorrectly(e.getSource().equals(buttonList.get(question.getCorrectOptionIndex())));
            if (e.getSource().equals(buttonList.get(question.getCorrectOptionIndex()))) {
                System.out.println("Rätt svar");
            }
        }
    }

    private void revealAnswer() {
        for (int i = 0; i < buttonList.size(); i++) {
            buttonList.get(i).setBackground(question.getCorrectOptionIndex() == i ? Color.green : Color.red);
            buttonList.get(i).setEnabled(false);
        }
    }
}
