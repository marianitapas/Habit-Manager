package ui.tabs;

import ui.InfoManager;
import ui.Main;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class HomeTab extends Tab {

    private static final String INIT_GREETING = "Hello! Welcome to your Habit Time Tracking";
    private static final String SEC_GREETING = "You can request the following information:";
    private JLabel greeting;
    private JLabel greeting1;

    private JButton b1;
    private JButton b2;
    private JButton b3;
    private JButton b4;
    private JButton b5;


    public HomeTab(Main controller, InfoManager infoManager) {
        super(controller, infoManager);
        JPanel p = new JPanel();
        p.setBackground(Color.gray);
        GridLayout layout = new GridLayout(8,0);
        setLayout(layout);
        layout.setVgap(10);
        add(p, layout);

        placeGreeting();
        placeHomeButtons();
    }

    //EFFECTS: creates greeting at top of console
    public void placeGreeting() {
        greeting = new JLabel(INIT_GREETING, JLabel.CENTER);
        greeting.setFont(new Font("Harrington", Font.ITALIC,25));
        greeting1 = new JLabel(SEC_GREETING, JLabel.CENTER);
        greeting1.setFont(new Font("Harrington", Font.CENTER_BASELINE,18));
        this.add(greeting);
        greeting1.setSize(WIDTH, HEIGHT);
        this.add(greeting1);
    }

    //EFFECTS: creates Add Habit button
    private void placeHomeButton1() {
        JPanel menu = new JPanel();
        b1 = new JButton("Add Habit");
        b1.setFont(new Font("Harrington", Font.BOLD,15));
        menu.setBackground(new java.awt.Color(46, 139,87));
        menu.add(formatButtonRow(b1));

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("sound2.wav");
                String b1 = e.getActionCommand();
                if (b1.equals("Add Habit")) {
                    getController().getTabbedPane().setSelectedIndex(Main.ADD_HABIT_TAB_INDEX);
                }
            }
        });
        this.add(menu);
    }


    //EFFECTS: creates Remove Habit button
    private void placeHomeButton2() {

        JPanel menu = new JPanel();
        b2 = new JButton("Remove Habit");
        b2.setFont(new Font("Harrington", Font.BOLD,14));
        menu.add(formatButtonRow(b2));
        menu.setBackground(new java.awt.Color(224, 17,95));

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("sound.wav");
                String b2 = e.getActionCommand();
                if (b2.equals("Remove Habit")) {
                    getController().getTabbedPane().setSelectedIndex(Main.REMOVE_HABIT_TAB_INDEX);
                }
            }
        });
        this.add(menu);
    }


    //EFFECTS: creates View Habits button
    private void placeHomeButton3() {
        JPanel menu = new JPanel();
        b3 = new JButton("View Habits");
        b3.setSize(60,10);
        b3.setFont(new Font("Harrington", Font.BOLD,15));
        menu.add(formatButtonRow(b3));
        menu.setBackground(new java.awt.Color(99, 209,62));

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("sound.wav");
                String b3 = e.getActionCommand();
                if (b3.equals("View Habits")) {
                    getController().getTabbedPane().setSelectedIndex(Main.VIEW_HABIT_TAB_INDEX);
                }
            }
        });
        this.add(menu);
    }


    //EFFECTS: creates a Check Completion button
    private void placeHomeButton4() {
        JPanel menu = new JPanel();
        b4 = new JButton("Check Completion");
        b4.setFont(new Font("Harrington", Font.BOLD,15));
        menu.add(formatButtonRow(b4));
        menu.setBackground(new java.awt.Color(254, 127,156));

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("sound.wav");
                String b4 = e.getActionCommand();
                if (b4.equals("Check Completion")) {
                    getController().getTabbedPane().setSelectedIndex(Main.CHECK_COMPLETION_TAB_INDEX);
                }
            }
        });
        this.add(menu);
    }

    //EFFECTS: creates an Exit button
    private void placeHomeButton5() {
        JPanel menu = new JPanel();
        b5 = new JButton("Exit");
        menu.add(formatButtonRow(b5));
        b5.setFont(new Font("Harrington", Font.BOLD,15));
        b5.setSize(60,10);
        menu.setBackground(new java.awt.Color(0, 168,107));

        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                playSound("sound.wav");
                String b5 = e.getActionCommand();
                if (b5.equals("Exit")) {
                    getController().getTabbedPane().setSelectedIndex(Main.EXIT_TAB_INDEX);
                }
            }
        });
        this.add(menu);
    }

    private void placeHomeButtons() {
        placeHomeButton1();
        placeHomeButton2();
        placeHomeButton3();
        placeHomeButton4();
        placeHomeButton5();
    }

    //EFFECTS: plays sounds
    public void playSound(String soundName) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            System.out.println("Error with playing sound.");
            ex.printStackTrace();
        }
    }


}
