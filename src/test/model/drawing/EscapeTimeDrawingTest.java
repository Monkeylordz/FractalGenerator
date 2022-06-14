package model.drawing;

import model.fractal.Mandelbrot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class EscapeTimeDrawingTest {
    EscapeTimeDrawing drawing;
    Mandelbrot mandel;

    @BeforeEach
    public void before() {
        mandel = new Mandelbrot();
        drawing = new EscapeTimeDrawing(mandel);
    }

    @Test
    public void testConstructor() {
        assertEquals(mandel, drawing.getFractal());
        assertEquals(700, drawing.getWidth());
        assertEquals(400, drawing.getHeight());
        assertEquals(0, drawing.getXCoord());
        assertEquals(0, drawing.getYCoord());
        assertEquals(1, drawing.getZoom());
    }

    @Test
    public void testConstructorCustom() {
        drawing = new EscapeTimeDrawing(mandel, 735, 420, -0.75, 0.1, 5);
        assertEquals(mandel, drawing.getFractal());
        assertEquals(735, drawing.getWidth());
        assertEquals(420, drawing.getHeight());
        assertEquals(-0.75, drawing.getXCoord());
        assertEquals(0.1, drawing.getYCoord());
        assertEquals(5, drawing.getZoom());
    }

    @Test
    public void testDraw() {
        drawing.draw();
        assertTrue(drawing.getImage() != null);
        assertEquals(700, drawing.getImage().getWidth());
        assertEquals(400, drawing.getImage().getHeight());
    }

    @Test
    public void testZoomInNoParams() {
        drawing.zoomIn();
        assertEquals(5, drawing.getZoom());
        assertEquals(0, drawing.getXCoord());
        assertEquals(0, drawing.getYCoord());

        drawing.zoomIn();
        assertEquals(25, drawing.getZoom());
    }

    @Test
    public void testZoomInWithParams() {
        drawing.zoomIn(37, 58);
        assertEquals(5, drawing.getZoom());
        assertEquals(-1.565, drawing.getXCoord());
        assertEquals(-0.71, drawing.getYCoord());
    }

    @Test
    public void testZoomOut() {
        drawing.zoomOut();
        assertEquals(1.0 / 5, drawing.getZoom());
        assertEquals(0, drawing.getXCoord());
        assertEquals(0, drawing.getYCoord());
    }

    @Test
    public void testSetPalette() {
        try {
            drawing.setPalette(new ColorPalette("src/resources/64-red.png"));
        } catch (IOException e) {
            fail();
        }
    }

}
