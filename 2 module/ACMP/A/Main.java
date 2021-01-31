package com.company;

import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static PrintStream out = System.out;
    public static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        int n = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[n - i - 1] = in.nextInt();
        }
        for (int i = 0; i < n; i++) {
            out.println(a[i]);
        }
    }
}
