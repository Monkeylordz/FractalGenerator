package model.fractal;

import math.ComplexNumber;

public class Multibrot extends EscapeTimeFractal {
    protected int dimensions;

    public Multibrot(int dims) {
        dimensions = dims;
    }

    @Override
    public int escapeTime(double xcoord, double ycoord) {
        ComplexNumber c = new ComplexNumber(xcoord, ycoord);
        ComplexNumber z = new ComplexNumber();
        int iteration = 0;
        while (z.getReal() <= 4 && iteration < maxIteration) {
            z.pow(dimensions);
            z.addWithComplex(c);
            iteration++;
        }
        return iteration;
    }

    public int getDimensions() {
        return dimensions;
    }

    public FractalType getType() {
        return FractalType.MULTIBROT;
    }
}
