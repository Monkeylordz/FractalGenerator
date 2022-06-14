package ui;

import math.ComplexNumber;
import model.drawing.AttractorDrawing;
import model.drawing.Drawing;
import model.drawing.EscapeTimeDrawing;
import model.fractal.*;

import java.util.ArrayList;

public class SettingsParser {
    public static Drawing parseSettings(SettingsDialog settings) {
        ArrayList<String> values = settings.getFieldValues();
        try {
            switch (settings.getType()) {
                case MANDELBROT: return parseMandel(values);
                case MULTIBROT: return parseMulti(values);
                case JULIA: return parseJulia(values);
                case ATTRACTOR: return parseAttractor(values);
                default: return null;
            }
        } catch (NumberFormatException exception) {
            // Parsing error
            return null;
        }
    }

    private static Drawing parseMandel(ArrayList<String> values) {
        int width = Integer.parseInt(values.get(0));
        int height = Integer.parseInt(values.get(1));
        double x = Double.parseDouble(values.get(2));
        double y = Double.parseDouble(values.get(3));
        double zoom = Double.parseDouble(values.get(4));
        Mandelbrot mandel = new Mandelbrot();
        return new EscapeTimeDrawing(mandel, width, height, x, y, zoom);
    }

    private static Drawing parseJulia(ArrayList<String> values) {
        int width = Integer.parseInt(values.get(0));
        int height = Integer.parseInt(values.get(1));
        int dims = Integer.parseInt(values.get(2));
        double c1 = Double.parseDouble(values.get(3));
        double c2 = Double.parseDouble(values.get(4));
        double x = Double.parseDouble(values.get(5));
        double y = Double.parseDouble(values.get(6));
        double zoom = Double.parseDouble(values.get(7));
        Julia julia = new Julia(dims, new ComplexNumber(c1, c2));
        return new EscapeTimeDrawing(julia, width, height, x, y, zoom);
    }

    private static Drawing parseAttractor(ArrayList<String> values) {
        int width = Integer.parseInt(values.get(0));
        int height = Integer.parseInt(values.get(1));
        double x = Double.parseDouble(values.get(2));
        double y = Double.parseDouble(values.get(3));
        double a = Double.parseDouble(values.get(4));
        double b = Double.parseDouble(values.get(5));
        double c = Double.parseDouble(values.get(6));
        double d = Double.parseDouble(values.get(7));
        StrangeAttractor attractor = new StrangeAttractor(x, y, a, b, c, d);
        return new AttractorDrawing(attractor, width, height);
    }

    private static Drawing parseMulti(ArrayList<String> values) {
        int width = Integer.parseInt(values.get(0));
        int height = Integer.parseInt(values.get(1));
        int dims = Integer.parseInt(values.get(2));
        double x = Double.parseDouble(values.get(3));
        double y = Double.parseDouble(values.get(4));
        double zoom = Double.parseDouble(values.get(5));
        Multibrot multi = new Multibrot(dims);
        return new EscapeTimeDrawing(multi, width, height, x, y, zoom);
    }

    public static SettingsDialog parseDrawing(Drawing d) {
        if (d != null) {
            switch (d.getFractal().getType()) {
                case MANDELBROT:
                    return mandelToSettings(d);
                case MULTIBROT:
                    return multiToSettings(d);
                case JULIA:
                    return juliaToSettings(d);
                case ATTRACTOR:
                    return attractorToSettings(d);
            }
        }
        return new SettingsDialog();
    }

    private static SettingsDialog mandelToSettings(Drawing d) {
        String w = "" + d.getWidth();
        String h = "" + d.getHeight();
        String x = "" + d.getXCoord();
        String y = "" + d.getYCoord();
        String z = "" + d.getZoom();
        SettingsDialog settings = new SettingsDialog(
                Fractal.FractalType.MANDELBROT,
                FractalGenerator.MANDEL_OPTIONS,
                new String[]{w, h, x, y, z});
        return settings;
    }

    private static SettingsDialog multiToSettings(Drawing d) {
        String w = "" + d.getWidth();
        String h = "" + d.getHeight();
        String dim = "" + ((Multibrot) d.getFractal()).getDimensions();
        String x = "" + d.getXCoord();
        String y = "" + d.getYCoord();
        String z = "" + d.getZoom();
        SettingsDialog settings = new SettingsDialog(
                Fractal.FractalType.MULTIBROT,
                FractalGenerator.MULTI_OPTIONS,
                new String[]{w, h, dim, x, y, z});
        return settings;
    }

    private static SettingsDialog juliaToSettings(Drawing d) {
        String w = "" + d.getWidth();
        String h = "" + d.getHeight();
        String dim = "" + ((Julia) d.getFractal()).getDimensions();
        String cr = "" + ((Julia) d.getFractal()).getComplex().getReal();
        String ci = "" + ((Julia) d.getFractal()).getComplex().getImaginary();
        String x = "" + d.getXCoord();
        String y = "" + d.getYCoord();
        String z = "" + d.getZoom();
        SettingsDialog settings = new SettingsDialog(
                Fractal.FractalType.JULIA,
                FractalGenerator.JULIA_OPTIONS,
                new String[]{w, h, dim, cr, ci, x, y, z});
        return settings;
    }

    private static SettingsDialog attractorToSettings(Drawing drawing) {
        String w = "" + drawing.getWidth();
        String h = "" + drawing.getHeight();
        String x = "" + ((StrangeAttractor) drawing.getFractal()).getX();
        String y = "" + ((StrangeAttractor) drawing.getFractal()).getY();
        String a = "" + ((StrangeAttractor) drawing.getFractal()).getA();
        String b = "" + ((StrangeAttractor) drawing.getFractal()).getB();
        String c = "" + ((StrangeAttractor) drawing.getFractal()).getC();
        String d = "" + ((StrangeAttractor) drawing.getFractal()).getD();

        SettingsDialog settings = new SettingsDialog(
                Fractal.FractalType.ATTRACTOR,
                FractalGenerator.ATTRACTOR_OPTIONS,
                new String[]{w, h, x, y, a, b, c, d});
        return settings;
    }
}
