package model.drawing;

import model.fractal.Fractal;
import java.awt.image.BufferedImage;

public abstract class Drawing {
    // Default Final Values
    protected static final int defaultWidth = 700;
    protected static final int defaultHeight = 400;
    protected final double defaultX = 0;
    protected final double defaultY = 0;
    protected final double defaultZoom = 1;
    protected final ColorPalette defaultPalette = new ColorPalette();
    public final double zoomInConstant = 5.0;

    protected BufferedImage drawing;
    protected double xcoord;
    protected double ycoord;
    protected double zoom;
    protected ColorPalette palette;
    protected double boundWidth;
    protected double boundHeight;

    public Drawing(int width, int height) {
        drawing = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        xcoord = defaultX;
        ycoord = defaultY;
        zoom = defaultZoom;
        palette = defaultPalette;
        setBounds();
    }

    public Drawing() {
        this(defaultWidth, defaultHeight);
    }

    public Drawing(int width, int height, double x, double y, double zoom) {
        this(width, height);
        xcoord = x;
        ycoord = y;
        this.zoom = zoom;
    }

    public abstract void draw();

    protected abstract void setBounds();

    // Modifies: this
    // Effect: Zooms into the middle of the fractal image
    public void zoomIn() {
        zoomIn(getWidth() / 2, getHeight() / 2);
    }

    // Requires: zoom > 0
    // Modifies: this
    // Effect: Zooms by given zoom factor
    public void zoomIn(double z) {
        zoom = zoom * z;
    }

    // Requires: 0 <= x < width, 0 <= y < height of the current drawing
    // Modifies: this
    // Effect: Zooms into the fractal image at a given x or y image coordinate
    public void zoomIn(int x, int y) {
        xcoord = scaleX(x);
        ycoord = scaleY(y);
        zoom = zoom * zoomInConstant;
    }

    public void zoomOut() {
        zoom = zoom / zoomInConstant;
    }

    // Effect: Scale image x coordinate to real coordinate
    protected double scaleX(double x) {
        return x * ((boundWidth / zoom) / getWidth()) + (xcoord - (boundWidth / 2) / zoom);
    }

    // Effect: Scale image x coordinate to real coordinate
    protected double scaleY(double y) {
        return y * ((boundHeight / zoom) / getHeight()) + (ycoord - (boundHeight / 2) / zoom);
    }

    public void setPalette(ColorPalette palette) {
        this.palette = palette;
    }

    public BufferedImage getImage() {
        return drawing;
    }

    // Effect: Returns the width of drawing
    public int getWidth() {
        return drawing.getWidth();
    }

    // Effect: Returns the height of drawing
    public int getHeight() {
        return drawing.getHeight();
    }

    // Effect: Returns the current X coordinate the fractal is looking at.
    public double getXCoord() {
        return xcoord;
    }

    // Effect: Returns the current Y coordinate the fractal is looking at.
    public double getYCoord() {
        return ycoord;
    }

    // Effect: Returns the zoom
    public double getZoom() {
        return zoom;
    }

    // Modifies: this
    public void setZoom(double z) {
        zoom = z;
    }

    // Effect: Returns fractal
    public abstract Fractal getFractal();
}
