/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kurssikuulustelija.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import kurssikuulustelija.domain.Exercise;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Luokka huolehtii tehtävien käsittelemisestä tietokannassa.
 */
@Component
public class ExerciseDao implements Dao<Exercise, Integer> {

    static String jdbcString = "jdbc:h2:./";
    String dbName = "kurssikuulustelija";
    String stmnt = "CREATE TABLE IF NOT EXISTS Exercise (\n"
            + "id INTEGER AUTO_INCREMENT PRIMARY KEY,\n"
            + "course varchar(100),\n"
            + "question varchar(100),\n"
            + "answer varchar(100),\n"
            + ");";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    PointDao pointDao;

    public ExerciseDao() {
        try {
            Connection connection = DriverManager.getConnection(jdbcString + "kurssikuulustelija", "sa", "");
            PreparedStatement statement = connection.prepareStatement(stmnt);
            statement.execute();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public ExerciseDao(String dbName) {
        try {
            this.dbName = dbName;
            Connection connection = DriverManager.getConnection(jdbcString + dbName, "sa", "");
            PreparedStatement statement = connection.prepareStatement(stmnt);
            statement.execute();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodi poistaa ja luo uuden Exercise-taulun.
     *
     * @param point Point-olio
     */
    public void formatTable() {
        try {
            Connection connection = DriverManager.getConnection(jdbcString + this.dbName, "sa", "");
            PreparedStatement delete = connection.prepareStatement("DROP TABLE Exercise IF EXISTS;");
            delete.execute();
            PreparedStatement statement = connection.prepareStatement(stmnt);
            statement.execute();
            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Metodi luo tietokantaan uuden tehtävän.
     *
     * @param exercise Exercise-olio
     */
    @Override
    public void create(Exercise exercise) throws SQLException {
        Connection connection = DriverManager.getConnection(jdbcString + this.dbName, "sa", "");
        String insertStmt = "INSERT INTO Exercise (course, question, answer) VALUES (?, ?, ?)";
        PreparedStatement insert = connection.prepareStatement(insertStmt);
        insert.setString(1, exercise.getCourse());
        insert.setString(2, exercise.getQuestion());
        insert.setString(3, exercise.getAnswer());
        insert.execute();
        insert.close();

    }

    /**
     * Metodi luo tietokantaan uuden tehtävän. ja palauttaa tehtävän id:n
     *
     * @param exercise Exercise-olio
     * @return int id
     */
    public int createAndReturId(Exercise exercise) throws SQLException {
        int id = 0;
        Connection connection = DriverManager.getConnection(jdbcString + this.dbName, "sa", "");
        String insertStmt = "INSERT INTO Exercise (course, question, answer) VALUES (?, ?, ?)";
        PreparedStatement insert = connection.prepareStatement(insertStmt);
        insert.setString(1, exercise.getCourse());
        insert.setString(2, exercise.getQuestion());
        insert.setString(3, exercise.getAnswer());
        insert.execute();
        insert.close();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Exercise WHERE question = ?");
        stmt.setString(1, exercise.getQuestion());
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            id = rs.getInt(1);
        }
        rs.close();
        stmt.close();
        connection.close();
        return id;
    }

    /**
     * Metodi poistaa tehtävän tietokannasta
     *
     * @param question tehtävän kysymys
     */
    public void deleteExercise(String question) throws SQLException {
        Connection connection = DriverManager.getConnection(jdbcString + this.dbName, "sa", "");
        String statement = "DELETE FROM Exercise WHERE question = ?";
        PreparedStatement stmt = connection.prepareStatement(statement);
        stmt.setString(1, question);
        stmt.execute();
        stmt.close();
        connection.close();
    }

    /**
     * Metodi hakee tietokannasta satunnaisen tehtävän. ja palauttaa sen
     *
     * @param currentCourse kurssin nimi
     * @return Exercise-olio
     */
    public Exercise getRandom(String currentCourse) throws SQLException {
        int id = 0;
        String course = "";
        String question = "";
        String answer = "";
        Connection connection = DriverManager.getConnection(jdbcString + this.dbName, "sa", "");
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Exercise WHERE course = ? ORDER BY RAND() LIMIT 1");
        stmt.setString(1, currentCourse);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            id = rs.getInt(1);
            course = rs.getString(2);
            question = rs.getString(3);
            answer = rs.getString(4);
        }
        rs.close();
        stmt.close();
        connection.close();
        return new Exercise(id, course, question, answer);
    }

    /**
     * Metodi hakee tietokannasta satunnaisen tehtävän, jota käyttäjä ei ole
     * vielä ratkaissut
     *
     * @param currentCourse kurssin nimi
     * @param userId käyttäjän id
     * @return Exercise-olio
     */
    public Exercise getUniqueRandomExercise(String currentCourse, int userId) throws SQLException {
        Exercise random = null;
        int id = 0;
        String course = "";
        String question = "";
        String answer = "";
        Connection connection = DriverManager.getConnection(jdbcString + this.dbName, "sa", "");
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Exercise JOIN Point ON Exercise.id = Point.exerciseId WHERE NOT userId = ? ORDER BY RAND() LIMIT 1");
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            id = rs.getInt(1);
            course = rs.getString(2);
            question = rs.getString(3);
            answer = rs.getString(4);
        }
        rs.close();
        stmt.close();
        connection.close();
        return new Exercise(id, course, question, answer);
    }

    /**
     * Metodi hakee tehtävän oikean vastauksen.
     *
     * @param id tehtävän numero
     * @return tehtävän vastaus
     */
    public String getAnswer(int id) throws SQLException {
        String answer = "";
        Connection connection = DriverManager.getConnection(jdbcString + this.dbName, "sa", "");
        String statement = "SELECT * FROM Exercise WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(statement);
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            answer = rs.getString(4);
        }
        rs.close();
        stmt.close();
        connection.close();
        return answer;

    }

    /**
     * Metodi laskee tehtävien määrän tietokannassa
     *
     * @param course kurssin nimi
     * @return tehtävien määrä.
     */
    public int getCourseExerciseAmount(String course) {
        List<Exercise> list = jdbcTemplate.query("SELECT * FROM Exercise WHERE course = ?", (rs, rowNum)
            -> new Exercise(rs.getInt("id"), rs.getString("course"), rs.getString("question"), rs.getString("answer")), course);
        return list.size();
    }

    /**
     * Metodi palauttaa kaikki tietokannassa olevat tehtävät listana.
     *
     * @return Exercise-lista
     */
    public List<Exercise> list() {
        List<Exercise> list = jdbcTemplate.query("SELECT * FROM Exercise", (rs, rowNum)
            -> new Exercise(rs.getInt("id"), rs.getString("course"), rs.getString("question"), rs.getString("answer")));
        return list;
    }
}
