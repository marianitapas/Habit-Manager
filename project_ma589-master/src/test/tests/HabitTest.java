package tests;

import model.Habit;
import model.HabitGenre;
import model.HabitManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class HabitTest {
    private Habit habit1;
    private Habit habit2;
    private Habit habit3;
    private Habit habit4;
    private Habit habit5;
    private Habit habit6;
    private Habit habit7;
    private Habit habit8;
    private Habit habit9;
    private HabitManager habits;


    @BeforeEach
    public void beforeEachTest() throws IOException {
        habit1 = new Habit("Gaming", 3, "at home","Social", false);
        habit2 = new Habit("Reading", 0, "at home","WorkAndStudy", false);
        habit3 = new Habit("Meditating", 6, "at home","Health",false);
        habit4 = new Habit("Sleeping", 8, "none","Health", false);
        habit5 = new Habit("Sleeping", 8, "none","Health", false);
        habit6 = new Habit("Gaming", 3, "at home","Social", false);
        habit7 =  new Habit("Reading", 0, "at home","WorkAndStudy", false);
        habit8 =  new Habit("Studying", 0, "at home","WorkAndStudy", false);
        habit9 =  new Habit("Studying", 0, "at home","WorkAndStudy", false);

        habits = new HabitManager();
    }

    @Test
    public void testCheckIfCompletePass() {
        assertTrue(habit1.checkIfComplete(5));
        assertTrue(habit1.checkIfComplete(3));
    }

    @Test
    public void testCheckIfCompleteFail() {
        assertFalse(habit1.checkIfComplete(2));
        assertFalse(habit1.checkIfComplete(1));
    }

    @Test
    public void testGetDescription() {
        assertEquals("Gaming", habit1.getDescription());
        assertEquals("Reading", habit2.getDescription());
        assertEquals("Meditating", habit3.getDescription());
        assertEquals("Sleeping", habit4.getDescription());
    }

    @Test
    public void testGetTime() {
        assertEquals(3, habit1.getTime());
        assertEquals(0, habit2.getTime());
        assertEquals(6, habit3.getTime());
        assertEquals(8, habit4.getTime());
    }

    @Test
    public void testGetComment() {
        assertEquals("at home", habit1.getComment());
        assertEquals("at home", habit2.getComment());
        assertEquals("at home", habit3.getComment());
        assertEquals("none", habit4.getComment());
    }

    @Test
    public void testGetGenre() {
        assertEquals(HabitGenre.printHabitGenre(), HabitGenre.printHabitGenre());
        //assertEquals(null, habit1.getGenre()););

    }


    @Test
    public void testCheckIfComplete() {
        assertFalse(habit1.checkIfComplete(2));
        assertTrue(habit1.checkIfComplete(4));
        assertTrue(habit2.checkIfComplete(1));
        assertTrue(habit2.checkIfComplete(4));
        assertTrue(habit2.checkIfComplete(0));
        assertFalse(habit3.checkIfComplete(1));
        assertTrue(habit3.checkIfComplete(7));
        assertFalse(habit4.checkIfComplete(1));
        assertTrue(habit4.checkIfComplete(8));
    }

    @Test
    public void testSetUserInput() {
        habit1.setUserInput();
        assertTrue(habit1.getUserInput());
        habit2.setUserInput();
        assertTrue(habit2.getUserInput());
        habit3.setUserInput();
        assertTrue(habit3.getUserInput());
    }

    @Test
    public void testgetHabitGenre() {
        habit1.getGenre();
    }

    @Test
    public void testgetFullDescription() {
        habit1.getFullDescription();
    }


    @Test
    public void testEquals() {
        assertFalse(habit4.equals(habit3));
        assertTrue(habit4.equals(habit5));
        assertTrue(habit1.equals(habit6));
        assertTrue(habit2.equals(habit7));
        assertFalse(habit4.equals(habit3));
    }

    @Test
    public void testEqualsReturnTrue() {
        assertTrue(habit4.equals(habit5));
        assertTrue(habit1.equals(habit6));
        assertTrue(habit2.equals(habit7));
        assertTrue(habit2.equals(habit7));
        assertTrue(habit4.equals(habit5));
        assertTrue(habit1.equals(habit6));
        assertTrue(habit8.equals(habit9));

    }

    @Test
    public void testEqualsReturnFalse() {
        assertFalse(habit4.equals(habit3));
        assertFalse(habit4.equals(habit6));
        assertFalse(habit1.equals(habits));
        assertFalse(habit2.equals(habit6));
    }

    @Test
    public void testHashCode() {
        assertNotEquals(habit4.hashCode(), habit3.hashCode());
        assertEquals(habit4.hashCode(), habit5.hashCode());
        assertEquals(habit1.hashCode(), habit6.hashCode());
        assertEquals(habit2.hashCode(), habit7.hashCode());
        assertNotEquals(habit4.hashCode(), habit3.hashCode());
    }
}
