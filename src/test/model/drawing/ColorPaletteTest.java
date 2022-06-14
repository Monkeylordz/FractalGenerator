package model.drawing;

import model.fractal.Fractal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class ColorPaletteTest {
    private ColorPalette palette;

    @BeforeEach
    public void before() {
        palette = new ColorPalette();
    }

    @Test
    public void testCustomPaletteConstructor() {
        try {
            palette = new ColorPalette("src/resources/64-red.png");
        } catch (IOException e) {
            fail();
        }

        int color = palette.getColor(1);
        assertEquals(Color.RED.getRGB(), color);
    }

    @Test
    public void testGetColor() {
        int color = palette.getColor(1);
        assertEquals(Color.WHITE.getRGB(), color);

        color = palette.getColor(Fractal.maxIteration);
        assertEquals(Color.BLACK.getRGB(), color);

        color = palette.getColor(155);
        assertEquals(-1, color);

        color = palette.getColor(-1);
        assertEquals(Color.WHITE.getRGB(), color);
    }

    @Test
    public void testSetPalette() {
        try {
            palette.setColorPalette("src/resources/64-red.png");
        } catch (IOException e) {
            fail();
        }

        int color = palette.getColor(1);
        assertEquals(Color.RED.getRGB(), color);
    }


}
