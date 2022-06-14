package ui;

import model.drawing.ColorPalette;
import model.drawing.Drawing;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Animator {
    private final Drawing drawing;

    public Animator(Drawing d, ColorPalette p) {
        drawing = d;
        drawing.setPalette(p);
    }

    // Requires: zoom > 0, frames > 0, movieName is a valid file name
    // Modifies: drawing
    // Effect: Zooms in at drawing's x and y coordinates to given zoom over given
    //         frames and saves images in folder named movieName in /data
    public void animate(double initialZoom, double finalZoom, int frames, String movieName) {
        String folder = initSaveFolder(movieName);
        drawing.setZoom(initialZoom);
        double deltaZoom = Math.pow((finalZoom / initialZoom), 1.0 / frames);
        for (int i = 0; i < frames; i++) {
            drawing.zoomIn(deltaZoom);
            drawing.draw();
            saveImage(drawing.getImage(), i, folder);
        }
    }

    private String initSaveFolder(String movieName) {
        File dir = new File("data/" + movieName);
        if (!dir.exists()) {
            dir.mkdir();
        }
        return dir.toString();
    }

    private void saveImage(BufferedImage image, int index, String folder) {
        String imageFileName = indexToName(index);
        try {
            ImageIO.write(image, "png", new File(folder + "/" + imageFileName));
        } catch (IOException e) {
            System.out.println("Unable to save image");
        }
    }

    private String indexToName(int index) {
        String name = "";
        name += index;
        return name + ".png";
    }
}
