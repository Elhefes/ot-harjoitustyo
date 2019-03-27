/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import domain.User;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;


/**
 *
 * @author henripal
 */

public class UserDao implements Dao<User, Integer> {
    
    @Autowired
    JdbcTemplate jdbcTemplate = new JdbcTemplate();
    
    public void create(User user) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:h2:./kurssikuulustelija", "sa", "");
    }
    
    public List<User> list() {
        
    }
    
}
