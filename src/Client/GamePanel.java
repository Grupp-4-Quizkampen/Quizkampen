package Client;

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
    private JButton nextQuestion = new JButton("Nästa fråga");
    List<JButton> buttonList = new ArrayList<>();
    Question question;

    GamePanel(Question question) {
        showPanel(question);

    }
    public void showPanel(Question question){
            this.question = question;
            setLayout(new BorderLayout());
            questionPanel.setLayout(new FlowLayout());
            questionPanel.add(new JLabel(question.getPrompt()));
            add(BorderLayout.NORTH, questionPanel);

            answerGrid.setLayout(new GridLayout(2, 2));
            for (int i = 0; i < 4; i++) {
                JButton button = new JButton(question.getOptions()[i]);
                button.addActionListener(this);
                buttonList.add(button);
                answerGrid.add(button);
            }
            add(BorderLayout.CENTER, answerGrid);

            informationPanel.setLayout(new FlowLayout());
            informationPanel.add(new JLabel("Kategori:     " + question.getCategory()));
            add(BorderLayout.SOUTH, informationPanel);

        }



    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(nextQuestion)) {
            setVisible(false);
        }
        else {
            revealAnswer();
            questionPanel.add(nextQuestion);
            nextQuestion.addActionListener(this);
            revalidate();

        if (e.getSource().equals(buttonList.get(question.getCorrectOptionIndex()))) {
            System.out.println("Rätt svar");

        }
    }

}

    private void revealAnswer() {
        for (int i = 0; i < buttonList.size(); i++) {
            JButton button = buttonList.get(i);
            button.setBackground(question.guess(i) ? Color.green : Color.red);
        }
    }
}
