package com.company;

import java.io.PrintStream;

public class NashCalculator {
    // Сделал небольшой класс, чтобы было легче использовать эти функции и скидывать файл
    public static void printMatrix(boolean[][] matrix, PrintStream out) {
        for (boolean[] booleans : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                out.print(booleans[j] + "\t");
            }
            out.print('\n');
        }
    }

    public static void printMatrix(int[][] matrix, PrintStream out) {
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                out.print(ints[j] + "\t");
            }
            out.print('\n');
        }
    }

    public static boolean[][] calculate(int[][] rulesA, int[][] rulesB, boolean isMin) {
        if (isMin) {
            int[] minValues = new int[rulesA.length];
            for (int i = 0; i < rulesA[0].length; i++) {
                int min = rulesA[0][i];
                for (int j = 1; j < rulesA.length; j++) {
                    if (rulesA[j][i] < min) {
                        min = rulesA[j][i];
                    }
                }
                minValues[i] = min;
            }
            boolean[][] aOptimal = new boolean[rulesA.length][rulesA[0].length];
            for (int i = 0; i < rulesA[0].length; i++) {
                for (int j = 0; j < rulesA.length; j++) {
                    aOptimal[j][i] = (rulesA[j][i] == minValues[i]);
                }
            }

            minValues = new int[rulesB.length];
            for (int i = 0; i < rulesB.length; i++) {
                int min = rulesB[i][0];
                for (int j = 1; j < rulesB[0].length; j++) {
                    if(rulesB[i][j] < min) {
                        min = rulesB[i][j];
                    }
                }
                minValues[i] = min;
            }

            boolean[][] output = new boolean[rulesB.length][rulesB[0].length];
            for (int i = 0; i < rulesB.length; i++) {
                for (int j = 0; j < rulesB[0].length; j++) {
                    output[i][j] = (rulesB[i][j] == minValues[i]) && aOptimal[i][j];
                }
            }
            return output;
        }
        else {
            int[] maxValues = new int[rulesA.length];
            for (int i = 0; i < rulesA[0].length; i++) {
                int max = rulesA[0][i];
                for (int j = 1; j < rulesA.length; j++) {
                    if (rulesA[j][i] > max) {
                        max = rulesA[j][i];
                    }
                }
                maxValues[i] = max;
            }

            boolean[][] aOptimal = new boolean[rulesA.length][rulesA[0].length];
            for (int i = 0; i < rulesA[0].length; i++) {
                for (int j = 0; j < rulesA.length; j++) {
                    aOptimal[j][i] = (rulesA[j][i] == maxValues[i]);
                }
            }

            maxValues = new int[rulesB.length];
            for (int i = 0; i < rulesB.length; i++) {
                int max = rulesB[i][0];
                for (int j = 1; j < rulesB[0].length; j++) {
                    if(rulesB[i][j] > max) {
                        max = rulesB[i][j];
                    }
                }
                maxValues[i] = max;
            }

            boolean[][] output = new boolean[rulesB.length][rulesB[0].length];
            for (int i = 0; i < rulesB.length; i++) {
                for (int j = 0; j < rulesB[0].length; j++) {
                    output[i][j] = (rulesB[i][j] == maxValues[i]) && aOptimal[i][j];
                }
            }
            return output;
        }
    }
}
