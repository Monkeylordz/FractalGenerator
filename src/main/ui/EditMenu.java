package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class EditMenu extends JPanel {
    private SettingsDialog settings;

    public EditMenu() {
        setBackground(Color.LIGHT_GRAY);
        setLayout(new GridLayout(0,1, 5, 10));
        setSize(new Dimension(0 , 0));
        settings = new SettingsDialog();
        add(settings);
    }

    public void setSettings(SettingsDialog s) {
        remove(settings);
        settings = s;
        add(settings, 0);
    }

    public SettingsDialog getSettings() {
        return settings;
    }

}
