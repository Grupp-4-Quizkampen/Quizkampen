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
    public boolean guess(int index) {
        return index == correctOptionIndex;
    }

    public String getCategory() {
        return category;
    }
}

