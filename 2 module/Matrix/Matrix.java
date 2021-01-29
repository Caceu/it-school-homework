package com.company;

import jdk.jshell.spi.ExecutionControl;

import java.util.Arrays;
import java.util.Random;

public class Matrix {
    // Не знаю, как реализовать некоторые функции под дженерики, например сложение и вычисления, так что сделал всё с
    // интами.

    public int[] dimension;
    private int[][] data;
    public Matrix() {
        this.dimension = new int[]{0, 0};
    }

    public Matrix(int[] m, int[] n) {
        this.dimension = new int[]{m.length, n.length};
        // Я не знаю, как из двух массивов разной (?) длины сделать квадратную матрицу, я что-то не так понял
    }

    public Matrix(int[][] m) {
        this.data = m.clone();
        this.dimension = new int[]{m.length, m[0].length};
    }

    public Matrix(Matrix c) {
        this.data = c.data.clone();
        this.dimension = c.dimension.clone();
    }

    public Matrix add(Matrix other) throws IndexOutOfBoundsException {
        int m = this.dimension[0];
        int n = this.dimension[1];
        if(m == other.dimension[0] && n == other.dimension[1]){

            int[][] newData = new int[m][n];
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    newData[i][j] = this.data[i][j] + other.data[i][j];
                }
            }

            return new Matrix(newData);
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }

    public Matrix subtract(Matrix other) throws IndexOutOfBoundsException {
        int m = this.dimension[0];
        int n = this.dimension[1];
        if(m == other.dimension[0] && n == other.dimension[1]){

            int[][] newData = new int[m][n];
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    newData[i][j] = this.data[i][j] - other.data[i][j];
                }
            }

            return new Matrix(newData);
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }

    public Matrix multiply(Matrix other) throws IndexOutOfBoundsException {
        int m = this.dimension[0];
        int n = this.dimension[1];
        if(m == other.dimension[0] && n == other.dimension[1]){

            int[][] newData = new int[m][n];
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    newData[i][j] = this.data[i][j] * other.data[i][j];
                }
            }

            return new Matrix(newData);
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }

    public Matrix divide(Matrix other) throws IndexOutOfBoundsException {
        int m = this.dimension[0];
        int n = this.dimension[1];
        if(m == other.dimension[0] && n == other.dimension[1]){

            int[][] newData = new int[m][n];
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    newData[i][j] = this.data[i][j] / other.data[i][j];
                }
            }
            return new Matrix(newData);
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }

    public static Matrix zeros(int[] dims) {
        int[][] newData = new int[dims[0]][dims[1]];
        for(int i = 0; i < dims[0]; i++) {
            for (int j = 0; j < dims[1]; j++) {
                newData[i][j] = 0;
            }
        }
        return new Matrix(newData);
    }

    public static Matrix ones(int[] dims) {
        int[][] newData = new int[dims[0]][dims[1]];
        for(int i = 0; i < dims[0]; i++) {
            for (int j = 0; j < dims[1]; j++) {
                if(i != j)
                    newData[i][j] = 0;
                else
                    newData[i][j] = 1;
            }
        }
        return new Matrix(newData);
    }

    public static Matrix range(int[] dims) {
        int[][] newData = new int[dims[0]][dims[1]];
        int counter = 0;
        for(int i = 0; i < dims[0]; i++) {
            for (int j = 0; j < dims[1]; j++) {
                newData[i][j] = counter++;
            }
        }
        return new Matrix(newData);
    }

    public static Matrix random(int[] dims) {
        int[][] newData = new int[dims[0]][dims[1]];
        Random rand = new Random();
        int counter = 0;
        for(int i = 0; i < dims[0]; i++) {
            for (int j = 0; j < dims[1]; j++) {
                newData[i][j] = rand.nextInt(2);
            }
        }
        return new Matrix(newData);
    }

    @Override
    public String toString() {
        return "Matrix{" +
                "data=" + Arrays.toString(data) +
                '}';
    }

    public String toLines() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < this.dimension[0]; i++) {
            for (int j = 0; j < this.dimension[1]; j++) {
                output.append(String.format("%5d", this.data[i][j]));
            }
            output.append('\n');
        }
        return output.toString();
    }

    public int sum() {
        int out = 0;
        for (int i = 0; i < this.dimension[0]; i++)
            for (int j = 0; j < this.dimension[1]; j++)
                out += this.data[i][j];

        return out;
    }

    public int trace() {
        int out = 0;
        int minDim = Math.min(this.dimension[0], this.dimension[1]);
        for (int i = 0; i < minDim; i++) {
            out += this.data[i][i];
        }
        return out;
    }

    public int getElement(int index) throws IndexOutOfBoundsException{
        if(index < this.dimension[0] * this.dimension[1]){
            throw new IndexOutOfBoundsException();
        }

        int x = index / this.dimension[0];
        int y = index % this.dimension[0];

        return this.data[x][y];
    }

    public int getElement(int indexX, int indexY) throws IndexOutOfBoundsException {
        if(indexY >= this.dimension[0] || indexX >= this.dimension[1]){
            throw new IndexOutOfBoundsException();
        }

        return this.data[indexY][indexX];
    }

    public int getN() {
        return this.dimension[0] * this.dimension[1];
        // Не знаю, что оно может ещё значить...
    }

    public Matrix[] split(int count, int newN) {
        // Я не знаю, как разделять эти матрицы
        return new Matrix[0];
    }

    public int argmax() {
        int maxIndex = 0;
        int maxItem = this.data[0][0];
        for (int i = 0; i < this.dimension[0]; i++) {
            for (int j = 0; j < this.dimension[1]; j++) {
                if(this.data[i][j] > maxItem) {
                    maxIndex = i * this.dimension[0] + j;
                    maxItem = this.data[i][j];
                }
            }
        }
        return maxIndex;
    }

    public int argmin() {
        int minIndex = 0;
        int minItem = this.data[0][0];
        for (int i = 0; i < this.dimension[0]; i++) {
            for (int j = 0; j < this.dimension[1]; j++) {
                if(this.data[i][j] < minItem) {
                    minIndex = i * this.dimension[0] + j;
                    minItem = this.data[i][j];
                }
            }
        }
        return minIndex;
    }

    public int max() {
        int maxItem = this.data[0][0];
        for (int i = 0; i < this.dimension[0]; i++) {
            for (int j = 0; j < this.dimension[1]; j++) {
                if(this.data[i][j] > maxItem) {
                    maxItem = this.data[i][j];
                }
            }
        }
        return maxItem;
    }

    public int min() {
        int minItem = this.data[0][0];
        for (int i = 0; i < this.dimension[0]; i++) {
            for (int j = 0; j < this.dimension[1]; j++) {
                if(this.data[i][j] < minItem) {
                    minItem = this.data[i][j];
                }
            }
        }
        return minItem;
    }

    public void isDiagonal() {
        // Я не знаю, что эта функция должна делать :(
    }

    public int search(int value) {
        for (int i = 0; i < this.dimension[0]; i++) {
            for (int j = 0; j < this.dimension[1]; j++) {
                if(value == this.data[i][j]) {
                    return i * this.dimension[0] + j;
                }
            }
        }
        return -1;
    }

    public double mean() {
        return ((double) this.sum()) / ((double) this.getN());
    }

    public Matrix transpose() {
        int[][] newData = new int[this.dimension[1]][this.dimension[0]];
        for (int i = 0; i < this.dimension[0]; i++) {
            for (int j = 0; j < this.dimension[1]; j++) {
                newData[j][i] = this.data[i][j];
            }
        }
        return new Matrix(newData);
    }
}
