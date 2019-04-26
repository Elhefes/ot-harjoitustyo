package dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.SQLException;
import java.util.List;
import kurssikuulustelija.dao.UserDao;
import kurssikuulustelija.domain.User;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 *
 * @author henripal
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public class UserDaoTest {
//    
//    @Autowired
//    UserDao userDao;
//    
//    @Autowired
//    JdbcTemplate jdbcTemplate;
////    
////    @Before
////    public void setUp() {
////        
////    }
//    
//    @Test
//    public void createWorks() {
//        //Assert.assertNotNull(userDao);
//        User user = new User(1, "Chuck", "Norris");
//        try {
//            userDao.create(user);
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        }
//        List<User> target = jdbcTemplate.query("SELECT * FROM User WHERE username = Chuck AND password = Norris", (rs, rowNum) -> 
//                new User(0, rs.getString("username"), rs.getString("password")));
//        assertEquals("Chuck", target.get(0).getUsername());
//        assertEquals("Norris", target.get(0).getPassword());
//    }
//    
}
