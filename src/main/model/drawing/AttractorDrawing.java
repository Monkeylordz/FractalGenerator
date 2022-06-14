package model.drawing;

import model.fractal.Fractal;
import model.fractal.StrangeAttractor;

public class AttractorDrawing extends Drawing {
    protected StrangeAttractor attractor;


    public AttractorDrawing(StrangeAttractor attractor) {
        this.attractor = attractor;
    }

    public AttractorDrawing(StrangeAttractor attractor, int width, int height) {
        super(width, height);
        this.attractor = attractor;
    }

    protected void setBounds() {
        boundWidth = 4;
        boundHeight = 4;
    }

    @Override
    public void draw() {
        for (int i = 0; i < attractor.initialIterations; i++) {
            attractor.iterate();
        }
        for (int i = 0; i < attractor.attractorMaxIterations; i++) {
            attractor.iterate();
            try {
                drawing.setRGB(scaleForImageX(attractor.getX()),
                        scaleForImageY(attractor.getY()), palette.getColor(1));
            } catch (ArrayIndexOutOfBoundsException e) {

            }
        }
    }

    // Effect: Scales real x coordinate to image coordinate
    // Bounds centered on (0,0)
    protected int scaleForImageX(double x) {
        if ((x < -(boundWidth / 2)) || (x > (boundWidth / 2))) {
            return -1;
        }
        // int newX =  (int) (x * (getWidth() / zoom) + getWidth() / (2 / zoom));
        int newX = (int) (getWidth() * ((((boundWidth / zoom) / 2) - (xcoord - x))) / boundWidth);
        return newX;
    }

    // Effect: Scales real x coordinate to image coordinate
    // Bounds centered on (0,0)
    protected int scaleForImageY(double y) {
        if ((y < -(boundHeight / 2)) || (y > (boundHeight / 2))) {
            return -1;
        }
        // int newY =  (int) (y * (getHeight() / zoom) + getHeight() / (2 / zoom));
        int newY = (int) (getHeight() * ((((boundHeight / zoom) / 2) - (ycoord - y))) / boundHeight);
        return newY;
    }

    @Override
    public Fractal getFractal() {
        return attractor;
    }
}
