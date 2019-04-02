/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import kurssikuulustelija.domain.Exercise;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author henripal
 */
public class ExerciseTest {
    
    public ExerciseTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void creatingExerciseWorks() {
        Exercise exercise = new Exercise("Tietorakenteet ja algoritmit", "Mikä on kurssin lyhenne?", "Tira");
        assertEquals("Mikä on kurssin lyhenne?", exercise.getQuestion());
    }
}
