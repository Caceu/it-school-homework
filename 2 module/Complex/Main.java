package com.company;

import java.io.PrintStream;
import java.util.Scanner;

public class Main {

    public static PrintStream out = System.out;
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        Complex z1 = new Complex(0.5, Math.sqrt(3) / 2);
        Complex z2 = new Complex(5, 9);
        out.println(z1.eString());
        out.println(z1.sqrt());
        out.println(z1.pow(20));
        out.println(z1.add(z2));
        out.println(z1.subtract(z2));
        out.println(z1.abs());
        out.println(z1.getArgument());
        out.println(z1.conjugate());
        out.println(z1.neg());

        Complex[] matrix = new Complex[5];
        for(int i = 0; i < 5; i++){
            matrix[i] = new Complex(z1);
            out.println(i + 1);
            out.println(matrix[i].multiply(z1));
        }

        out.println(Complex.parse(in.next()).getArgument());
    }
}