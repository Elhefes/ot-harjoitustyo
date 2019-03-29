package kurssikuulustelija.dao;

import kurssikuulustelija.domain.User;
import java.sql.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 *
 * @author henripal
 */

@Component
public class UserDao implements Dao<User, Integer> {
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public void create(User user) throws SQLException {
        jdbcTemplate.update("INSERT INTO User"
                + " (username, password)"
                + " VALUES (?, ?)",
                user.getUsername(),
                user.getPassword()); 
        
    }
    
    public User findByUsername(User user) throws SQLException {
        List<User> targets = jdbcTemplate.query("SELECT * FROM User WHERE username = ?", (rs, rowNum) -> new User(rs.getString("username"), rs.getString("password")), user.getUsername());
        if (targets.isEmpty()) return null;
        return targets.get(0);
    }
    
    @Override
    public List<User> list() throws SQLException {
        List<User> list = jdbcTemplate.query("SELECT * FROM User", (rs, rowNum) -> new User(rs.getString("username"), rs.getString("password")));
        return list;
    }
    
    
    
}
