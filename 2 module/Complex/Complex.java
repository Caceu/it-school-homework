package com.company;

public class Complex {
    // Поля
    private double real, imaginary;

    // Конструкторы
    public Complex() {
        this.real = 0.;
        this.imaginary = 0.;
    }

    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex(Complex complex) {
        this.real = complex.getReal();
        this.imaginary = complex.getImaginary();
    }

    // Геттеры
    public double getReal() {
        return this.real;
    }

    public double getImaginary() {
        return this.imaginary;
    }

    // Арифметические методы
    public Complex add(Complex other) {
        Complex newComplex = new Complex(this.getReal(), this.getImaginary());
        newComplex.real += other.getReal();
        newComplex.imaginary += other.getImaginary();
        return newComplex;
    }

    public Complex subtract(Complex other) {
        Complex newComplex = new Complex(this.getReal(), this.getImaginary());
        newComplex.real -= other.getReal();
        newComplex.imaginary -= other.getImaginary();
        return newComplex;
    }

    public Complex multiply(Complex other) {
        Complex newComplex = new Complex();
        newComplex.real = this.real * other.real - this.imaginary * other.imaginary;
        newComplex.imaginary = this.real * other.imaginary + this.imaginary * other.real;
        return newComplex;
    }

    public Complex divide(Complex other) {
        Complex newComplex = new Complex();
        newComplex.real = (this.real * other.real + this.imaginary * other.imaginary) /
                (other.real * other.real + other.imaginary * other.imaginary);
        newComplex.imaginary = (this.imaginary * other.real - this.real * other.imaginary) /
                (other.real * other.real + other.imaginary * other.imaginary);
        return newComplex;
    }

    // Другие операции
    public double abs() {
        return Math.sqrt(this.real * this.real + this.imaginary * this.imaginary);
    }

    public Complex conjugate() {
        return new Complex(this.getReal(), -this.getImaginary());
    }

    public static boolean equals(Complex z1, Complex z2) {
        return z1.getReal() == z2.getReal() && z1.getImaginary() == z2.getImaginary();
    }

    // getReal() и getImaginary() объявлены выше

    public Complex neg() {
        return new Complex(-this.getReal(), -this.getImaginary());
    }

    @Override
    public String toString() {
        String sign = "";
        if (this.imaginary > 0) {
            sign = "+";
        }
        return String.format("%.3f%s%.3fi", this.real, sign, this.imaginary);
    }

    // Парсинг
    public static Complex parse(String str) throws NumberFormatException {
        int realEnd = -1, imaginaryStart = -1, imaginaryEnd = -1;
        Complex newComplex = new Complex();

        // Найдём конец действительной части числа, так как оно начинается с начала самой строки
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '.' && str.charAt(i) != ',' && !Character.isDigit(str.charAt(i))) {
                realEnd = i;
                newComplex.real = Double.parseDouble(str.substring(0, i));
                break;
            }
        }

        // Затем найдём начало и конец комплексной части числа
        for (int i = realEnd; i < str.length(); i++) {
            if (imaginaryStart == -1 &&
                    (str.charAt(i) == '.' || str.charAt(i) == ',' || Character.isDigit(str.charAt(i)))) {
                imaginaryStart = i;
            }
            if (imaginaryStart != -1 && str.charAt(i) != '.' && str.charAt(i) != ',' && !Character.isDigit(str.charAt(i))) {
                imaginaryEnd = i;
                newComplex.imaginary = Double.parseDouble(str.substring(imaginaryStart, imaginaryEnd));
                break;
            }
        }
        return newComplex;
    }

    public double getArgument() {
        return Math.atan(this.imaginary / this.real);
    }

    public Complex pow(double n) {
        double rPowered = Math.pow(this.abs(), n);
        double phi = this.getArgument();
        return new Complex(rPowered * Math.cos(n * phi), rPowered * Math.sin(n * phi));
    }

    public Complex sqrt() {
        return this.pow(0.5);
    }

    public String eString() {
        return String.format("%1$.3f (cos %2$.3f + i*sin %2$.3f)", this.abs(), this.getArgument());
    }

    public boolean checkReal() {
        return this.imaginary == 0;
    }

    public boolean checkImaginary() {
        return this.imaginary != 0;
    }
}
