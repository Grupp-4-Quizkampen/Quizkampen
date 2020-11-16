package Client;

import Server.Question;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class GUI extends JFrame {
    private JPanel promptPanel = new JPanel();
    private JPanel answerPanel = new JPanel();
    private JButton button1 = new JButton();
    private JButton button2 = new JButton();
    private JButton button3 = new JButton();
    private JButton button4 = new JButton();
    private JLabel promptLabel = new JLabel();

    //    int correctAnswerIndex = (int) (Math.random() * 3);
    ArrayList<JButton> buttons = new ArrayList<>();
    Question question = getQuestion();

    public GUI() {
        setLayout(new BorderLayout());
        add(BorderLayout.NORTH, promptPanel);
        promptPanel.add(BorderLayout.SOUTH, promptLabel);
        add(answerPanel);

        promptLabel.setText(question.prompt);
        answerPanel.setLayout(new GridLayout(2, 2));

        button1.setPreferredSize(new Dimension(50, 20));
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);

        for (int i = 0; i < buttons.size(); i++) {
            var btn = buttons.get(i);
            btn.setText(question.options[i]);
            btn.setBackground(Color.yellow);
            btn.setOpaque(true);
            btn.setBorderPainted(true);
            btn.addActionListener(e -> revealAnswer());
            answerPanel.add(btn);
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
        answerPanel.setPreferredSize(new Dimension(500, 100));
        promptPanel.setPreferredSize(new Dimension(500,20));
        pack();
    }

    private void revealAnswer() {
        for (int i = 0; i < buttons.size(); i++) {
            JButton button = buttons.get(i);
            button.setBackground(question.guess(i) ? Color.green : Color.red);
        }
    }


    Question getQuestion() {
        ArrayList<Question> questions = new ArrayList<>();
        questions.add(new Question("", "Vilken är min favoritfärg?", new String[]{"Grön", "Röd", "Blå", "Gul"}, 0));
        questions.add(new Question("", "Vilket är mitt favoritberg?", new String[]{"Mount Blanc", "K2", "Mount Everest", "Makalu"}, 3));
        questions.add(new Question("", "Vilken är mitt favoritbär?", new String[]{"Krusbär", "Hallon", "Blåbär", "Hjortron"}, 1));
        questions.add(new Question("", "Vilken är min favoritdvärg?", new String[]{"Toker", "Kloker", "Butter", "Prosit"}, 2));
        return questions.get((int) (Math.random() * questions.size()));
    }

    public static void main(String[] args) {
        new GUI();
    }
}
