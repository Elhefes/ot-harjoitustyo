package kurssikuulustelija.domain;

/**
 *
 * @author henripal
 */
public class Exercise {
    String course;
    String question;
    String answer;

    public Exercise(String course, String question, String answer) {
        this.course = course;
        this.question = question;
        this.answer = answer;
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

}
