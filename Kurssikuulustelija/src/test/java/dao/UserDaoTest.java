package dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.SQLException;
import kurssikuulustelija.dao.UserDao;
import kurssikuulustelija.domain.User;
import static org.junit.Assert.assertEquals;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author henripal
 */
public class UserDaoTest {

    static UserDao userDao;

    @BeforeClass
    public static void setUp() {
        userDao = new UserDao("fakeDb");
    }

    @Test
    public void createWorks() throws SQLException {
        User user = new User(1, "Chuck", "Norris");
        try {
            userDao.create(user);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        assertEquals(user.getUsername(), userDao.findByUsername(user).getUsername());
    }

    @Test
    public void findByUsernameWorkingCorrectly() throws SQLException {
        User user = new User(1, "Chuck", "Norris");
        try {
            userDao.create(user);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        assertEquals(null, userDao.findByUsername(new User(1, "Dan", "Norris")));
    }

    @Test
    public void checkCorrectCredentialsWorks() throws SQLException {
        User user = new User(1, "Anonymous", "Duck");
        try {
            userDao.create(user);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        assertEquals(user.getPassword(), userDao.checkCredentialsAndReturnUser(user).getPassword());
    }

    @Test
    public void checkIncorrectCredentialsWorks() throws SQLException {
        User user = new User(1, "Pizza", "Guy");
        try {
            userDao.create(user);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        assertEquals(null, userDao.checkCredentialsAndReturnUser(new User(4, "Test", "Dude")));
    }

}
