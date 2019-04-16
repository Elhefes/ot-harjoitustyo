/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kurssikuulustelija.dao;

import kurssikuulustelija.domain.Exercise;
import java.sql.SQLException;
import java.util.List;
import kurssikuulustelija.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author henripal
 */

@Component
public class ExerciseDao implements Dao<Exercise, Integer> {
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public void create(Exercise exercise) throws SQLException {
        jdbcTemplate.update("INSERT INTO Exercise"
                + " (course, question, answer)"
                + " VALUES (?, ?, ?)",
                exercise.getCourse(),
                exercise.getQuestion(),
                exercise.getAnswer()); 
    }
    
    public Exercise getRandom(String currentCourse) {
        List<Exercise> targets = jdbcTemplate.query("SELECT * FROM Exercise WHERE course = ? ORDER BY RAND() LIMIT 1", (rs, rowNum) -> 
                new Exercise(rs.getInt("id"), rs.getString("course"), rs.getString("question"), rs.getString("answer")), currentCourse);
        if (targets.isEmpty()) {
            return null;
        }
        return targets.get(0);
    }
    
    public String getAnswer(int id) {
        List<Exercise> target = jdbcTemplate.query("SELECT * FROM Exercise WHERE id = ?", (rs, rowNum) -> 
                new Exercise(rs.getInt("id"), rs.getString("course"), rs.getString("question"), rs.getString("answer")), id);
        if (target.isEmpty()) {
            return null;
        }
        return target.get(0).getAnswer();
    }
    
    public int getCourseExerciseAmount(String course) {
        List<Exercise> list = jdbcTemplate.query("SELECT * FROM Exercise WHERE course = ?", (rs, rowNum) -> 
                new Exercise(rs.getInt("id"), rs.getString("course"), rs.getString("question"), rs.getString("answer")), course);
        return list.size();
    }
    
    public List<Exercise> list() {
        List<Exercise> list = jdbcTemplate.query("SELECT * FROM Exercise", (rs, rowNum) -> 
                new Exercise(rs.getInt("id"), rs.getString("course"), rs.getString("question"), rs.getString("answer")));
        return list;
    }
}
