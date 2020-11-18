package Server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Cretated by Madeleine Lennhamn
 * Date: 2020-11-17
 * Time: 19:16
 * Project: $ {PROJECT_NAME}
 * Copyright: MIT
 */
public class PropertiesHandler {

    int numOfRounds;
    int numOfQuestions;


    public PropertiesHandler() {

        Properties p = new Properties();

        try {

            p.load(new FileInputStream("src/Server/Rounds.properties")); //Reads key-pair from file

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        numOfRounds = Integer.parseInt(p.getProperty("numOfRounds", "1"));
        numOfQuestions = Integer.parseInt(p.getProperty("numOfQuestions", "1"));


    }

    public int getNumOfRounds() {
        return numOfRounds;
    }

    public int getNumOfQuestions() {
        return numOfQuestions;
    }


}
