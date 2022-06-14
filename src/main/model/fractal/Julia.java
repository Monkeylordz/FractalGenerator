package model.fractal;

import math.ComplexNumber;

public class Julia extends EscapeTimeFractal {
    protected ComplexNumber complex;
    protected int dimensions;

    public Julia(int dimensions, ComplexNumber c) {
        this.dimensions = dimensions;
        complex = c;
    }

    public int escapeTime(double curX, double curY) {
        ComplexNumber z = new ComplexNumber(curX, curY);
        int iteration = 0;
        while (ComplexNumber.multiplyComplexes(z, z).getReal() <= 4 && iteration < maxIteration) {
            z.pow(dimensions);
            z.addWithComplex(complex);
            iteration++;
        }
        return iteration;
    }

    public ComplexNumber getComplex() {
        return complex;
    }

    public int getDimensions() {
        return dimensions;
    }

    public FractalType getType() {
        return FractalType.JULIA;
    }
}
