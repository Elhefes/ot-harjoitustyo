/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import kurssikuulustelija.domain.User;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author henripal
 */
public class UserTest {
    
    @Test
    public void creatingUserWorks() {
        User user = new User(1, "testuser", "testpass");
        assertEquals(1, user.getId());
        assertEquals("testuser", user.getUsername());
        assertEquals("testpass", user.getPassword());
        assertEquals("#1 Username: testuser Password: testpass", user.toString());
    }
    
}
