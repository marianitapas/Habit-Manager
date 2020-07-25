package ui.tabs;

import ui.InfoManager;
import ui.Main;

import javax.swing.*;
import java.awt.*;

public abstract class Tab extends JPanel {
    protected Main controller;
    protected InfoManager infoManager;

    //REQUIRES: InfoManager controller that holds this tab
    public Tab(Main controller, InfoManager infoManager) {
        this.controller = controller;
        this.infoManager = infoManager;

    }

    //EFFECTS: creates and returns row with button included
    public JPanel formatButtonRow(JButton b) {
        b.setSize(60,10);
        b.setBorderPainted(true);
        b.setFocusPainted(true);
        b.setContentAreaFilled(true);
        JPanel p = new JPanel();
        p.setBackground(Color.GRAY);
        GridLayout layout = new GridLayout(5,5);
        layout.setHgap(5);
        layout.setVgap(5);
        p.add(b);

        return p;
    }

    //EFFECTS: returns the Main controller for this tab
    public Main getController() {
        return controller;
    }

    //EFFECTS: returns the InfoManager for this tab
    public InfoManager getInfoManager() {
        return infoManager;
    }



}

