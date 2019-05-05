/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.SQLException;
import kurssikuulustelija.dao.PointDao;
import kurssikuulustelija.domain.Point;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author henripal
 */
public class PointDaoTest {

    static PointDao pointDao;

    @BeforeClass
    public static void setUp() {
        pointDao = new PointDao("fakeDb");
    }

    @Test
    public void createWorks() throws SQLException {
        pointDao.formatTable();
        try {
            pointDao.create(new Point("Tira", 3, 1));
            pointDao.create(new Point("Tira", 3, 2));
            pointDao.create(new Point("Tira", 3, 3));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        assertEquals(3, pointDao.getUserPoints("Tira", 3));
    }
}
