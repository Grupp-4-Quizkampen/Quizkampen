package Server;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    int rounds = 2;
    int questionsPerRound = 2;
    List<GameRound> gameRounds;
    List<Question> questionList;
    List<GameRound> rounds = new ArrayList<>();
    int questionsPerRound = 2;
    int numberOfRounds = 2;
    Player player1;
    Player player2;
    Path filepath = Path.of("src/Server/questions");

    Game(Player player1, Player player2){
        this.player1 = player1;
        this.player2 = player2;
        player1.start();
        player2.start();

        readFileAddToList(filepath); //move to file management

        play();
    }

    private void play() {
        for (int i = 0; i < numberOfRounds; i++) {
            rounds.add(new GameRound(player1, player2, questionsPerRound));
        }


    }

    //move to file management
    private void readFileAddToList(Path filepath) {
        try (Scanner scan = new Scanner(filepath)) {
            scan.useDelimiter(";");
            questionList = new ArrayList<>();

            while (scan.hasNext()) {
                String category = scan.next();
                String prompt = scan.next();
                String[] options = new String[4];
                for (int i = 0; i < 4; i++) {
                    options[i] = scan.next();
                }
                int correctAnswerIndex = scan.nextInt();
                questionList.add(new Question(category, prompt, options, correctAnswerIndex));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

        private void playRound(){
            for (int i = 0; i < rounds; i++) {
                GameRound round = new GameRound(questionsPerRound, questionList);
            }
            return;
        }


}
