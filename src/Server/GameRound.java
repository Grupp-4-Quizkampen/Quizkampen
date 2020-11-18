package Server;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class GameRound implements Serializable {

    List<Question> roundQuestionList = new ArrayList<>();

    GameRound(List<Question> questionList) {
        roundQuestionList = questionList;
        System.out.println(roundQuestionList.get(0).getCategory());
    }

    public List<Question> getRoundQuestionList() {
        return roundQuestionList;
    }
}

