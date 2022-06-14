package model.drawing;

import model.fractal.StrangeAttractor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AttractorDrawingTest {
    AttractorDrawing drawing;
    StrangeAttractor attractor;

    @BeforeEach
    public void before() {
        attractor = new StrangeAttractor(0.1,0.1,0.2,-0.7,-2.1,1.4);
        drawing = new AttractorDrawing(attractor);
    }

    @Test
    public void testConstructor() {
        assertEquals(attractor, drawing.getFractal());
        assertEquals(700, drawing.getWidth());
        assertEquals(400, drawing.getHeight());
        assertEquals(0, drawing.getXCoord());
        assertEquals(0, drawing.getYCoord());
        assertEquals(1, drawing.getZoom());
    }

    @Test
    public void testConstructorCustom() {
        drawing = new AttractorDrawing(attractor, 640, 640);
        assertEquals(attractor, drawing.getFractal());
        assertEquals(640, drawing.getWidth());
        assertEquals(640, drawing.getHeight());
        assertEquals(0, drawing.getXCoord());
        assertEquals(0, drawing.getYCoord());
        assertEquals(1, drawing.getZoom());
    }

    @Test
    public void testDraw() {
        drawing.draw();
        assertNotNull(drawing.getImage());
        assertEquals(700, drawing.getImage().getWidth());
        assertEquals(400, drawing.getImage().getHeight());
    }
}
