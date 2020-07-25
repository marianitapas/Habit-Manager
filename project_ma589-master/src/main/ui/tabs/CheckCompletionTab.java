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

public class CheckCompletionTab extends Tab implements MouseListener {

    private JScrollPane reportPane;
    private JLabel reportMessage2;
    private DefaultListModel<String> model = new DefaultListModel<>();
    private JList<String> list = new JList<>(model);
    private HabitManager habits = new HabitManager();
    private UserData user = new UserData(habits);

    public CheckCompletionTab(Main controller, InfoManager infoManager) {
        super(controller, infoManager);

        placeCheckCompletionButton();

        JPanel chechCompletionBlock = new JPanel(new GridLayout(4, 0));
        chechCompletionBlock.setSize(Main.WIDTH - (Main.WIDTH / 5),
                Main.HEIGHT - (Main.HEIGHT / 5));

        reportMessage2 = new JLabel("Please select a habit from the list");
        reportMessage2.setFont(new Font("Georgia", Font.BOLD, 14));
        JTextArea textArea = new JTextArea(4, 30);
        reportPane = new JScrollPane(new JScrollPane(textArea));
        chechCompletionBlock.add(reportMessage2);

        chechCompletionBlock.add(reportPane);

        list.addMouseListener(this);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.VERTICAL_WRAP);
        list.setVisibleRowCount(-1);
        chechCompletionBlock.add(list);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));
        add(chechCompletionBlock);
    }

    //MODIFIES: this
    //EFFECTS: adds a generate button RemoveHabit
    private void placeCheckCompletionButton() {
        JButton b2 = new JButton("Check Completion");
        b2.setFont(new Font("Harrington", Font.BOLD, 15));
        JPanel buttonRow = formatButtonRow(b2);

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals("Check Completion")) {
                    try {
                        userHabitsGi();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    reportPane.setViewportView(list);
                }
            }
        });
        this.add(buttonRow);
    }

    public void userHabitsGi() throws IOException {
        ArrayList<Habit> habits = user.load();
        ArrayList<String> habitDescriptions = new ArrayList<>();
        for (Habit h : habits) {
            habitDescriptions.add(h.getDescription());
            model.addElement(h.getDescription());
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    //EFFECTS: shows a window message when CheckComplete button is pressed
    public void mousePressed(MouseEvent e) {
        JFrame f = new JFrame();
        ListSelectionModel selmodel = list.getSelectionModel();
        int index = selmodel.getMinSelectionIndex();
        int addTime = Integer.parseInt(JOptionPane.showInputDialog(f, "Please add the time"));
        ArrayList<Habit> habit = null;
        try {
            habit = user.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        for (Habit h : habit) {
            if (h.checkIfComplete(addTime)) {
                JOptionPane.showMessageDialog(f, "Good job you have accomplished your goal");
            } else {
                JOptionPane.showMessageDialog(f, "You are close, try harder next time");
            }
        }
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
