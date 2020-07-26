package ui.tabs;

import model.Habit;
import ui.InfoManager;
import ui.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URL;

public class AddHabitTab extends Tab implements MouseListener {

    private JScrollPane reportPane;
    private JLabel reportMessage;
    private JList list;

    //REQUIRES: Main controller that holds this tab
    //EFFECTS: creates
    public AddHabitTab(Main controller, InfoManager infoManager) {
        super(controller,infoManager);
        setBackground(new java.awt.Color(178, 130,181));
        JPanel addHabitBlock = new JPanel(new GridLayout(5,0));



        placeAddRegularHabitButton();
        placeAddCornerStoneHabitButton();

//        addHabitBlock.setSize(Main.WIDTH - (Main.WIDTH / 5),
//                Main.HEIGHT - (Main.HEIGHT / 2));

        reportMessage = new JLabel("Pick a category that interest you! ");
        JTextArea textArea = new JTextArea(4, 1);
        textArea.setLineWrap(true);
        reportPane = new JScrollPane(new JScrollPane(textArea));
        addHabitBlock.add(reportMessage);
        addHabitBlock.add(reportPane);
        reportMessage.setFont(new Font("Harrington", Font.BOLD,18));
        addHabitBlock.setBackground(new java.awt.Color(178, 130,181));



        list = new JList();
        list.addMouseListener(this);
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(-1);
        list.setFont(new Font("Georgia", Font.BOLD,16));
        addHabitBlock.add(list);
        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));
        add(addHabitBlock);

    }



    //MODIFIES: this
    //EFFECTS: adds a generate button Add Regular Habit
    private void placeAddRegularHabitButton()  {
        JButton b1 = new JButton("Add Regular Habit");
        b1.setFont(new Font("Harrington", Font.BOLD,18));
        JPanel buttonRow = formatButtonRow(b1);
       // buttonRow.setBackground(new java.awt.Color(178, 130,181));


        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)  {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals("Add Regular Habit")) {
                    try {
                        list.setListData(getInfoManager().printCategories().toArray());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    reportPane.setViewportView(list);
                }
            }
        });

        this.add(buttonRow);
    }

    //MODIFIES: this
    //EFFECTS: adds a generate button AddHabit
    private void placeAddCornerStoneHabitButton() {
        JButton b1 = new JButton("Add Corner Stone Habit");
        b1.setFont(new Font("Harrington", Font.BOLD,18));
        JPanel buttonRow = formatButtonRow(b1);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = e.getActionCommand();
                if (buttonPressed.equals("Add Corner Stone Habit")) {
                    try {
                        list.setListData(getInfoManager().printCategories().toArray());
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    reportPane.setViewportView(list);
                }
            }
        });

        this.add(buttonRow);

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    //EFFECTS: creates a new user Habit if the AddHabit button is pressed
    public void mousePressed(MouseEvent e) {

        Object index = list.getSelectedValue();
        JFrame f = new JFrame();


        String addDescription = JOptionPane.showInputDialog(f, getInfoManager()
                .printCategoriesAndLists(list.getSelectedValue().toString()).toArray());
        int addTime = Integer.parseInt(JOptionPane.showInputDialog(f,"What is the time you want to add?"));
        String addComment = JOptionPane.showInputDialog(f, "Would you like to add a comment? ");

        Habit habit = new Habit(addDescription, addTime, addComment, index.toString(), false);
        habit.setUserInput();
        try {
            getInfoManager().questionsAddHabit(habit,addTime,addComment,index.toString(),addDescription);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


}

