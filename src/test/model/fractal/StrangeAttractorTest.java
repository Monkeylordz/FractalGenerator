package model.fractal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StrangeAttractorTest {
    StrangeAttractor attractor1;
    StrangeAttractor attractor2;

    @BeforeEach
    public void before() {
        attractor1 = new StrangeAttractor(1.0, 1.0, 1, 1, 1, 1);
        attractor2 = new StrangeAttractor(-0.1, 0.1, 0.56, -1.9, 0.73, 1.4);
    }

    @Test
    public void testConstructor() {
        assertEquals(1.0, attractor1.getX());
        assertEquals(1.0, attractor1.getY());
        assertEquals(1.0, attractor1.getA());
        assertEquals(1.0, attractor1.getB());
        assertEquals(1.0, attractor1.getC());
        assertEquals(1.0, attractor1.getD());

        assertEquals(-0.1, attractor2.getX());
        assertEquals(0.1, attractor2.getY());
        assertEquals(0.56, attractor2.getA());
        assertEquals(-1.9, attractor2.getB());
        assertEquals(0.73, attractor2.getC());
        assertEquals(1.4, attractor2.getD());
    }

    @Test
    public void testIterate() {
        attractor1.iterate();
        assertEquals(1.682941969615793, attractor1.getX());
        assertEquals(1.682941969615793, attractor1.getY());

        attractor2.iterate();
        assertEquals(-0.05099190164365516, attractor2.getX());
        assertEquals(0.022388294102302184, attractor2.getY());
    }

    @Test
    public void testFractalType() {
        assertEquals(Fractal.FractalType.ATTRACTOR, attractor1.getType());
    }
}
