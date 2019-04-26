package kurssikuulustelija.dao;

import kurssikuulustelija.domain.User;
import java.sql.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Luokka huolehtii käyttäjien
 * käsittelemisestä tietokannassa.
 */

@Component
public class UserDao implements Dao<User, Integer> {
    
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    /**
     * Metodi luo tietokantaan uuden käyttäjän.
     * @param user User-olio
     */
    @Override
    public void create(User user) throws SQLException {
        jdbcTemplate.update("INSERT INTO User"
                + " (username, password)"
                + " VALUES (?, ?)",
                user.getUsername(),
                user.getPassword());
    }
    
    /**
     * Metodi saman annetun käyttäjän tietokannasta ja palauttaa sen
     * jos sellainen on siellä
     * @param user Käyttäjä-olio
     * @return User-olio
     */
    public User findByUsername(User user) throws SQLException {
        List<User> targets = jdbcTemplate.query("SELECT * FROM User WHERE username = ?", (rs, rowNum) -> 
                new User(rs.getInt("id"), rs.getString("username"), rs.getString("password")), user.getUsername());
        if (targets.isEmpty()) {
            return null;
        }
        return targets.get(0);
    }
    
    /**
     * Metodi tarkastaa käyttäjätunnuksen ja salasanan
     * etsimällä samoilla tunnuksilla tietokannasta
     * @param user Käyttäjä-olio
     * @return User-olio
     */
    public User checkCredentialsAndReturnUser(User user) throws SQLException {
        List<User> targets = jdbcTemplate.query("SELECT * FROM User WHERE username = ? AND password = ?", (rs, rowNum) -> 
                new User(rs.getInt("id"), rs.getString("username"), rs.getString("password")), user.getUsername(), user.getPassword());
        if (targets.isEmpty()) {
            return null;
        }
        return targets.get(0);
    }
    
    /**
     * Metodi palauttaa kaikki tietokannassa olevat
     * käyttäjät listana
     * @return User-lista
     */
    @Override
    public List<User> list() throws SQLException {
        List<User> list = jdbcTemplate.query("SELECT * FROM User", (rs, rowNum) -> new User(rs.getInt("id"), rs.getString("username"), rs.getString("password")));
        return list;
    } 
    
}
