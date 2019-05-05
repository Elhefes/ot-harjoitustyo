package kurssikuulustelija.domain;

/**
 *
 * @author henripal
 */
public class Exercise {

    int id;
    String course;
    String question;
    String answer;

    public Exercise(int id, String course, String question, String answer) {
        this.id = id;
        this.course = course;
        this.question = question;
        this.answer = answer;
    }

    public int getId() {
        return this.id;
    }

    public String getCourse() {
        return this.course;
    }

    public String getQuestion() {
        return this.question;
    }

    public String getAnswer() {
        return this.answer;
    }

    public String toString() {
        return "#" + this.id + " | Course: " + this.course + " | Question: " + this.question + " | Answer: " + this.answer;
    }

}
