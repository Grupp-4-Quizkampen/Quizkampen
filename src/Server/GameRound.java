package Server;

import java.util.ArrayList;
import java.util.List;


public class GameRound {

    List<Question> roundQuestionList = new ArrayList<>();

    GameRound(List<Question> questionList) {

    }

    public List<Question> getRoundQuestionList() {
        return roundQuestionList;
    }
}

