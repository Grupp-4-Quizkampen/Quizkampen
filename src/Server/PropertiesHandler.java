package Server;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

public class PropertiesHandler implements Serializable {

    private int questionsPerRound;
    private int numberOfRounds;



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
        questionsPerRound = Integer.parseInt(properties.getProperty("questionsPerRound", "1"));


    }

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public int getNumberOfQuestions() {
        return questionsPerRound;
    }


}
