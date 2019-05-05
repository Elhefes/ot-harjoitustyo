package kurssikuulustelija.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import kurssikuulustelija.domain.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 * Luokka huolehtii pisteiden käsittelemisestä tietokannassa.
 */
@Component
public class PointDao implements Dao<Point, Integer> {

    static String jdbcString = "jdbc:h2:./";
    String dbName = "kurssikuulustelija";
    String stmnt = "CREATE TABLE IF NOT EXISTS Point (\n"
            + "id INTEGER AUTO_INCREMENT PRIMARY KEY,\n"
            + "course varchar(100),\n"
            + "userId INTEGER,\n"
            + "exerciseId INTEGER,\n"
            + ");";

    @Autowired
    JdbcTemplate jdbcTemplate;

    public PointDao() {
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

    public PointDao(String dbName) {
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
     * Metodi poistaa ja luo uuden Point-taulun.
     *
     * @param point Point-olio
     */
    public void formatTable() {
        try {
            Connection connection = DriverManager.getConnection(jdbcString + this.dbName, "sa", "");
            PreparedStatement delete = connection.prepareStatement("DROP TABLE Point IF EXISTS;");
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
     * Metodi luo tietokantaan uuden pisteen käyttäjälle.
     *
     * @param point Point-olio
     */
    @Override
    public void create(Point point) throws SQLException {
        Connection connection = DriverManager.getConnection(jdbcString + this.dbName, "sa", "");
        String insertStmt = "INSERT INTO Point (course, userId, exerciseId) VALUES (?, ?, ?)";
        PreparedStatement insert = connection.prepareStatement(insertStmt);
        insert.setString(1, point.getCourse());
        insert.setInt(2, point.getUserId());
        insert.setInt(3, point.getExerciseId());
        insert.execute();
        insert.close();

    }

    /**
     * Metodi poistaa kaikki saadut pisteet tehtävästä
     *
     * @param String kysymys
     */
    public void deleteExercisePoints(int exerciseId) throws SQLException {
        Connection connection = DriverManager.getConnection(jdbcString + this.dbName, "sa", "");
        String statement = "DELETE FROM Point WHERE exerciseId = ?";
        PreparedStatement stmt = connection.prepareStatement(statement);
        stmt.setInt(1, exerciseId);
        stmt.execute();
        stmt.close();
        connection.close();
    }

    /**
     * Metodi tarkastaa, kuinka monta tehtävää käyttäjä on suorittanut
     * kurssista.
     *
     * @param course kurssin nimi
     * @param userId kurssin id
     * @return käyttäjän pisteet
     */
    public int getUserPoints(String course, int userId) throws SQLException {
        int points = 0;
        Connection connection = DriverManager.getConnection(jdbcString + this.dbName, "sa", "");
        String statement = "SELECT COUNT(DISTINCT exerciseId) FROM Point WHERE course = ? AND userId = ?";
        PreparedStatement stmt = connection.prepareStatement(statement);
        stmt.setString(1, course);
        stmt.setInt(2, userId);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            points = rs.getInt(1);
        }
        rs.close();
        stmt.close();
        connection.close();
        return points;

    }

    /**
     * Metodi palauttaa kaikki tietokannassa olevat pisteet listana.
     *
     * @return Point-lista
     */
    public List<Point> list() {
        List<Point> list = jdbcTemplate.query("SELECT * FROM Point", (rs, rowNum)
            -> new Point(rs.getString("course"), rs.getInt("userId"), rs.getInt("exerciseId")));
        return list;
    }
}
