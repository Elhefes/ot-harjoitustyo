package kurssikuulustelija.domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author henripal
 */
public class Point {

    String course;
    int userId;
    int exerciseId;

    public Point(String course, int userId, int exerciseId) {
        this.course = course;
        this.userId = userId;
        this.exerciseId = exerciseId;
    }

    public String getCourse() {
        return this.course;
    }

    public int getUserId() {
        return this.userId;
    }

    public int getExerciseId() {
        return this.exerciseId;
    }

    public String toString() {
        return this.course + " " + this.userId + " " + this.exerciseId;
    }
}
