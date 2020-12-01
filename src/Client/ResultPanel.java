package Client;

import Server.PlayerData;
import Server.PropertiesHandler;
import Server.RoundResults;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ResultPanel extends JPanel implements ActionListener {
    Client client;

    PlayerData localPlayerData;
    PlayerData opponentPlayerData;

    PropertiesHandler propertiesHandler = new PropertiesHandler();
    int numberOfRounds = propertiesHandler.getNumberOfRounds();
    int questionsPerRound = propertiesHandler.getQuestionsPerRound();

    ImageIcon[] avatar = AvatarDatabase.getAvatars();
    Border border = new LineBorder(Color.BLACK, 1, false);

    JPanel player1Panel = new JPanel();
    JLabel player1NameLabel = new JLabel("Spelare 1");
    ImageIcon player1Icon = avatar[0];
    JPanel player1HeaderPanel = new JPanel();
    JPanel player1HistoryPanel = new JPanel();
    int player1Total = 0;
    List<JPanel> player1ScorePanels = new ArrayList<>();

    JPanel player2Panel = new JPanel();
    JLabel player2NameLabel = new JLabel("Spelare 2");
    ImageIcon player2Icon = avatar[1];
    JPanel player2HeaderPanel = new JPanel();
    JPanel player2HistoryPanel = new JPanel();
    int player2Total = 0;
    List<JPanel> player2ScorePanels = new ArrayList<>();

    JPanel centerPanel = new JPanel();
    JButton nextRoundButton = new JButton("Nästa runda");
    JPanel centerScorePanel = new JPanel();
    JLabel scoreLabel = new JLabel();
    JPanel centerHistoryPanel = new JPanel();

    List<RoundResults> ownResultsList = new ArrayList<>();

    List<RoundResults> opponentResultsList = new ArrayList<>();

    public void setPlayerData(PlayerData localPlayerData, PlayerData opponentPlayerData) {
        this.localPlayerData = localPlayerData;
        player1NameLabel.setText(localPlayerData.getName());
        player1Icon.setImage(avatar[localPlayerData.getAvatar()].getImage());

        this.opponentPlayerData = opponentPlayerData;
        player2NameLabel.setText(opponentPlayerData.getName());
        player2Icon.setImage(avatar[opponentPlayerData.getAvatar()].getImage());
        revalidate();
        System.out.println(localPlayerData.getAvatar() + ", " + opponentPlayerData.getAvatar());
    }

    ResultPanel(Client client){
        setLayout(new BorderLayout());
        this.client = client;
        centerPanel.setLayout(new BorderLayout());
        centerScorePanel.setLayout(new BorderLayout());
        scoreLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
        scoreLabel.setBorder(border);
        scoreLabel.setPreferredSize(new Dimension(150,105));
        scoreLabel.setHorizontalAlignment(0);
        centerScorePanel.add(BorderLayout.NORTH, scoreLabel);
        nextRoundButton.setPreferredSize(new Dimension(150,50));
        centerScorePanel.add(BorderLayout.CENTER, nextRoundButton);
        centerPanel.add(BorderLayout.NORTH, centerScorePanel);

        centerPanel.add(BorderLayout.CENTER, centerHistoryPanel);
        nextRoundButton.addActionListener(this);

        player1Panel.setLayout(new BorderLayout());
        player1HeaderPanel.setLayout(new BoxLayout(player1HeaderPanel, BoxLayout.PAGE_AXIS));
        player1NameLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        player1NameLabel.setHorizontalAlignment(0);
        player1HeaderPanel.add(player1NameLabel);
        player1HeaderPanel.add(new JLabel(player1Icon));
        player1Panel.add(BorderLayout.NORTH, player1HeaderPanel);
        player1HistoryPanel.setLayout(new GridLayout(numberOfRounds, questionsPerRound));
        for (int i = 0; i < numberOfRounds*questionsPerRound; i++) {
            JPanel scorePanel = new JPanel();
            scorePanel.setBackground(Color.GRAY);
            scorePanel.setBorder(border);
            player1HistoryPanel.add(scorePanel);
            player1ScorePanels.add(scorePanel);
        }
        player1Panel.add(BorderLayout.CENTER, player1HistoryPanel);

        player2Panel.setLayout(new BorderLayout());
        player2HeaderPanel.setLayout(new BoxLayout(player2HeaderPanel, BoxLayout.PAGE_AXIS));
        player2NameLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        player2NameLabel.setHorizontalAlignment(0);
        player2HeaderPanel.add(player2NameLabel);
        player2HeaderPanel.add(new JLabel(player2Icon));
        player2Panel.add(BorderLayout.NORTH, player2HeaderPanel);
        player2HistoryPanel.setLayout(new GridLayout(numberOfRounds, questionsPerRound));

        for (int i = 0; i < numberOfRounds*questionsPerRound; i++) {
            JPanel scorePanel = new JPanel();
            scorePanel.setBackground(Color.GRAY);
            scorePanel.setBorder(border);
            player2HistoryPanel.add(scorePanel);
            player2ScorePanels.add(scorePanel);
        }

        player2Panel.add(BorderLayout.CENTER, player2HistoryPanel);

        add(BorderLayout.WEST, player1Panel);
        add(BorderLayout.EAST, player2Panel);
        add(BorderLayout.CENTER, centerPanel);

    }

    public void updateOwnResults(RoundResults roundResults) {
        int index = 0;
        ownResultsList.add(roundResults);
        for (RoundResults roundResult : ownResultsList) {
            for (Boolean isCorrect : roundResult) {
                player1ScorePanels.get(index++).setBackground(isCorrect ? Color.GREEN : Color.RED);
                if (isCorrect) {
                    player1Total++;
                }
            }
        }
        setScore();
    }

    public void updateOpponentResults(RoundResults currentRound) {
        int index = 0;
        opponentResultsList.add(currentRound);
        for (RoundResults roundResult : opponentResultsList) {
            for (Boolean isCorrect : roundResult) {
                player2ScorePanels.get(index++).setBackground(isCorrect ? Color.GREEN : Color.RED);
                if (isCorrect) {
                    player2Total++;
                }
            }
        }
        setScore();
    }

    public void setScore() {
        scoreLabel.setText(String.format("Poäng: %d-%d", player1Total, player2Total));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == nextRoundButton) {
            client.requestNextRound();
        }
    }
}
