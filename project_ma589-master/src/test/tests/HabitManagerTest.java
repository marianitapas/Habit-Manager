package tests;

import model.Habit;
import model.HabitManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class HabitManagerTest {

    private HabitManager habitManager;
    private ArrayList<Habit> habits;


    Habit habit1 = new Habit("Dancing",2, "at club","Health", false);
    Habit habit2 = new Habit("Swimming", 2, "at UBC","Health", false);
    Habit habit3 = new Habit("Go out more", 4, "to clubs","Social", false);
    Habit habit4 = new Habit("Earning",2, "at club","Financial", true);
    Habit habit5 = new Habit("Drawing", 2, "at UBC","Arts", true);
    Habit habit6 = new Habit("Sleeping", 4, "to clubs","Health", true);
    Habit habit7 = new Habit("Studying", 4, "at UBC","WorkAndStudy", true);
    Habit habit8 = new Habit("Procrastination", 4, "at UBC","SelfImprovement", true);
    Habit habit9 = new Habit("Listening to music", 4, "at UBC","Arts", true);



    public HabitManagerTest() {
    }

    @BeforeEach
    public void beforeEachTest() throws IOException {
        habitManager = new HabitManager();
        habits = new ArrayList<>();
    }

    @Test
    void testAddOne()  {
        habitManager.addHabit(habit1, "Health", "Dancing");
        assertTrue(habitManager.containsHabit("Dancing"));
        assertEquals(6, habitManager.getHashMap().size());
        assertEquals(3, habitManager.size("Health"));
    }

    @Test
    void testAddOnetoDifferentCategory()  {
        habitManager.addHabit(habit3, "Social", "Go out more");
        assertTrue(habitManager.containsHabit("Go out more"));
        assertEquals(6, habitManager.getHashMap().size());
        assertEquals(3, habitManager.size("Social"));
    }


    @Test
    void testAddOneDuplicate() {
        assertEquals(2, habitManager.size("Social"));
        habitManager.addHabit(habit1, "Social", "Dancing");
        habitManager.addHabit(habit1, "Social", "Dancing");
        assertTrue(habitManager.containsHabit("Dancing"));
        assertFalse(habitManager.containsHabit("Gaming"));
        assertEquals(3, habitManager.size("Social"));

    }

    @Test
    void testAddTwo() {
        habitManager.addHabit(habit1, "Health", "Dancing");
        habitManager.addHabit(habit2, "Health", habit2.getDescription());
        assertTrue(habitManager.containsHabit(habit1.getDescription()));
        assertTrue(habitManager.containsHabit(habit2.getDescription()));
        assertFalse(habitManager.containsHabit(habit3.getDescription()));
        assertEquals(4, habitManager.size("Health"));
    }


    @Test
    void testRemoveOne() {
        habitManager.addHabit(habit1, "Health", "Dancing");
        habitManager.addHabit(habit2, "Health", habit2.getDescription());
        habitManager.removeHabit(habit2.getDescription());
        assertTrue(habitManager.containsHabit(habit1.getDescription()));
        assertFalse(habitManager.containsHabit(habit2.getDescription()));
        assertFalse(habitManager.containsHabit(habit3.getDescription()));
        assertEquals(3, habitManager.size("Health"));
    }

    @Test
    void testRemoveTwo() {
        habitManager.addHabit(habit1, "Health", "Dancing");
        habitManager.addHabit(habit2, "Health", habit2.getDescription());
        habitManager.removeHabit(habit1.getDescription());
        assertFalse(habitManager.containsHabit(habit1.getDescription()));
        assertTrue(habitManager.containsHabit(habit2.getDescription()));
        assertFalse(habitManager.containsHabit(habit3.getDescription()));
        habitManager.removeHabit(habit2.getDescription());
        assertFalse(habitManager.containsHabit(habit2.getDescription()));
        assertEquals(2, habitManager.size("Health"));
    }

    @Test
    public void testSize() {
        assertEquals(2, habitManager.size("Health"));
        assertEquals(2, habitManager.size("Social"));
        assertEquals(2, habitManager.size("Arts"));
        assertEquals(2, habitManager.size("Financial"));
        assertEquals(0, habitManager.size("health"));
    }

    @Test
    public void testContainsOne() {
        assertFalse(habitManager.containsHabit(habit1.getDescription()));
        habitManager.addHabit(habit2, "Health", habit2.getDescription());
        assertTrue(habitManager.containsHabit(habit2.getDescription()));
        assertEquals(3, habitManager.size("Health"));
    }

    @Test
    public void testContainsZero() throws IOException {
        HabitManager  newManager = new HabitManager();
        assertFalse(newManager.containsHabit(""));
        habitManager.addHabit(habit2, "Health", habit2.getDescription());
        assertTrue(habitManager.containsHabit(habit2.getDescription()));
    }

    @Test
    public void testAddHealth() {
        habitManager.addHabit(habit1, "Health", "Dancing");
        assertTrue(habitManager.containsHabit("Dancing"));
        assertEquals(6, habitManager.getHashMap().size());
        assertEquals(3, habitManager.size("Health"));
        habitManager.addHealthHabits(habit6.getDescription(),habit6);
        assertTrue(habitManager.containsHabit(habit1.getDescription()));
    }

    @Test
    public void testAddArts() {
        habitManager.addHabit(habit5, "Arts", "Drawing");
        assertTrue(habitManager.containsHabit("Drawing"));
        assertEquals(6, habitManager.getHashMap().size());
        assertEquals(2, habitManager.size("Arts"));
        habitManager.addArtsHabits(habit5.getDescription(),habit5);
        assertTrue(habitManager.containsHabit(habit5.getDescription()));
        assertEquals(2, habitManager.size("Arts"));
    }

    @Test
    public void testAddArtsTwo() {
        habitManager.addHabit(habit5, "Arts", "Drawing");
        assertTrue(habitManager.containsHabit("Drawing"));
        assertEquals(6, habitManager.getHashMap().size());
        assertEquals(2, habitManager.size("Arts"));

        habitManager.addHabit(habit9, "Arts", "Listening to music");
        habitManager.addArtsHabits(habit9.getDescription(),habit9);
        assertTrue(habitManager.containsHabit(habit9.getDescription()));
        assertEquals(3, habitManager.size("Arts"));
    }

    @Test
    public void testAddSocial() {
        habitManager.addHabit(habit3, "Social", habit3.getDescription());
        assertTrue(habitManager.containsHabit(habit3.getDescription()));
        assertEquals(6, habitManager.getHashMap().size());
        assertEquals(3, habitManager.size("Social"));
        habitManager.addSocialHabits(habit3.getDescription(),habit3);
        assertTrue(habitManager.containsHabit(habit3.getDescription()));
        assertEquals(3, habitManager.size("Social"));
    }

    @Test
    public void testAddFinancial() {
        habitManager.addHabit(habit4, "Financial", habit4.getDescription());
        assertTrue(habitManager.containsHabit(habit4.getDescription()));
        assertEquals(6, habitManager.getHashMap().size());
        assertEquals(3, habitManager.size("Financial"));
        habitManager.addFinancialHabits(habit4.getDescription(),habit4);
        assertTrue(habitManager.containsHabit(habit4.getDescription()));
        assertEquals(3, habitManager.size("Financial"));
    }

    @Test
    public void testAddWorkStudy() {
        habitManager.addHabit(habit4, "Financial", habit4.getDescription());
        assertTrue(habitManager.containsHabit(habit4.getDescription()));
        assertEquals(6, habitManager.getHashMap().size());
        assertEquals(3, habitManager.size("Financial"));
        habitManager.addFinancialHabits(habit4.getDescription(),habit4);
        assertTrue(habitManager.containsHabit(habit4.getDescription()));
        assertEquals(3, habitManager.size("Financial"));

        habitManager.addHabit(habit7, "WorkAndStudy", habit7.getDescription());
        assertTrue(habitManager.containsHabit(habit7.getDescription()));
        assertEquals(6, habitManager.getHashMap().size());
        assertEquals(3, habitManager.size("WorkAndStudy"));
        habitManager.addWorkAndStudyHabits(habit7.getDescription(),habit7);
        assertTrue(habitManager.containsHabit(habit7.getDescription()));
        assertEquals(3, habitManager.size("WorkAndStudy"));
    }

    @Test
    public void testAddWorkStudyNone() {

        habitManager.addHabit(habit7, "Study", habit7.getDescription());
        assertFalse(habitManager.containsHabit(habit7.getDescription()));
        assertEquals(6, habitManager.getHashMap().size());
        assertEquals(2, habitManager.size("WorkAndStudy"));
    }

    @Test
    public void testAddSelfImprovement() {
        habitManager.addHabit(habit8, "SelfImprovement", habit8.getDescription());
        assertTrue(habitManager.containsHabit(habit8.getDescription()));
        assertEquals(6, habitManager.getHashMap().size());
        assertEquals(3, habitManager.size("SelfImprovement"));
        habitManager.addSelfImprovementHabits(habit8.getDescription(),habit8);
        assertTrue(habitManager.containsHabit(habit8.getDescription()));
        assertEquals(3, habitManager.size("SelfImprovement"));
    }

    @Test
    public void testUserHabitCheckTime() {
        habitManager.addHabit(habit8, "SelfImprovement", habit8.getDescription());
        assertTrue(habitManager.containsHabit(habit8.getDescription()));
        assertEquals(6, habitManager.getHashMap().size());
        assertEquals(3, habitManager.size("SelfImprovement"));
        assertTrue(habitManager.userHabitCheckCompletion(habit8.getDescription(),5));
        assertTrue(habitManager.containsHabit(habit8.getDescription()));
        assertFalse(habitManager.userHabitCheckCompletion(habit8.getDescription(),0));

    }

    @Test
    public void testUserHabitCheckTimeFalse() {
        habitManager.addHabit(habit1, "Health", habit8.getDescription());
        assertTrue(habitManager.containsHabit(habit1.getDescription()));
        assertFalse(habitManager.containsHabit(habit3.getDescription()));
        assertFalse(habitManager.containsHabit(habit4.getDescription()));
        assertEquals(2, habitManager.size("SelfImprovement"));
        habitManager.addHabit(habit8, "SelfImprovement", habit8.getDescription());
        assertTrue(habitManager.containsHabit(habit8.getDescription()));
        assertEquals(6, habitManager.getHashMap().size());
        assertEquals(3, habitManager.size("SelfImprovement"));
        assertTrue(habitManager.userHabitCheckCompletion(habit8.getDescription(),5));
        assertTrue(habitManager.containsHabit(habit8.getDescription()));
        assertFalse(habitManager.userHabitCheckCompletion(habit8.getDescription(),0));

    }


}


