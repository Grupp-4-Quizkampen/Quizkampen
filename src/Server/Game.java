package Server;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Game implements Serializable {
    List<GameRound> gameRounds = new ArrayList<>();
    List<Question> unusedQuestions;
    List<Question> questionList;

    PropertiesHandler propertiesHandler = new PropertiesHandler();
    int questionsPerRound = propertiesHandler.getNumberOfQuestions();
    int numberOfRounds = propertiesHandler.getNumberOfRounds();

    Player player1;
    Player player2;
    Path filepath = Path.of("src/Server/questions");

    Game(Player player1, Player player2){
        readFileAddToList(filepath); //move to file management
        generateGameRounds();

        player1.opponent = player2;
        player2.opponent = player1;

        this.player1 = player1;
        this.player2 = player2;

        player1.start();
        player1.setActiveGame(this);
        player2.start();
        player2.setActiveGame(this);


    }

    private void generateGameRounds() {
        for (int i = 0; i < numberOfRounds; i++) {
            List<Question> questionsToBeAsked = generateQuestions();
            gameRounds.add(new GameRound(questionsToBeAsked));
        }


    }

    private List<Question> generateQuestions() {
        if (unusedQuestions.size() == 0) {
            Collections.copy(unusedQuestions, questionList);
        }

        List<Question> roundQuestionList = new ArrayList<>();
        Collections.shuffle(unusedQuestions);
        Question firstQuestion = unusedQuestions.get(0);
        roundQuestionList.add(firstQuestion);
        unusedQuestions.remove(firstQuestion);

        for (Question question : unusedQuestions) {
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

            int counter = 0;
            while (scan.hasNext()) {
                System.out.println("Loop "+ ++counter);
                String category = scan.next();
                String prompt = scan.next();
                String[] options = new String[4];
                for (int i = 0; i < 4; i++) {
                    options[i] = scan.next();
                    System.out.println(options[i]);
                }
                int correctAnswerIndex = Integer.parseInt(scan.next().trim());
                unusedQuestions.add(new Question(category, prompt, options, correctAnswerIndex));
                System.out.println(unusedQuestions.get(unusedQuestions.size()-1).getCategory());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<GameRound> getGameRounds() {
        return gameRounds;
    }

}
