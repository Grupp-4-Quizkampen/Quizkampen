package Client;

import Server.GameRound;
import Server.Player;
import Server.Question;
import Server.RoundResults;

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
    private RoundResults roundResults = new RoundResults();
    private GameRound gameRound;
    private int currentQuestionIndex = -1;

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
        this.gameRound = nextRound;
        askNextQuestion();
    }


    private void askNextQuestion() {
        currentQuestionIndex++;
        this.question = this.gameRound.getRoundQuestionList().get(currentQuestionIndex);
        categoryLabel.setText(question.getCategory());
        promptLabel.setText(question.getPrompt());
        for (int i = 0; i < 4; i++) {
            buttonList.get(i).setText(question.getOptions()[i]);
            buttonList.get(i).setEnabled(true);
            buttonList.get(i).setBackground(Color.white);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(nextQuestionButton)) {
            informationPanel.remove(nextQuestionButton);
            informationPanel.add(categoryLabel);
            revalidate();
            askNextQuestion();
        } else {
            revealAnswer();
            informationPanel.remove(categoryLabel);
            informationPanel.add(nextQuestionButton);
            revalidate();

            //Check if the clicked button is in the correct index of the buttonList
            roundResults.add(e.getSource().equals(buttonList.get(question.getCorrectOptionIndex())));
            if (roundResults.size() == gameRound.getRoundQuestionList().size()) {
                client.submitResults(roundResults);
            }
            if (e.getSource().equals(buttonList.get(question.getCorrectOptionIndex()))) {
                System.out.println("Rätt svar");
            }
            if (currentQuestionIndex+1 == gameRound.getRoundQuestionList().size()) {
                nextQuestionButton.setEnabled(false);
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
