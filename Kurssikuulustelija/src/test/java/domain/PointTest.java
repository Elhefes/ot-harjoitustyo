package domain;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import kurssikuulustelija.domain.Point;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author henripal
 */
public class PointTest {
    
    @Test
    public void creatingExerciseWorks() {
        Point point = new Point("Tietorakenteet ja algoritmit", 1, 2);
        assertEquals("Tietorakenteet ja algoritmit", point.getCourse());
        assertEquals(1, point.getUserId());
        assertEquals(2, point.getExerciseId());
        assertEquals("Tietorakenteet ja algoritmit 1 2", point.toString());
    }
}
