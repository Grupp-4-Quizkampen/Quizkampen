package Client;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class ResultPanel extends JPanel{
    int numberOfRounds = 2;
    int questionsPerRound = 2;
    Border border = new LineBorder(Color.BLACK, 1, false);

    JPanel centerPanel = new JPanel();
    ImageIcon centerIcon = AvatarDatabase.getAvatars()[1];
    JPanel centerScore = new JPanel();
    JLabel scoreLabel = new JLabel("Poäng: 3-2");
    JPanel centerHistory = new JPanel();

    JPanel player1 = new JPanel();
    JLabel player1Label = new JLabel("Spelare 1");
    ImageIcon player1Icon = AvatarDatabase.getAvatars()[0];
    JPanel player1Header = new JPanel();
    JPanel player1History = new JPanel();

    JPanel player2 = new JPanel();
    JLabel player2Label = new JLabel("Spelare 2");
    ImageIcon player2Icon = AvatarDatabase.getAvatars()[1];
    JPanel player2Header = new JPanel();
    JPanel player2History = new JPanel();

    ResultPanel(){
        setLayout(new BorderLayout());

        centerPanel.setLayout(new BorderLayout());
        centerScore.setLayout(new BoxLayout(centerScore, BoxLayout.PAGE_AXIS));
        scoreLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
        scoreLabel.setBorder(border);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        centerScore.add(scoreLabel);
        centerScore.add(new JLabel(centerIcon));
        centerPanel.add(BorderLayout.NORTH, centerScore);
        centerHistory.setLayout(new GridLayout(numberOfRounds, questionsPerRound));
        for (int i = 0; i < numberOfRounds*questionsPerRound; i++) {


            JPanel gameHistoryPanel = new JPanel();
            gameHistoryPanel.setLayout(new BorderLayout());
            JLabel questionLabel = new JLabel("Vilken färg är blå?");
            questionLabel.setBorder(border);
            gameHistoryPanel.add(BorderLayout.NORTH, questionLabel);
            JPanel answerGrid = new JPanel();
            answerGrid.setLayout(new GridLayout(2, 2));
            for (int j = 0; j < 4; j++) {
                JLabel centerLabel = new JLabel("               Röd");
                centerLabel.setBackground(Color.GRAY);
                centerLabel.setBorder(border);
                answerGrid.add(centerLabel);
            }
            gameHistoryPanel.add(BorderLayout.CENTER, answerGrid);
            centerHistory.add(gameHistoryPanel);
        }

        centerPanel.add(BorderLayout.CENTER, centerHistory);

        player1.setLayout(new BorderLayout());
        player1Header.setLayout(new BoxLayout(player1Header, BoxLayout.PAGE_AXIS));
        player1Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        player1Header.add(player1Label);
        player1Header.add(new JLabel(player1Icon));
        player1.add(BorderLayout.NORTH, player1Header);
        player1History.setLayout(new GridLayout(numberOfRounds, questionsPerRound));
        for (int i = 0; i < numberOfRounds*questionsPerRound; i++) {
            JPanel player1Panel = new JPanel();
            player1Panel.setBackground(Color.GREEN);
            player1Panel.setBorder(border);
            player1History.add(player1Panel);
        }
        player1.add(BorderLayout.CENTER, player1History);

        player2.setLayout(new BorderLayout());
        player2Header.setLayout(new BoxLayout(player2Header, BoxLayout.PAGE_AXIS));
        player2Label.setFont(new Font("Verdana", Font.PLAIN, 20));
        player2Header.add(player2Label);
        player2Header.add(new JLabel(player2Icon));
        player2.add(BorderLayout.NORTH, player2Header);
        player2History.setLayout(new GridLayout(numberOfRounds, questionsPerRound));
        for (int i = 0; i < numberOfRounds*questionsPerRound; i++) {
            JPanel player2Panel = new JPanel();
            player2Panel.setBackground(Color.RED);
            player2Panel.setBorder(border);
            player2History.add(player2Panel);
        }

        player2.add(BorderLayout.CENTER, player2History);

        add(BorderLayout.WEST, player1);
        add(BorderLayout.EAST, player2);
        add(BorderLayout.CENTER, centerPanel);

    }
}
