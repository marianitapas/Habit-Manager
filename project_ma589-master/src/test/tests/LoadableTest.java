package tests;

import model.Habit;
import model.HabitManager;
import model.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class LoadableTest {

    private HabitManager habitManager;
    private UserData userData;


    Habit habit1 = new Habit("Dancing",2, "at club","Health", false);
    Habit habit2 = new Habit("Swimming", 2, "at UBC","Health", false);
    Habit habit3 = new Habit("Go out more", 4, "to clubs","Social", false);
    Habit habit4 = new Habit("Meditating",2, "at club","Financial", true);
    Habit habit5 = new Habit("Reading", 2, "at UBC","WorkAndStudy", true);
    Habit habit6 = new Habit("Sleeping", 4, "to clubs","Health", true);



    @BeforeEach
    public void beforeEachTest() throws IOException {
        habitManager = new HabitManager();
        userData = new UserData(habitManager);
    }

    @Test
    public void load() throws IOException {
        habitManager.addHabit(habit1, "Health", "Dancing");
        habitManager.addHabit(habit3, "Social", "Go out more");
        assertTrue(habitManager.containsHabit("Dancing"));
        assertTrue(habitManager.containsHabit("Go out more"));
        userData.userHabits();
        userData.load();
    }

    @Test
    public void loadFromFile() throws IOException, URISyntaxException {
        assertFalse(habitManager.containsHabit("Dancing"));
        assertFalse(habitManager.containsHabit("Go out more"));
        userData.userHabits();
        userData.load();
    }
}