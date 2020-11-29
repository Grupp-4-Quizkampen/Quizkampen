package Server;

import java.io.Serializable;

public class Question implements Serializable {

    public String category;
    public String prompt;
    public String[] options;
    int correctOptionIndex;

    public Question(String category, String prompt, String[] options, int correctOptionIndex) {
        this.category = category;
        this.prompt = prompt;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }
    public boolean isCorrectAnswer(int index) {
        return index == correctOptionIndex;
    }

    public String getCategory() {
        return category;
    }

    public String getPrompt() {
        return prompt;
    }

    public String[] getOptions() {
        return options;
    }

    public int getCorrectOptionIndex() {
        return correctOptionIndex;
    }
}

