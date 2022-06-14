package math;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComplexNumberTest {
    ComplexNumber c1;
    ComplexNumber c2;

    @BeforeEach
    public void before() {
        c1 = new ComplexNumber();
        c2 = new ComplexNumber(2.5, -4.83);
    }

    @Test
    public void testConstructor() {
        assertEquals(0, c1.getReal());
        assertEquals(0, c1.getImaginary());

        assertEquals(2.5, c2.getReal());
        assertEquals(-4.83, c2.getImaginary());
    }

    @Test
    public void testAddWithComplex() {
        c1.addWithComplex(c2);
        assertEquals(2.5, c1.getReal());
        assertEquals(-4.83, c1.getImaginary());

        c2.addWithComplex(c1);
        assertEquals(5.0, c2.getReal());
        assertEquals(-9.66, c2.getImaginary());
    }

    @Test
    public void testSubtractByComplex() {
        c1.subtractByComplex(c2);
        assertEquals(-2.5, c1.getReal());
        assertEquals(4.83, c1.getImaginary());

        c2.subtractByComplex(c1);
        assertEquals(5.0, c2.getReal());
        assertEquals(-9.66, c2.getImaginary());
    }

    @Test
    public void testMultiplyByConstant() {
        c1.multiplyByConstant(3.8);
        assertEquals(0, c1.getReal());
        assertEquals(0, c1.getImaginary());

        c2.multiplyByConstant(3.8);
        assertEquals(9.5, c2.getReal());
        assertEquals(-18.354, c2.getImaginary());
    }

    @Test
    public void testMultiplyByComplex() {
        c1.multiplyByComplex(c2);
        assertEquals(0, c1.getReal());
        assertEquals(0, c1.getImaginary());

        ComplexNumber c3 = new ComplexNumber(0.55, 2.56);
        c2.multiplyByComplex(c3);
        assertEquals(13.7398, c2.getReal());
        assertEquals(3.7435, c2.getImaginary());
    }

    @Test
    public void testMultiplyComplexes() {
        ComplexNumber c3 = new ComplexNumber(0.55, 2.56);
        ComplexNumber cResult = ComplexNumber.multiplyComplexes(c2, c3);
        assertEquals(13.7398, cResult.getReal());
        assertEquals(3.7435, cResult.getImaginary());
    }

    @Test
    public void testPow() {
        c2.pow(1);
        assertEquals(2.5, c2.getReal());
        assertEquals(-4.83, c2.getImaginary());

        c2.pow(2);
        assertEquals(-17.0789, c2.getReal());
        assertEquals(-24.15, c2.getImaginary());
    }

    @Test
    public void testCopyOf() {
        ComplexNumber c3 = ComplexNumber.copyOf(c1);
        assertEquals(0, c3.getReal());
        assertEquals(0, c3.getImaginary());

        c3 = ComplexNumber.copyOf(c2);
        assertEquals(2.5, c3.getReal());
        assertEquals(-4.83, c3.getImaginary());
    }

    @Test
    public void testToString() {
        assertEquals("0.0 + (0.0)i", c1.toString());
        assertEquals("2.5 + (-4.83)i", c2.toString());
    }

    @Test
    public void testSet() {
        c1.setReal(1.1);
        c1.setImaginary(2.2);
        assertEquals(1.1, c1.getReal());
        assertEquals(2.2, c1.getImaginary());
    }

    @Test
    public void testHashCode() {
        assertEquals(961, c1.hashCode());
        assertEquals(92925306, c2.hashCode());
    }

}