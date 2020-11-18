package Server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesHandler {

    int numberOfRounds;
    int numberOfQuestions;


    public PropertiesHandler() {

        Properties properties = new Properties();

        try {

            properties.load(new FileInputStream("src/Server/Rounds.properties")); //Reads key-pair from file

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        numberOfRounds = Integer.parseInt(properties.getProperty("numberOfRounds", "1"));
        numberOfQuestions = Integer.parseInt(properties.getProperty("numberOfQuestions", "1"));


    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }


}
