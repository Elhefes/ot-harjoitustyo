package kurssikuulustelija.dao;

import kurssikuulustelija.domain.User;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import kurssikuulustelija.domain.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Luokka huolehtii käyttäjien käsittelemisestä tietokannassa.
 */
@Component
public class UserDao implements Dao<User, Integer> {

    static String jdbcString = "jdbc:h2:./";
    String dbName = "kurssikuulustelija";
    String stmt = "CREATE TABLE IF NOT EXISTS User (\n"
            + "id INTEGER AUTO_INCREMENT PRIMARY KEY,\n"
            + "username varchar(100),\n"
            + "password varchar(100),\n"
            + ");";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public UserDao() {
        try {
            Connection connection = DriverManager.getConnection(jdbcString + "kurssikuulustelija", "sa", "");
            PreparedStatement statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS User (\n"
                    + "id INTEGER PRIMARY KEY,\n"
                    + "username varchar(100),\n"
                    + "password varchar(100),\n"
                    + ");");
            statement.execute();
            statement.close();
            connection.close();
        } catch (Exception e) {
        }
    }

    public UserDao(String dbName) {
        try {
            this.dbName = dbName;
            Connection connection = DriverManager.getConnection(jdbcString + dbName, "sa", "");
            PreparedStatement statement = connection.prepareStatement(stmt);
            statement.execute();
            statement.close();
            connection.close();
        } catch (Exception e) {
        }
    }

    /**
     * Metodi luo tietokantaan uuden käyttäjän.
     *
     * @param user User-olio
     */
    @Override
    public void create(User user) throws SQLException {
        Connection connection = DriverManager.getConnection(jdbcString + this.dbName, "sa", "");
        String insertStmt = "INSERT INTO User (username, password) VALUES (?, ?)";
        PreparedStatement insert = connection.prepareStatement(insertStmt);
        insert.setString(1, user.getUsername());
        insert.setString(2, user.getPassword());
        insert.execute();
        insert.close();

    }

    /**
     * Metodi saman annetun käyttäjän tietokannasta ja palauttaa sen jos
     * sellainen on siellä
     *
     * @param user Käyttäjä-olio
     * @return User-olio
     */
    public User findByUsername(User user) throws SQLException {
        int id = 0;
        String username = "";
        String password = "";
        Connection connection = DriverManager.getConnection(jdbcString + this.dbName, "sa", "");
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM User WHERE username = ?");
        stmt.setString(1, user.getUsername());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            id = rs.getInt(1);
            username = rs.getString(2);
            password = rs.getString(3);
        }
        rs.close();
        stmt.close();
        connection.close();
        if (id == 0 && username.isEmpty() && password.isEmpty()) {
            return null;
        }
        return new User(id, username, password);
    }

    /**
     * Metodi tarkastaa käyttäjätunnuksen ja salasanan etsimällä samoilla
     * tunnuksilla tietokannasta
     *
     * @param user Käyttäjä-olio
     * @return User-olio
     */
    public User checkCredentialsAndReturnUser(User user) throws SQLException {
        int id = 0;
        String username = "";
        String password = "";
        Connection connection = DriverManager.getConnection(jdbcString + this.dbName, "sa", "");
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM User WHERE username = ? AND password = ?");
        stmt.setString(1, user.getUsername());
        stmt.setString(2, user.getPassword());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            id = rs.getInt(1);
            username = rs.getString(2);
            password = rs.getString(3);
        }
        rs.close();
        stmt.close();
        connection.close();
        if (id == 0 && username.isEmpty() && password.isEmpty()) {
            return null;
        }
        return new User(id, username, password);
    }

    /**
     * Metodi palauttaa kaikki tietokannassa olevat käyttäjät listana
     *
     * @return User-lista
     */
    @Override
    public List<User> list() throws SQLException {
        List<User> list = jdbcTemplate.query("SELECT * FROM User", (rs, rowNum) -> new User(rs.getInt("id"), rs.getString("username"), rs.getString("password")));
        return list;
    }

}
