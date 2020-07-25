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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

public class RemoveHabitTab extends Tab implements MouseListener {
    private JScrollPane reportPane;
    private JLabel reportMessage2;
    private JLabel reportMessage;
    private DefaultListModel model = new DefaultListModel();
    private JList<String> list = new JList<>(model);
    private HabitManager habits =  new HabitManager();
    private UserData user =  new UserData(habits);


    public RemoveHabitTab(Main controller, InfoManager infoManager) throws IOException {
        super(controller, infoManager);

        placeRemoveHabitButton();

        JPanel removeHabitBlock = new JPanel(new GridLayout(4,0));
        removeHabitBlock.setSize(Main.WIDTH - (Main.WIDTH / 5),
                Main.HEIGHT - (Main.HEIGHT / 5));

        reportMessage = new JLabel("Here is your list of habits!.");
        reportMessage.setFont(new Font("Georgia", Font.BOLD,14));
        reportMessage2 = new JLabel("Please select the habit you want to remove");
        reportMessage2.setFont(new Font("Georgia", Font.BOLD,14));
        JTextArea textArea = new JTextArea(4, 30);
        reportPane = new JScrollPane(new JScrollPane(textArea));
        removeHabitBlock.add(reportMessage);
        removeHabitBlock.add(reportMessage2);
        removeHabitBlock.add(reportPane);


        list.addMouseListener(this);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL_WRAP);
        list.setVisibleRowCount(-1);
        removeHabitBlock.add(list);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));
        add(removeHabitBlock);
    }

    //MODIFIES: this
    //EFFECTS: adds a generate button RemoveHabit
    private void placeRemoveHabitButton() {
        JButton b2 = new JButton("Remove Habit");
        b2.setFont(new Font("Harrington", Font.BOLD,15));
        JPanel buttonRow = formatButtonRow(b2);

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals("Remove Habit")) {
                    try {
                        userHabitsGui();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    reportPane.setViewportView(list);
                }
            }
        });
        this.add(buttonRow);
    }

    public void userHabitsGui() throws IOException {
        ArrayList<Habit> habits = user.load();
        ArrayList<String> habitDescriptions = new ArrayList<>();
        for (Habit h : habits) {
            habitDescriptions.add(h.getFullDescription());
            model.addElement(h.getFullDescription());
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    //EFFECTS: removes the selected Habit when Remove button is pressed
    public void mousePressed(MouseEvent e) {

        JFrame f = new JFrame();
        ListSelectionModel selmodel = list.getSelectionModel();
        int index = selmodel.getMinSelectionIndex();

        int a = JOptionPane.showConfirmDialog(f,"Are you sure you wan to delete this habit?");

        if (a == JOptionPane.YES_OPTION) {
            removeUserHabitsGui(index);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }

    //MODIFIES: this
    //EFFECTS: removes the Habit from the user habits' list
    private void removeUserHabitsGui(int index) {
        ArrayList<Habit> habit = null;
        try {
            habit = user.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        for (Habit h: habit) {
            try {
                getInfoManager().instructionsRemoveHabit(h.getDescription());
                user.save();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        model.remove(index);
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}

