package ui;

import model.Habit;
import model.HabitGenre;
import model.HabitManager;
import model.UserData;

import java.io.IOException;
import java.util.ArrayList;

public class InfoManager {

    private HabitManager habits;
    private UserData userData;
    private boolean runProgram;

    public InfoManager(HabitManager habits, UserData data) throws IOException {

        this.habits = habits;
        this.runProgram = true;
        this.userData = data;
    }


    //EFFECTS: prints the habit categories in a list.
    public ArrayList printCategories() throws IOException {
        //System.out.println("Pick the category you are interested in");
        return HabitGenre.printHabitGenre();

    }

    //MODIFIES: this
    //EFFECTS:  adds the habit that the user has created and save it
    public void questionsAddHabit(Habit habit, int time, String comment,
                                  Object category, String description) throws IOException {
        habits.addHabit(habit, category.toString(), description);
        userData.save();
    }

    //MODIFIES: this
    //EFFECTS: prints the habit categories and the list of habits.
    public ArrayList printCategoriesAndLists(String category) {
        ArrayList categoryAndList = new ArrayList();
        for (HabitGenre entry : habits.getHashMap().keySet()) {
            if (category.equals(entry.toString())) {
                ArrayList<Habit> list = habits.getHashMap().get(entry);
                String comment = "Category: " + entry + "\n"  + "List of Habits:" + "\n";
                categoryAndList.add(0, comment);

                for (Habit h : list) {
                    String description = h.getDescription();
                    categoryAndList.add(description);
                }
                String question = "\n"  +  " Which habit would you like to add?";
                categoryAndList.add(categoryAndList.size(), question);
            }
        }
        return  categoryAndList;
    }

    //EFFECTS: removes the habit by description and returns the remaining list
    public HabitManager instructionsRemoveHabit(String description) throws IOException {
        habits.removeHabit(description);
        //userData.save();
        return habits;
    }

}
