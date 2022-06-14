package model.fractal;

public class Mandelbrot extends EscapeTimeFractal {

    @Override
    public int escapeTime(double xcoord, double ycoord) {
        double x0 = xcoord;
        double y0 = ycoord;
        double reSquared = 0;
        double imSquared = 0;
        double zsquared = 0;
        double x = 0;
        double y = 0;
        int iteration = 0;
        while (reSquared + imSquared <= 4  &&  iteration < maxIteration) {
            x = reSquared - imSquared + x0;
            y = zsquared - reSquared - imSquared + y0;
            reSquared = x * x;
            imSquared = y * y;
            zsquared = (x + y) * (x + y);
            iteration++;
        }
        return iteration;
    }

    public FractalType getType() {
        return FractalType.MANDELBROT;
    }

}
