package Client;

import Server.Question;
import Server.Question2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI {
    private JPanel panel1;
    private JButton button4;
    private JButton button3;
    private JButton button1;
    private JButton button2;
    private JLabel labelQuestion;

    //    int correctAnswerIndex = (int) (Math.random() * 3);
    ArrayList<JButton> buttons = new ArrayList<>();
    Question2 question = getQuestion();

    public GUI() {
        labelQuestion.setText(question.title);

        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);
        buttons.add(button4);

        for (int i = 0; i < buttons.size(); i++) {
            var btn = buttons.get(i);
            btn.setText(question.options[i]);
            btn.setBackground(Color.yellow);
            btn.setOpaque(true);
            btn.setBorderPainted(false);
            btn.addActionListener(e -> revealAnswer());
        }
    }

    private void revealAnswer() {
        for (int i = 0; i < buttons.size(); i++) {
            JButton button = buttons.get(i);
            button.setBackground(question.guess(i) ? Color.green : Color.red);
        }
    }


    Question2 getQuestion() {
        ArrayList<Question2> questions = new ArrayList<>();
        questions.add(new Question2("Vilken är min favoritfärg?", new String[]{"Grön", "Röd", "Blå", "Gul"}, 0));
        questions.add(new Question2("Vilket är mitt favoritberg?", new String[]{"Mount Blanc", "K2", "Mount Everest", "Makalu"}, 3));
        questions.add(new Question2("Vilken är mitt favoritbär?", new String[]{"Krusbär", "Hallon", "Blåbär", "Hjortron"}, 1));
        questions.add(new Question2("Vilken är min favoritdvärg?", new String[]{"Toker", "Kloker", "Butter", "Prosit"}, 2));
        return questions.get((int) (Math.random() * questions.size()));
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("GUI");
        frame.setContentPane(new GUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
