/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
