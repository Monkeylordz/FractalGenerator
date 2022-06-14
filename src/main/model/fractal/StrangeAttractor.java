package model.fractal;


public class StrangeAttractor extends Fractal {
    public final int initialIterations = 100;
    public final int attractorMaxIterations = 65536;
    protected double x, y, a, b, c, d;

    public StrangeAttractor(double x, double y, double a, double b, double c, double d) {
        setParameters(x, y, a, b, c, d);
    }

    public void setParameters(double x, double y, double a, double b, double c, double d) {
        this.x = x;
        this.y = y;
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    // Attractor Formula found on Nathan Selikoff's website:
    // https://nathanselikoff.com/training/tutorial-strange-attractors-in-c-and-opengl
    public void iterate() {
        double tempx = Math.sin(y * b) + c * Math.sin(x * b);
        double tempy = Math.sin(x * a) + d * Math.sin(y * a);

        x = tempx;
        y = tempy;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }

    public double getC() {
        return c;
    }

    public double getD() {
        return d;
    }

    public FractalType getType() {
        return FractalType.ATTRACTOR;
    }
}
