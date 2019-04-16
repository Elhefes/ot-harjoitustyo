package kurssikuulustelija.dao;

import java.sql.SQLException;
import java.util.List;
import kurssikuulustelija.domain.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**
 *
 * @author henripal
 */

@Component
public class PointDao implements Dao<Point, Integer> {
    @Autowired
    JdbcTemplate jdbcTemplate;
    
    @Override
    public void create(Point point) throws SQLException {
        jdbcTemplate.update("INSERT INTO Point"
                + " (course, userId, exerciseId)"
                + " VALUES (?, ?, ?)",
                point.getCourse(),
                point.getUserId(),
                point.getExerciseId());
    }
    
    public int getUserPoints(String course, int userId) {
        List<Integer> target = jdbcTemplate.query("SELECT DISTINCT exerciseId FROM Point WHERE course = ? AND userId = ?", (rs, rowNum) -> 
                rs.getInt("exerciseId"), course, userId);
        if (target.isEmpty()) return 0;
        return target.size();
    }
    
    public List<Point> list() {
        List<Point> list = jdbcTemplate.query("SELECT * FROM Point", (rs, rowNum) -> 
                new Point(rs.getString("course"), rs.getInt("userId"), rs.getInt("exerciseId")));
        return list;
    }
}
