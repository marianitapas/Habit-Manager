package ui.tabs;

import ui.InfoManager;
import ui.Main;

import javax.swing.*;
import java.awt.*;

public class ExitTab extends Tab {

    //REQUIRES: Main controller that holds this tab
    //EFFECTS: creates
    public ExitTab(Main controller, InfoManager infoManager) {
        super(controller,infoManager);
        setBackground(new java.awt.Color(178, 130,181));
        setLocation(this.getWidth()/2,this.getHeight()/2);
        placeAddRegularHabitButton();
        //System.out.println("Thank you. See you next time");
    }

    //MODIFIES: this
    //EFFECTS: adds a generate button Add Regular Habit
    private void placeAddRegularHabitButton()  {
        JButton b1 = new JButton("Exit");
        b1.setSize(WIDTH, HEIGHT / 6);
        b1.setLocation(this.getWidth()/2,this.getHeight()/2);

        b1.setFont(new Font("Harrington", Font.BOLD,18));

        b1.addActionListener(e -> System.exit(0));
        this.add(b1);
    }
}
