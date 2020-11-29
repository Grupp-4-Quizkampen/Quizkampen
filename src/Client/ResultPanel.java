package Client;

import Server.PlayerData;
import Server.RoundResults;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;

public class ResultPanel extends JPanel{
    PlayerData localPlayerData;
    PlayerData opponentPlayerData;

    public void setPlayerData(PlayerData localPlayerData, PlayerData opponentPlayerData) {
        this.localPlayerData = localPlayerData;
        player1NameLabel.setText(localPlayerData.getName());
        player1Icon.setImage(AvatarDatabase.getAvatars()[localPlayerData.getAvatar()].getImage());

        this.opponentPlayerData = opponentPlayerData;
        player2NameLabel.setText(opponentPlayerData.getName());
        player2Icon.setImage(AvatarDatabase.getAvatars()[opponentPlayerData.getAvatar()].getImage());
        revalidate();
        System.out.println(localPlayerData.getAvatar() + ", " + opponentPlayerData.getAvatar());
    }

    int numberOfRounds = 2;
    int questionsPerRound = 2;
    ImageIcon[] avatar = AvatarDatabase.getAvatars();
    Border border = new LineBorder(Color.BLACK, 1, false);

    JPanel centerPanel = new JPanel();
    JButton nextRoundButton = new JButton("Nästa runda");
    JPanel centerScorePanel = new JPanel();
    JLabel scoreLabel = new JLabel("Poäng: 3-2");
    JPanel centerHistoryPanel = new JPanel();

    JPanel player1Panel = new JPanel();
    JLabel player1NameLabel = new JLabel("Spelare 1");
    ImageIcon player1Icon = avatar[0];
    JPanel player1HeaderPanel = new JPanel();
    JPanel player1HistoryPanel = new JPanel();

    JPanel player2Panel = new JPanel();
    JLabel player2NameLabel = new JLabel("Spelare 2");
    ImageIcon player2Icon = avatar[1];
    JPanel player2HeaderPanel = new JPanel();
    JPanel player2HistoryPanel = new JPanel();

    ArrayList<RoundResults> ownResultsList = new ArrayList<>();
    ArrayList<RoundResults> opponentResultsList = new ArrayList<>();

    ResultPanel(){
        setLayout(new BorderLayout());

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

        player1Panel.setLayout(new BorderLayout());
        player1HeaderPanel.setLayout(new BoxLayout(player1HeaderPanel, BoxLayout.PAGE_AXIS));
        player1NameLabel.setFont(new Font("Verdana", Font.PLAIN, 20));
        player1NameLabel.setHorizontalAlignment(0);
        player1HeaderPanel.add(player1NameLabel);
        player1HeaderPanel.add(new JLabel(player1Icon));
        player1Panel.add(BorderLayout.NORTH, player1HeaderPanel);
        player1HistoryPanel.setLayout(new GridLayout(numberOfRounds, questionsPerRound));
        for (int i = 0; i < numberOfRounds*questionsPerRound; i++) {
            JPanel player1Panel = new JPanel();
            player1Panel.setBackground(Color.GREEN);
            player1Panel.setBorder(border);
            player1HistoryPanel.add(player1Panel);
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
            JPanel player2Panel = new JPanel();
            player2Panel.setBackground(Color.RED);
            player2Panel.setBorder(border);
            player2HistoryPanel.add(player2Panel);
        }

        player2Panel.add(BorderLayout.CENTER, player2HistoryPanel);

        add(BorderLayout.WEST, player1Panel);
        add(BorderLayout.EAST, player2Panel);
        add(BorderLayout.CENTER, centerPanel);

    }

    public void updateOpponentResults(RoundResults roundResults) {
        opponentResultsList.add(roundResults);
    }

    public void updateOwnResults(RoundResults roundResults) {
        ownResultsList.add(roundResults);
    }
}
