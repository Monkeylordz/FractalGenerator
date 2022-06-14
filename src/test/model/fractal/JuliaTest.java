package model.fractal;

import math.ComplexNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JuliaTest {
    Julia julia1;
    Julia julia2;

    @BeforeEach
    public void before() {
        julia1 = new Julia(2, new ComplexNumber(0.1, -0.1));
        julia2 = new Julia(3, new ComplexNumber(0.3, -0.5));
    }

    @Test
    public void testConstructor() {
        assertEquals(2, julia1.getDimensions());
        assertEquals(3, julia2.getDimensions());

        assertEquals(new ComplexNumber(0.1, -0.1), julia1.getComplex());
        assertEquals(new ComplexNumber(0.3, -0.5), julia2.getComplex());
    }

    @Test
    public void testEscapeTime() {
        assertEquals(Fractal.maxIteration, julia1.escapeTime(0,0));
        assertEquals(8, julia1.escapeTime(-0.905,-0.095));

        assertEquals(Fractal.maxIteration, julia2.escapeTime(0,0));
        assertEquals(5, julia2.escapeTime(-0.905,-0.095));
    }

    @Test
    public void testFractalType() {
        assertEquals(Fractal.FractalType.JULIA, julia1.getType());
    }
}
