package Server;

public class Question {

    public String category;
    public String prompt;
    public String[] options;
    int correctOptionIndex;

    public Question(String category, String prompt, String[] options, int correctOptionIndex) {
        this.prompt = prompt;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }
    public boolean guess(int index) {
        return index == correctOptionIndex;
    }
}

