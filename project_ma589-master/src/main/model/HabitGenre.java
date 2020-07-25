package model;

import java.util.ArrayList;

public enum HabitGenre {
    Health, SelfImprovement, WorkAndStudy, Social, Financial, Arts;


    //EFFECTS: prints all the habit categories
    public static ArrayList printHabitGenre() {

        ArrayList categories = new ArrayList();
        for (HabitGenre hg : HabitGenre.values()) {
            categories.add(hg);

        }
        return categories;
    }
}