package dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.SQLException;
import kurssikuulustelija.dao.ExerciseDao;
import kurssikuulustelija.domain.Exercise;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author henripal
 */
public class ExerciseDaoTest {

    static ExerciseDao exerciseDao;

    @BeforeClass
    public static void setUp() {
        exerciseDao = new ExerciseDao("fakeDb");
    }

    @Test
    public void constructorWorks() throws SQLException {
        Exercise exercise = new Exercise(1, "Tira", "Testi", "Vastaus");
        exerciseDao = new ExerciseDao();
        assertNotNull(exerciseDao);
    }

    @Test
    public void createWorks() throws SQLException {
        Exercise exercise = new Exercise(1, "Tira", "Testi", "Vastaus");
        try {
            exerciseDao.create(exercise);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        assertEquals(exercise.getAnswer(), exerciseDao.getAnswer(exercise.getId()));
    }

    @Test
    public void createAndReturnIdWorks() throws SQLException {
        exerciseDao.formatTable();
        Exercise exercise = new Exercise(1, "Tikape", "Testi", "Vastaus");
        assertEquals(1, exerciseDao.createAndReturId(exercise));
    }

    @Test
    public void getRandomWorks() throws SQLException {
        exerciseDao.formatTable();
        Exercise exercise = new Exercise(1, "Tikape", "Testi", "Vastaus");
        exerciseDao.create(exercise);
        assertEquals(exercise.getAnswer(), exerciseDao.getRandom("Tikape").getAnswer());
    }
}
