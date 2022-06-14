package ui;

import model.drawing.ColorPalette;
import model.fractal.Fractal;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class FractalGenerator extends JFrame {
    public static final int WIDTH = 870;
    public static final int HEIGHT = 570;

    public static final String[] MANDEL_OPTIONS =
            new String[]{"Width", "Height", "X-Coordinate", "Y-Coordinate", "Zoom"};
    public static final String[] MULTI_OPTIONS =
            new String[]{"Width", "Height", "Dimensions", "X-Coordinate", "Y-Coordinate", "Zoom"};
    public static final String[] JULIA_OPTIONS =
            new String[]{"Width", "Height", "Dimensions", "Real Part", "Complex Part",
                    "X-Coordinate", "Y-Coordinate", "Zoom"};
    public static final String[] ATTRACTOR_OPTIONS =
            new String[]{"Width", "Height", "Starting X", "Starting Y", "a", "b", "c", "d"};

    private FractalDisplay display;
    private EditMenu editMenu;
    private ColorPalette palette = new ColorPalette();

    public FractalGenerator() {
        super("Fractal Generator");
        initFrame();
        initEditMenu();
        initDisplay();
    }

    private void initFrame() {
        setVisible(true);
        setMinimumSize(new Dimension(WIDTH, HEIGHT));
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addTopButtons();
    }

    private void addTopButtons() {
        JPanel buttonArea = new JPanel();
        buttonArea.setLayout(new GridLayout(1,2, 3, 3));
        buttonArea.setSize(new Dimension(0, 0));
        add(buttonArea, BorderLayout.NORTH);

        addNewFractalButton(buttonArea);
        addColorPaletteButton(buttonArea);
        addSaveImageButton(buttonArea);
    }

    private void addNewFractalButton(JPanel buttonArea) {
        JButton newFractalButton = new JButton("Create New Fractal");
        buttonArea.add(newFractalButton);
        newFractalButton.addActionListener(e -> newFractal());
    }

    private void addColorPaletteButton(JPanel buttonArea) {
        JButton changePaletteButton = new JButton("Change Color Palette");
        buttonArea.add(changePaletteButton);
        changePaletteButton.addActionListener(e -> changePalette());
    }

    private void addSaveImageButton(JPanel buttonArea) {
        JButton saveButton = new JButton("Save Image");
        buttonArea.add(saveButton);
        saveButton.addActionListener(e -> saveImage());
    }

    private void initDisplay() {
        display = new FractalDisplay();
        display.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if (display != null && !((x < 0) || (x > display.getImage().getWidth())
                        || (y < 0) || (y > display.getImage().getHeight()))) {
                    display.zoomIn(x, y);
                    updateDisplay();
                }
            }
        });
        add(display, BorderLayout.CENTER);
    }

    private void initEditMenu() {
        editMenu = new EditMenu();
        addEditButtons();
        editMenu.setVisible(false);
        add(editMenu, BorderLayout.WEST);
    }

    private void addEditButtons() {
        addGoButton();
        addZoomInButton();
        addZoomOutButton();
        addAnimateButton();
    }

    private void addGoButton() {
        JButton go = new JButton("Go!");
        go.addActionListener(e -> {
            display.setDrawing(SettingsParser.parseSettings(editMenu.getSettings()), palette);
            updateDisplay();
        });
        editMenu.getButtonArea().add(go);
    }

    private void addZoomInButton() {
        JButton zoomIn = new JButton("Zoom In");
        zoomIn.addActionListener(e -> {
            display.zoomIn();
            updateDisplay();
        });
        editMenu.getButtonArea().add(zoomIn);
    }

    private void addZoomOutButton() {
        JButton zoomOut = new JButton("Zoom Out");
        zoomOut.addActionListener(e -> {
            display.zoomOut();
            updateDisplay();
        });
        editMenu.getButtonArea().add(zoomOut);
    }

    private void addAnimateButton() {
        JButton animate = new JButton("Animate");
        animate.addActionListener(e -> animate());
        editMenu.getButtonArea().add(animate);
    }

    private void setSettings(SettingsDialog settings) {
        editMenu.setVisible(true);
        editMenu.setSettings(settings);
    }

    private void newFractal() {
        Object[] fractalList = {"Mandelbrot", "Multibrot", "Julia", "Strange Attractor"};
        String fractalType = (String) JOptionPane.showInputDialog(
                this,
                "Choose a fractal type:",
                "Create New Fractal",
                JOptionPane.PLAIN_MESSAGE,
                null,
                fractalList,
                "Mandelbrot");
        if (fractalType != null) {
            addDrawing(fractalType);
            updateDisplay();
        }
    }

    public void addDrawing(String fractalType) {
        switch (fractalType) {
            case "Mandelbrot" -> addMandelbrot();
            case "Multibrot" -> addMultibrot();
            case "Julia" -> addJulia();
            case "Strange Attractor" -> addAttractor();
        }
    }

    private void addMandelbrot() {
        SettingsDialog settings = new SettingsDialog(
                Fractal.FractalType.MANDELBROT,
                MANDEL_OPTIONS,
                new String[]{"700", "400", "-0.75", "0", "1"});
        confirmSettings(settings);
    }

    private void addMultibrot() {
        SettingsDialog settings = new SettingsDialog(
                Fractal.FractalType.MULTIBROT,
                MULTI_OPTIONS,
                new String[]{"700", "400", "2", "0", "0", "1"});
        confirmSettings(settings);
    }

    private void addJulia() {
        SettingsDialog settings = new SettingsDialog(
                Fractal.FractalType.JULIA,
                JULIA_OPTIONS,
                new String[]{"700", "400", "2", "0.1", "0.604", "0", "0", "1"});
        confirmSettings(settings);
    }

    private void addAttractor() {
        SettingsDialog settings = new SettingsDialog(
                Fractal.FractalType.ATTRACTOR,
                ATTRACTOR_OPTIONS,
                new String[]{"560", "560", "0.1", "0.1", "-0.966918", "2.879879", "0.765145", "0.744728"});
        confirmSettings(settings);
    }

    private void confirmSettings(SettingsDialog settings) {
        int result = JOptionPane.showConfirmDialog(
                getParent(), settings, "Specify Variables:",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);
        if (result == JOptionPane.OK_OPTION) {
            display.setDrawing(SettingsParser.parseSettings(settings), palette);
            setSettings(settings);
        }
    }

    private void updateDisplay() {
        display.update();
        remove(display);
        add(display);
        updateSettings();
        validate();
    }

    private void updateSettings() {
        setSettings(SettingsParser.parseDrawing(display.getDrawing()));
    }

    private void changePalette() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Choose color palette:");
        fileChooser.setFileFilter(new FileNameExtensionFilter("png file", "png"));
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                palette = new ColorPalette(fileChooser.getSelectedFile().toString());
                display.setPalette(palette);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                        "Unable to change palette.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        updateDisplay();
    }

    private void saveImage() {
        JFileChooser fileChooser = setupImageSaver();
        if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                String imageFileName = fileChooser.getSelectedFile().toString();
                if (!imageFileName.endsWith(".png")) {
                    imageFileName = imageFileName + ".png";
                }
                ImageIO.write(display.getImage(), "png", new File(imageFileName));
                JOptionPane.showMessageDialog(this,
                        "Image saved.",
                        "Success",
                        JOptionPane.PLAIN_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                        "Unable to save image.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private JFileChooser setupImageSaver() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save image as:");
        fileChooser.setCurrentDirectory(new File("/data"));
        fileChooser.setSelectedFile(new File("fractal.png"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("png file", "png"));
        return fileChooser;
    }

    private void animate() {
        double goalZoom = display.getDrawing() != null ? display.getDrawing().getZoom() : 1;
        SettingsDialog settings = new SettingsDialog(
                Fractal.FractalType.MANDELBROT,
                new String[] {"Initial Zoom:", "Final Zoom:", "Number of Frames:", "Movie Name:"},
                new String[] {"1", String.valueOf(goalZoom), "", ""});
        int result = JOptionPane.showConfirmDialog(
                getParent(), settings, "Enter Settings:",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null);
        if (result == JOptionPane.OK_OPTION) {
            ArrayList<String> values = settings.getFieldValues();

            // Animator
            Animator animator = new Animator(display.getDrawing(), palette);
            animator.animate(Double.parseDouble(values.get(0)), Double.parseDouble(values.get(1)),
                    Integer.parseInt(values.get(2)),values.get(3));

            JOptionPane.showMessageDialog(this,"Done");
        }
    }

    public static void main(String[] args) {
        new FractalGenerator();
    }
}
