package model;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class UserData implements Loadable, Saveable {

    static String FILENAME = "outputfile.json";
    //static String FILENAME1 = "inputfile.json";
    private HabitManager habitManager;

    public UserData(HabitManager habitManager) {
        this.habitManager = habitManager;
    }


    //EFFECTS: returns the list of habits from the user iff userInput = true
    public ArrayList<Habit> userHabits() {
        //habitManager.initializingHabitManager();
        ArrayList<Habit> userNewHabits = new ArrayList<>();
        for (ArrayList<Habit>  listh : habitManager.getHashMap().values()) {
            for (Habit h: listh) {
                //System.out.println(h.getDescription() + " " + h.getUserInput());
                if (h.getUserInput()) {
                    //System.out.println(h);
                    userNewHabits.add(h);
                }
            }
        }
        return userNewHabits;
    }


    //EFFECTS: loads the information from the file
    public ArrayList<Habit> load() throws IOException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(FILENAME));
        Habit[] listHabits = gson.fromJson(reader,Habit[].class);
        System.out.println("Here are the habits I found");
        ArrayList<Habit> userHabits = new ArrayList();
        for (Habit h : listHabits) {
            userHabits.add(h);
        }
        return userHabits;
    }

    //EFFECTS: saves the information to the file
    public void save() throws IOException {
        //userHabits();
        Gson gson = new Gson();
        String json = gson.toJson(userHabits());
        FileWriter writer = new FileWriter(FILENAME);
        writer.write(json);
        writer.close();
        System.out.println("Wrote text" + json);
    }

}
