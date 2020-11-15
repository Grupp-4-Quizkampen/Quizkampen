package Server;

public class Question2 {

    public String title;
    public String[] options;
    int correctOptionIndex;

    public Question2(String title, String[] options, int correctOptionIndex) {
        this.title = title;
        this.options = options;
        this.correctOptionIndex = correctOptionIndex;
    }
    public boolean guess(int index) {
        return index == correctOptionIndex;
    }
}

