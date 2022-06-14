package ui;

import model.drawing.ColorPalette;
import model.drawing.Drawing;
import java.awt.*;
import java.awt.image.BufferedImage;

public class FractalDisplay extends Canvas {
    // private BufferedImage image;
    private Drawing drawing;

    public void paint(Graphics g) {
        if (drawing == null) {
            g.drawImage(null, 0, 0, this);
        } else {
            g.drawImage(drawing.getImage(), 0, 0, this);
        }
    }

    public void setDrawing(Drawing d, ColorPalette p) {
        drawing = d;
        setPalette(p);
    }

    public void update() {
        if (drawing != null) {
            drawing.draw();
        }
        validate();
    }

    public void setPalette(ColorPalette palette) {
        drawing.setPalette(palette);
    }

    public void zoomIn() {
        drawing.zoomIn();
    }

    public void zoomIn(int x, int y) {
        drawing.zoomIn(x, y);
    }

    public void zoomOut() {
        drawing.zoomOut();
    }

    public BufferedImage getImage() {
        return drawing.getImage();
    }

    public Drawing getDrawing() {
        return drawing;
    }

}
