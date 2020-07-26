package ui.tabs;

import model.Habit;
import model.HabitManager;
import model.UserData;
import ui.InfoManager;
import ui.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class ViewHabitsTab extends Tab {


    private JScrollPane reportPane;
    private JLabel reportMessage;
    private DefaultListModel model = new DefaultListModel();
    private JList<String> list = new JList<>(model);
    private HabitManager habits =  new HabitManager();
    private UserData user =  new UserData(habits);
    private RemoveHabitTab removeHabitTab;


    public ViewHabitsTab(Main controller, InfoManager infoManager) throws IOException {
        super(controller, infoManager);
        removeHabitTab = new RemoveHabitTab(controller,infoManager);
        setBackground(new java.awt.Color(178, 130,181));
        placeViewHabitButton();

        JPanel viewHabitBlock = new JPanel(new GridLayout(4,0));
        viewHabitBlock.setBackground(new java.awt.Color(178, 130,181));
        viewHabitBlock.setSize(Main.WIDTH - (Main.WIDTH / 5),
                Main.HEIGHT - (Main.HEIGHT / 5));
        reportMessage = new JLabel("Here is your list of habits!.");
        reportMessage.setFont(new Font("Georgia", Font.BOLD,18));
        JTextArea textArea = new JTextArea(4, 30);
        reportPane = new JScrollPane(new JScrollPane(textArea));
        viewHabitBlock.add(reportMessage);
        viewHabitBlock.add(reportPane);

        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL_WRAP);
        list.setVisibleRowCount(-1);
        viewHabitBlock.add(list);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));
        add(viewHabitBlock);
    }

    //MODIFIES: this
    //EFFECTS: adds a generate button RemoveHabit
    private void placeViewHabitButton() {
        JButton b3 = new JButton("View Habits");
        b3.setFont(new Font("Harrington", Font.BOLD,18));
        JPanel buttonRow = formatButtonRow(b3);

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals("View Habits")) {
                    viewHabitsGui();
                }
            }
        });
        this.add(buttonRow);
    }

    //EFFECTS: returns the list of habits that the user has added
    private void viewHabitsGui() {
        ArrayList<Habit> habits = null;
        try {
            habits = user.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> habitDescriptions = new ArrayList<>();
        for (Habit h : habits) {
            habitDescriptions.add(h.getFullDescription());
            model.addElement(h.getFullDescription());
        }
        reportPane.setViewportView(list);
    }

}
