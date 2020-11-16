package Server;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
    List<Question> gameTurns;
    Path filepath = Path.of("src/Server/questions");

    Game(Player player1, Player player2){
        player1.start(); //startar spelare 1
        player2.start(); //startar spelare 2
        readFileAddToList(filepath);


    }

    private void readFileAddToList(Path filepath) {
        try(Scanner scan = new Scanner(filepath)) {
            scan.useDelimiter(";");
            gameTurns = new ArrayList<>();

            while(scan.hasNext()){
                String category = scan.next();
                String prompt = scan.next();
                String[] options = new String[4];
                for (int i = 0; i < 4; i++) {
                    options [i] = scan.next();
                }
                int correctAnswerIndex = scan.nextInt();
                gameTurns.add(new Question(category, prompt, options, correctAnswerIndex));
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
