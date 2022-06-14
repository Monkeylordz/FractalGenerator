package model.drawing;

import model.fractal.EscapeTimeFractal;
import model.fractal.Fractal;

public class EscapeTimeDrawing extends Drawing {
    protected EscapeTimeFractal escapeTimeFractal;

    public EscapeTimeDrawing(EscapeTimeFractal fractal) {
        super();
        escapeTimeFractal = fractal;
    }

    public EscapeTimeDrawing(EscapeTimeFractal fractal, int width, int height, double x, double y, double zoom) {
        super(width, height, x, y, zoom);
        escapeTimeFractal = fractal;
    }

    protected void setBounds() {
        boundWidth = 3.5;
        boundHeight = 2;
    }

    public void draw() {
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                int escapeTime = escapeTimeFractal.escapeTime(scaleX(x), scaleY(y));
                int rgb = palette.getColor(escapeTime);
                drawing.setRGB(x, y, rgb);
            }
        }
    }

    @Override
    public Fractal getFractal() {
        return escapeTimeFractal;
    }

}
