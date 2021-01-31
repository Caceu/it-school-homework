package com.company;

import java.io.PrintStream;
import java.util.*;

public class Main {
    public static PrintStream out = System.out;
    public static Scanner in = new Scanner(System.in);
    public static void main(String[] args) {
        out.print(fib(in.nextInt()));
    }

    public static Map<Integer, Integer> cache = new HashMap<Integer, Integer>();

    public static int fib(int step) {
        if(step == 0) {
            return 0;
        }
        else if (step == 1) {
            return 1;
        }
        else {
            int out;
            if(cache.containsKey(step)){
                out = cache.get(step);
            }
            else {
                out = fib(step - 1) + fib(step - 2);
                cache.put(step, out);
            }
            return out;
        }
    }
}
