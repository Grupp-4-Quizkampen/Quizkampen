package Server;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Game {
    List<GameRound> gameRounds;
    List<Question> unusedQuestions;
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
        player1.setActiveGame(this);
        player2.start();
        player2.setActiveGame(this);

        readFileAddToList(filepath); //move to file management

        playRound();
    }

    private void playRound() {
        for (int i = 0; i < numberOfRounds; i++) {
            List<Question> questionsToBeAsked = randomiseQuestions();
            rounds.add(new GameRound(questionsToBeAsked));
        }


    }

    private List<Question> randomiseQuestions() {
        if (unusedQuestions.size() == 0) {
            Collections.copy(unusedQuestions, questionList);
        }

        List<Question> roundQuestionList = new ArrayList<>();
        Collections.shuffle(unusedQuestions);
        Question firstQuestion = unusedQuestions.get(0);
        roundQuestionList.add(firstQuestion);
        unusedQuestions.remove(firstQuestion);

        for (Question question : questionList) {
            if (question.getCategory().equalsIgnoreCase(firstQuestion.getCategory())) {
                roundQuestionList.add(question);
                unusedQuestions.remove(question);
            }

            if (roundQuestionList.size() == questionsPerRound)
                break;
        }
        return roundQuestionList;
    }

    //move to file management
    private void readFileAddToList(Path filepath) {
        try (Scanner scan = new Scanner(filepath)) {
            scan.useDelimiter(";");
            unusedQuestions = new ArrayList<>();

            while (scan.hasNext()) {
                String category = scan.next();
                String prompt = scan.next();
                String[] options = new String[4];
                for (int i = 0; i < 4; i++) {
                    options[i] = scan.next();
                }
                int correctAnswerIndex = scan.nextInt();
                unusedQuestions.add(new Question(category, prompt, options, correctAnswerIndex));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<GameRound> getGameRounds() {
        return gameRounds;
    }
}
