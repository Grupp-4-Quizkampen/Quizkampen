package Server;

public class Question2 {

    public String category;
    public String prompt;
    public String[] options;
    int correctOptionIndex;

    public Question2(String category, String prompt, String[] options, int correctOptionIndex) {
        this.prompt = prompt;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }
    public boolean guess(int index) {
        return index == correctOptionIndex;
    }
}

