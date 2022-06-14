package model.fractal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MandelbrotTest {
    Mandelbrot mandel;

    @BeforeEach
    public void before() {
        mandel = new Mandelbrot();
    }

    @Test
    public void testEscapeTime() {
        assertEquals(Fractal.maxIteration, mandel.escapeTime(0,0));
        assertEquals(5, mandel.escapeTime(0.5,0.01));
        assertEquals(15, mandel.escapeTime(-0.76,0.2));
    }

    @Test
    public void testFractalType() {
        assertEquals(Fractal.FractalType.MANDELBROT, mandel.getType());
    }
}
