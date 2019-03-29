/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kurssikuulustelija.dao;

import kurssikuulustelija.domain.Exercise;
import kurssikuulustelija.domain.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author henripal
 */
public class ExerciseDao implements Dao<Exercise, Integer> {
    
    public void create(Exercise question) throws SQLException {
        
    }
    
    public List<Exercise> list() {
        return null;
    }
}
