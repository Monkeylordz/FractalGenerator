package math;

import java.util.Objects;

public class ComplexNumber {
    private double real;
    private double imaginary;

    public ComplexNumber() {
        real = 0;
        imaginary = 0;
    }

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    // Effect: Adds complex to this
    public void addWithComplex(ComplexNumber complex) {
        real += complex.getReal();
        imaginary += complex.getImaginary();
    }

    // Effect: Subtracts this by complex
    public void subtractByComplex(ComplexNumber complex) {
        real -= complex.getReal();
        imaginary -= complex.getImaginary();
    }

    // Effect: Multiplies this by constant
    public void multiplyByConstant(double constant) {
        real *= constant;
        imaginary *= constant;
    }

    // Requires: complex cannot be to this
    // Effect: Multiplies this by complex
    public void multiplyByComplex(ComplexNumber complex) {
        double tempReal = real;
        double tempIm = imaginary;
        real = tempReal * complex.getReal() - tempIm * complex.getImaginary();
        imaginary = tempReal * complex.getImaginary() + tempIm * complex.getReal();
    }

    // Effect: Returns the product of the two input complex numbers
    public static ComplexNumber multiplyComplexes(ComplexNumber c1, ComplexNumber c2) {
        ComplexNumber product = new ComplexNumber();
        product.setReal(c1.getReal() * c2.getReal() - c1.getImaginary() * c2.getImaginary());
        product.setImaginary(c1.getReal() * c2.getImaginary() + c1.getImaginary() * c2.getReal());
        return product;
    }

    // Effect: Multiplies this by itself power times
    public void pow(int power) {
        ComplexNumber tempComp = copyOf(this);
        for (int i = 1; i < power; i++) {
            multiplyByComplex(tempComp);
        }
    }

    public String toString() {
        return real + " + (" + imaginary + ")i";
    }

    // Effect: Returns a copy of the given complex number
    public static ComplexNumber copyOf(ComplexNumber c) {
        return new ComplexNumber(c.real, c.imaginary);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ComplexNumber that = (ComplexNumber) o;
        return Double.compare(that.real, real) == 0 &&
                Double.compare(that.imaginary, imaginary) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(real, imaginary);
    }

    public double getReal() {
        return real;
    }

    public double getImaginary() {
        return imaginary;
    }

    public void setReal(double real) {
        this.real = real;
    }

    public void setImaginary(double imaginary) {
        this.imaginary = imaginary;
    }
}
