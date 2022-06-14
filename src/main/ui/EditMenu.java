package ui;

import javax.swing.*;
import java.awt.*;

public class EditMenu extends JPanel {
    private SettingsDialog settings;
    private JPanel buttonArea;

    public EditMenu() {
        setLayout(new GridLayout(2, 1, 5, 4));
        setBackground(Color.LIGHT_GRAY);
        setSize(new Dimension(0 , 0));
        settings = new SettingsDialog();
        setSettings(settings);

        buttonArea = new JPanel(new GridLayout(0,1, 5, 4));
        buttonArea.setBackground(Color.LIGHT_GRAY);
        buttonArea.setSize(new Dimension(0 , 0));
        add(buttonArea, 1);
    }

    public void setSettings(SettingsDialog s) {
        if (settings != null) {
            remove(settings);
        }
        settings = s;
        add(settings, 0);
    }

    public SettingsDialog getSettings() {
        return settings;
    }

    public JPanel getButtonArea() {
        return buttonArea;
    }
}
