package Server;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by: Anton Rolin
 * Date: 17/11/2020
 * Time: 18:24
 * Project: Quizkampen
 * Copyright: MIT
 */
public class GameRound {

    List<Question> gameQuestionList = new ArrayList<>();

    GameRound(int questionsPerRound, List<Question> questionList) {
        randomiseQuestions(questionsPerRound, questionList);

    }

    private List<Question> randomiseQuestions(int questionsPerRound, List<Question> questionList) {
        if (this.gameQuestionList.size() == 0)
            Collections.copy(this.gameQuestionList, questionList);

        List<Question> roundQuestionList = new ArrayList<>();
        Collections.shuffle(this.gameQuestionList);
        Question q = this.gameQuestionList.get(0);
        roundQuestionList.add(q);
        this.gameQuestionList.remove(q);

        for (Question question : questionList) {
            if (question.getCategory().equalsIgnoreCase(q.getCategory())) {
                roundQuestionList.add(question);
                this.gameQuestionList.remove(question);
            }

            if (roundQuestionList.size() == questionsPerRound)
                break;
        }
        return roundQuestionList;
    }
}

