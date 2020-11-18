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

    List<Question> questionList = new ArrayList();

    GameRound(int questionsPerRound, List<Question> questionList){
        randomiseQuestions(questionsPerRound, questionList);

    }

    private List<Question> randomiseQuestions(int questionsPerRound, List<Question> questionList){
        Collections.copy(this.questionList, questionList);
        List<Question> roundQuestionList = new ArrayList();

        Collections.shuffle(questionList);
        Question q = questionList.get(0);
        roundQuestionList.add(q);
        this.questionList.remove(q);

        int i = 1;
        for (Question question: questionList) {
            if(question.getCategory().equalsIgnoreCase(q.getCategory())) {
                roundQuestionList.add(question);
                this.questionList.remove(question);
                i++;
            }

            if(i == questionsPerRound)
                break;
        }
        return roundQuestionList;
    }
}
