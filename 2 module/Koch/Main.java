package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println(calculateArea(10));
    }

    public static double calculateArea(int n) {
        if(n == 1) {
            return 1;
        }
        else {
            return 1 + calculateArea(n - 1, 1);
        }
    }

    public static double calculateArea(int n, int step) {
        if(n == step) {
            return 3 / Math.pow(9, step);
        }
        else {
            return 3 / Math.pow(9, step) + calculateArea(n, step + 1);
        }
    }
}
