package model.drawing;

import model.fractal.Fractal;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ColorPalette {
    private BufferedImage palette;

    public ColorPalette() {
        try {
            setColorPalette("src/resources/default.png");
            // setColorPalette("src/resources/palette_bright1.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ColorPalette(String fileName) throws IOException {
        setColorPalette(fileName);
    }

    public void setColorPalette(String fileName) throws IOException {
        File imageFile = new File(fileName);
        palette = new BufferedImage(1,1,BufferedImage.TYPE_INT_RGB);
        palette = ImageIO.read(imageFile);

    }

    public int getColor(int index) {
        int scaledLocation;
        scaledLocation = (int) ((((double) palette.getWidth()) / Fractal.maxIteration) * index);
        if (scaledLocation < 0) {
            scaledLocation = 0;
        } else if (scaledLocation >= palette.getWidth()) {
            scaledLocation = palette.getWidth()-1;
        }
        int rgb = palette.getRGB(scaledLocation, 0);
        return rgb;
    }

}

