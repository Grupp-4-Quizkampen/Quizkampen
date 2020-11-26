package Client;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Simon Ekenberg
 * Date: 2020-11-18
 * Time: 11:48
 * Project: IntelliJ IDEA
 * Copyright: MIT
 */
public class ResultPanel extends JPanel {
    private JPanel scorePanel = new JPanel();
    private JPanel namePanel = new JPanel();
    private JLabel firstName = new JLabel("");
    private JLabel secondName = new JLabel("");
    JLabel firstPlayerScore = new JLabel("");
    JLabel dash = new JLabel(" - ");
    JLabel secondPlayerScore = new JLabel("");
    JButton nextRoundButton = new JButton("Next Round");

    ResultPanel(){
        namePanel.setLayout(new FlowLayout());
    namePanel.add(firstName);
    namePanel.add(secondName);
    scorePanel.setLayout(new BorderLayout());
    firstPlayerScore.setText("");
    dash.setText("");
    secondPlayerScore.setText("");

        for (int i = 0; i < ; i++) {
            
        }
    }

}
