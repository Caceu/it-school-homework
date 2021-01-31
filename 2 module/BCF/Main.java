package com.company;

import java.io.PrintStream;
import java.util.*;

public class Main {
    public static PrintStream out = System.out;
    public static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        String input = in.next();
    }

    public double calculateFraction(double[] a, double[] b) {
        int last = b.length - 1;
        double cursor = b[last];
        for(int i = last - 1; i >= 0; i--)
            cursor = (a[i] / cursor) + b[i];
        return cursor;
    }
}
