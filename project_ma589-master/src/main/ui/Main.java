package ui;


import model.HabitManager;
import model.UserData;
import ui.tabs.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

public class Main extends JFrame {

    private static HabitManager habits;
    private static InfoManager infoManager;
    private static UserData data;
    private JTabbedPane sidebar;
    public static final int HOME_TAB_INDEX = 0;
    public static final int ADD_HABIT_TAB_INDEX = 1;
    public static final int REMOVE_HABIT_TAB_INDEX = 2;
    public static final int VIEW_HABIT_TAB_INDEX = 3;
    public static final int CHECK_COMPLETION_TAB_INDEX = 4;
    public static final int EXIT_TAB_INDEX = 5;
    private static Border raisedbevel;



    public static void main(String[] args) throws IOException {
        new Main();
    }

    //MODIFIES: this
    //EFFECTS: creates Main, loads
    private Main() throws IOException {
        super("HabitTracker Console");

        habits = new HabitManager();
        data = new UserData(habits);
        infoManager = new InfoManager(habits, data);



        getContentPane().setBackground(new java.awt.Color(50, 0,50));
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        sidebar = new JTabbedPane();
        sidebar.setSize(100, 50);
        sidebar.setTabPlacement(JTabbedPane.LEFT);
        loadAddAndRemoveTabs();
        loadViewAndCheckTabs();
        add(sidebar);
        setVisible(true);


    }

    private void loadAddAndRemoveTabs() throws IOException {



        JPanel homeTab = new HomeTab(this, infoManager);
        homeTab.setBackground(Color.WHITE);
        add(homeTab, BorderLayout.CENTER);
        sidebar.setBackground(Color.WHITE);
        sidebar.setBorder( BorderFactory.createEmptyBorder( 10, 5, 0, 5 ) );
        sidebar.add(homeTab, HOME_TAB_INDEX);
        sidebar.setTitleAt(HOME_TAB_INDEX, "Home");



        JPanel addHabitTab = new AddHabitTab(this, infoManager);
        add(addHabitTab, BorderLayout.AFTER_LAST_LINE);
        sidebar.add(addHabitTab, ADD_HABIT_TAB_INDEX);
        sidebar.setTitleAt(ADD_HABIT_TAB_INDEX, "Add Habit");


        JPanel removeHabitTab = new RemoveHabitTab(this, infoManager);
        add(removeHabitTab, BorderLayout.SOUTH);
        sidebar.add(removeHabitTab, REMOVE_HABIT_TAB_INDEX);
        sidebar.setTitleAt(REMOVE_HABIT_TAB_INDEX, "Remove Habit");
        sidebar.setSize(70, 100);

    }






    private void loadViewAndCheckTabs() throws IOException {
        JPanel vieHabitTab = new ViewHabitsTab(this, infoManager);
        add(vieHabitTab, BorderLayout.SOUTH);
        sidebar.add(vieHabitTab, VIEW_HABIT_TAB_INDEX);
        sidebar.setTitleAt(VIEW_HABIT_TAB_INDEX, "View Habits");
        sidebar.setFont(new Font("Georgia", Font.CENTER_BASELINE, 14));

        JPanel checkCompletionTab = new CheckCompletionTab(this, infoManager);
        add(checkCompletionTab, BorderLayout.AFTER_LAST_LINE);
        sidebar.add(checkCompletionTab, CHECK_COMPLETION_TAB_INDEX);
        sidebar.setTitleAt(CHECK_COMPLETION_TAB_INDEX, "Check Completion");

        JPanel exitTab = new ExitTab(this, infoManager);
        add(exitTab, BorderLayout.SOUTH);
        sidebar.add(exitTab, EXIT_TAB_INDEX);
        sidebar.setTitleAt(EXIT_TAB_INDEX, "Exit");
    }


    //EFFECTS: returns sidebar of this UI
    public JTabbedPane getTabbedPane() {
        return sidebar;
    }


}



