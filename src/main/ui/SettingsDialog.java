package ui;

import model.fractal.Fractal;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SettingsDialog extends JPanel {
    private ArrayList<JTextField> fieldList = new ArrayList<>();
    private Fractal.FractalType type;

    public SettingsDialog() {
        super();
    }

    public SettingsDialog(Fractal.FractalType type, String[] parameters) {
        this.type = type;
        setLayout(new GridLayout(0,1));
        for (int i = 0; i < parameters.length; i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1,2));
            panel.add(new JLabel(parameters[i]));
            JTextField field = new JTextField();
            panel.add(field);
            fieldList.add(field);
            add(panel);
        }
    }

    public SettingsDialog(Fractal.FractalType type, String[] parameters, String[] initialInputs) {
        this.type = type;
        setLayout(new GridLayout(0,1, 5, 5));
        for (int i = 0; i < parameters.length; i++) {
            JPanel panel = new JPanel();
            panel.setLayout(new GridLayout(1,2));
            panel.add(new JLabel(parameters[i]));
            JTextField field = new JTextField(initialInputs[i]);
            panel.add(field);
            fieldList.add(field);
            add(panel);
        }
    }

    public ArrayList<String> getFieldValues() {
        ArrayList<String> values = new ArrayList<>();
        for (JTextField field : fieldList) {
            values.add(field.getText());
        }
        return values;
    }

    public Fractal.FractalType getType() {
        return type;
    }
}
