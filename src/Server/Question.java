package Server;

/**
 * Created by: Anton Rolin
 * Date: 12/11/2020
 * Time: 14:10
 * Project: Quizkampen
 * Copyright: MIT
 */
public class Question {
    String category;
    String question;
    String answer1;
    String answer2;
    String answer3;
    String answer4;
    String correctAnswer;

    Question(String category, String question, String answer1, String answer2, String answer3, String answer4, String correctAnswer){
        this.category = category;
        this.question = question;
        this.answer1 = answer1;
        this.answer2 = answer2;
        this.answer3 = answer3;
        this.answer4 = answer4;
        this.correctAnswer = correctAnswer;
    }
}
