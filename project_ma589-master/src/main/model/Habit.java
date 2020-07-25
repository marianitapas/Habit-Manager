package model;

import java.util.ArrayList;
import java.util.Objects;

public class Habit  {

    private String description;
    protected int time;
    private String comment;
    private String categories;
    private boolean userInput;

    //EFFECTS: constructs a habit with description, time and comment
    // TODO:         if time = 0 throws TimeNotAddedException
    public Habit(String description, int time, String comment, String categories, boolean userInput) {
        this.description = description;
        this.time = time;
        this.comment = comment;
        this.userInput = userInput;
        this.categories = categories;
    }

    //getters
    public String getDescription() {
        return description;
    }

    public String getFullDescription() {
        return "description:  " + description + "  " + " time:  " + time + "  " + " comment:  " + comment;
    }

    public int getTime() {
        return time;
    }

    public String getComment() {
        return comment;
    }

    public void setUserInput() {
        userInput = true;
    }

    public boolean getUserInput() {
        return userInput;
    }

    public ArrayList getGenre() {
        return HabitGenre.printHabitGenre();
    }


    //MODIFIES:this
    //EFFECTS: returns true if given time >= to set time, otherwise
    //         returns false
    //         if habit incomplete throws HabitIncompleteException
    public boolean checkIfComplete(int time) {
        if (time >= this.time) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Habit)) {
            return false;
        }
        Habit habit = (Habit) o;
        return description.equals(habit.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

}
