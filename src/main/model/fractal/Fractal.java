package model.fractal;

public abstract class Fractal {
    public static int maxIteration = 2048;

    public abstract FractalType getType();

    public enum FractalType {
        MANDELBROT, MULTIBROT, JULIA, ATTRACTOR
    }
}
