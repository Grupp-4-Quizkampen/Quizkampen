package Server;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by: Anton Rolin
 * Date: 12/11/2020
 * Time: 14:08
 * Project: Quizkampen
 * Copyright: MIT
 */
public class Game {
    List<Question> gameTurns;
    Path filepath = Path.of("src/Server/questions");

    Game(){
        readFileAddToList(filepath);


    }

    private void readFileAddToList(Path filepath) {
        try(Scanner scan = new Scanner(filepath)) {
            scan.useDelimiter(";");
            gameTurns = new ArrayList<>();

            while(scan.hasNext()){
                String category = scan.next();
                String question = scan.next();
                String answer1 = scan.next();
                String answer2 = scan.next();
                String answer3 = scan.next();
                String answer4 = scan.next();
                String correctAnswer = scan.next();
                gameTurns.add(new Question(category, question, answer1, answer2, answer3, answer4, correctAnswer));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
