package model.fractal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultibrotTest {
    Multibrot multi2;
    Multibrot multi3;

    @BeforeEach
    public void before() {
        multi2 = new Multibrot(2);
        multi3 = new Multibrot(3);
    }

    @Test
    public void testConstructor() {
        assertEquals(2, multi2.getDimensions());
        assertEquals(3, multi3.getDimensions());
    }

    @Test
    public void testEscapeTime() {
        assertEquals(Fractal.maxIteration, multi2.escapeTime(0,0));
        assertEquals(17, multi2.escapeTime(-0.76,0.2));

        assertEquals(35, multi3.escapeTime(-0.526,-0.09));
        assertEquals(215, multi3.escapeTime(0.532703,0.400864));
    }

    @Test
    public void testFractalType() {
        assertEquals(Fractal.FractalType.MULTIBROT, multi2.getType());
    }
}
