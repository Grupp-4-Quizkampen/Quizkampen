package Server;

import java.io.IOException;
import java.nio.file.Path;
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
        fileReader(filepath);


    }

    private List<Question> fileReader(Path filepath) {
        try(Scanner scan = new Scanner(filepath)) {
            scan.useDelimiter(";");


        } catch (IOException e) {
            e.printStackTrace();
        }

        return gameTurns;
    }

    private
}
