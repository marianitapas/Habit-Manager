package model;

import java.util.ArrayList;
import java.util.HashMap;

public class HabitManager {


    private HashMap<HabitGenre, ArrayList<Habit>> listHabit;

    private ArrayList<Habit> healthHabits;
    private ArrayList<Habit> selfImprovementHabits;
    private ArrayList<Habit> workStudyHabits;
    private ArrayList<Habit> socialHabits;
    private ArrayList<Habit> financialHabits;
    private ArrayList<Habit> artsHabits;



    //EFFECTS: creates a HabitManager with lists a habits and a map
    public HabitManager() {
        listHabit = new HashMap<>();
        healthHabits = new ArrayList<>();
        workStudyHabits = new ArrayList<>();
        socialHabits = new ArrayList<>();
        financialHabits = new ArrayList<>();
        selfImprovementHabits = new ArrayList<>();
        artsHabits = new ArrayList<>();
        initializingHabitManager();
    }


    //EFFECTS: returns the HashMap containing a key, the habit's category (HabitGenre) and
    //         a list of habits associated with that category.
    public HashMap<HabitGenre, ArrayList<Habit>> getHashMap() {
        return listHabit;
    }

    //MODIFIES:this
    //EFFECTS: initializes the HabitManager with  list of habits and categories;
    public void initializingHabitManager() {
        //healthHabits.add(new Habit("Sleep", 2, "none",false));
        //healthHabits.add(new Habit("Take Sufficient Rest", 2, "none", false));
        healthHabits.add(new Habit("Exercise", 2, "none", HabitGenre.Health.toString(), false));
        healthHabits.add(new Habit("Yoga", 2, "none", HabitGenre.Health.toString(), false));
        //healthHabits.add(new Habit("Running", 2, "none", false));
        //healthHabits.add(new Habit("Medication", 2, "none", false));
        //healthHabits.add(new Habit("Sport", 2, "none", false));
        //healthHabits.add(new Habit("Coffee consumption", 2, "none", false));

        //healthHabits.add(new Habit("PersonalHygiene", 2, "none", false));

        selfImprovementHabits.add(new Habit("Meditation", 2, "none", HabitGenre.SelfImprovement.toString(), false));
        //selfImprovementHabits.add(new Habit("Procrastination", 2, "none", false));
        selfImprovementHabits.add(new Habit("Tidiness", 2, "none", HabitGenre.SelfImprovement.toString(), false));
        //selfImprovementHabits.add(new Habit("Time Management", 2, "none", false));

        workStudyHabits.add(new Habit("Study", 2, "none", HabitGenre.WorkAndStudy.toString(), false));
        //workStudyHabits.add(new Habit("Reading", 2, "none", false));
        workStudyHabits.add(new Habit("Work", 2, "none", HabitGenre.WorkAndStudy.toString(), false));
        // workStudyHabits.add(new Habit("Writing", 2, "none", false));
        //workStudyHabits.add(new Habit("Languages", 2, "none", false));

        socialHabits.add(new Habit("Family", 2, "none", HabitGenre.Social.toString(), false));
        socialHabits.add(new Habit("Friends", 2, "none", HabitGenre.Social.toString(), false));
        //socialHabits.add(new Habit("Partner", 2, "none", false));

        //financialHabits.add(new Habit("Saving", 2, "none", false));
        financialHabits.add(new Habit("Spending", 2, "none", HabitGenre.Financial.toString(), false));
        //financialHabits.add(new Habit("Earning", 2, "none", false));
        financialHabits.add(new Habit("Budgeting", 2, "none", HabitGenre.Financial.toString(), false));

        artsHabits.add(new Habit("Playing an Instrument", 2, "none", HabitGenre.Arts.toString(), false));
        artsHabits.add(new Habit("Drawing", 2, "none", HabitGenre.Arts.toString(), false));
        //artsHabits.add(new Habit("Listening to music", 2, "none", false));
        listHabit.put(HabitGenre.Health, healthHabits);
        listHabit.put(HabitGenre.Arts, artsHabits);
        listHabit.put(HabitGenre.Financial, financialHabits);
        listHabit.put(HabitGenre.SelfImprovement, selfImprovementHabits);
        listHabit.put(HabitGenre.Social, socialHabits);
        listHabit.put(HabitGenre.WorkAndStudy, selfImprovementHabits);



    }

    //REQUIRES: the map should not be empty
    //MODIFIES:this
    //EFFECTS:adds the habit to the map with its corresponding key
    public void addHabit(Habit habit, String category, String description) {
        if (category.equals("Health")) {
            addHealthHabits(description, habit);
        } else if (category.equals("Arts")) {
            addArtsHabits(description, habit);
        } else if (category.equals("Financial")) {
            addFinancialHabits(description, habit);
        } else if (category.equals("SelfImprovement")) {
            addSelfImprovementHabits(description, habit);
        } else if (category.equals("Social")) {
            addSocialHabits(description, habit);
        } else if (category.equals("WorkAndStudy")) {
            addWorkAndStudyHabits(description, habit);
        } else {
            System.out.println("Please input again the category");

        }
    }

    public void addHealthHabits(String description, Habit habit) {
        if (!containsHabit(description)) {
            listHabit.get(HabitGenre.Health).add(habit);
        }
    }

    public void addArtsHabits(String description, Habit habit) {
        if (!containsHabit(description)) {
            listHabit.get(HabitGenre.Arts).add(habit);
        }
    }

    public void addFinancialHabits(String description, Habit habit) {
        if (!containsHabit(description)) {
            listHabit.get(HabitGenre.Financial).add(habit);
        }
    }

    public void addSelfImprovementHabits(String description, Habit habit) {
        if (!containsHabit(description)) {
            listHabit.get(HabitGenre.SelfImprovement).add(habit);
        }
    }

    public void addSocialHabits(String description, Habit habit) {
        if (!containsHabit(description)) {
            listHabit.get(HabitGenre.Social).add(habit);
        }
    }

    public void addWorkAndStudyHabits(String description, Habit habit) {
        if (!containsHabit(description)) {
            listHabit.get(HabitGenre.WorkAndStudy).add(habit);
        }
    }

    //EFFECTS: returns the number of elements in the list of habits given the key
    public int size(String category) {
        for (HabitGenre entry : listHabit.keySet()) {
            if (category.equals(entry.toString())) {
                ArrayList<Habit> list = listHabit.get(entry);
                list.size();
                return list.size();
            }
        }
        return 0;
    }


    //MODIFIES:this
    //EFFECTS:removes the habit to the list of habits
    public void removeHabit(String description) {
        for (HabitGenre entry : listHabit.keySet()) {
            ArrayList<Habit> habits = listHabit.get(entry);
            Habit h = new Habit(description, 1, "none",HabitGenre.Arts.toString(), false);
            if (habits.contains(h)) {
                habits.remove(h);
            }
        }
    }

    //EFFECTS: returns true iff h is in the list of habits
    public boolean containsHabit(String description) {
        for (HabitGenre entry : listHabit.keySet()) {
            ArrayList<Habit> habits = listHabit.get(entry);
            Habit h = new Habit(description, 1, "none",HabitGenre.Arts.toString(), false);
            if (habits.contains(h)) {
                return true;
            }
        }
        return false;
    }

    //EFFECTS: return true if the habit has been completed, false otherwise.
    public boolean userHabitCheckCompletion(String description, int time) {
        if (containsHabit(description)) {
            for (ArrayList<Habit>  listh : listHabit.values()) {
                for (Habit h : listh) {
                    if (h.checkIfComplete(time)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}

