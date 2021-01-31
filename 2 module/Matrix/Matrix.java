package com.company;

import jdk.jshell.spi.ExecutionControl;

import java.util.Arrays;
import java.util.Random;

public class Matrix<T extends Number> {
    // Не знаю, как реализовать некоторые функции под дженерики, например сложение и вычисления, так что сделал всё с
    // интами.

    private static final double EPSILON = 1e-6;

    public int[] dimension;
    private T[][] data;
    public Matrix() {
        this.dimension = new int[]{0, 0};
    }

    public Matrix(int[] m, int[] n) {
        this.dimension = new int[]{m.length, n.length};
        // Я не знаю, как из двух массивов разной (?) длины сделать квадратную матрицу, я что-то не так понял
    }

    public Matrix(T[][] m) {
        this.data = m.clone();
        this.dimension = new int[]{m.length, m[0].length};
    }

    public Matrix(Matrix<T> c) {
        this.data = c.data.clone();
        this.dimension = c.dimension.clone();
    }

    public Matrix<T> add(Matrix other) throws IndexOutOfBoundsException {
        int m = this.dimension[0];
        int n = this.dimension[1];
        if(m == other.dimension[0] && n == other.dimension[1]){

            Matrix<T> newMatrix = new Matrix<T>(this.data);
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    newMatrix.data[i][j] = (T)(Object)((double) this.data[i][j] + (double)other.data[i][j]);
                }
            }

            return newMatrix;
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }

    public Matrix<T> subtract(Matrix<T> other) throws IndexOutOfBoundsException {
        int m = this.dimension[0];
        int n = this.dimension[1];
        if(m == other.dimension[0] && n == other.dimension[1]){

            Matrix<T> newMatrix = new Matrix<T>(this.data);
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    newMatrix.data[i][j] = (T)(Object)((double) this.data[i][j] - (double)other.data[i][j]);
                }
            }

            return newMatrix;
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }

    public Matrix<T> multiply(Matrix<T> other) throws IndexOutOfBoundsException {
        int m = this.dimension[0];
        int n = this.dimension[1];
        if(m == other.dimension[0] && n == other.dimension[1]){

            Matrix<T> newMatrix = new Matrix<T>(this.data);
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    newMatrix.data[i][j] = (T)(Object)((double) this.data[i][j] * (double)other.data[i][j]);
                }
            }

            return newMatrix;
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }

    public Matrix<T> divide(Matrix<T> other) throws IndexOutOfBoundsException {
        int m = this.dimension[0];
        int n = this.dimension[1];
        if(m == other.dimension[0] && n == other.dimension[1]){

            Matrix<T> newMatrix = new Matrix<T>(this.data);
            for(int i = 0; i < m; i++) {
                for(int j = 0; j < n; j++) {
                    newMatrix.data[i][j] = (T)(Object)((double) this.data[i][j] / (double)other.data[i][j]);
                }
            }
            return newMatrix;
        }
        else {
            throw new IndexOutOfBoundsException();
        }
    }

    public static Matrix<Double> zeros(int[] dims) {
        Double[][] newData = new Double[dims[0]][dims[1]];
        for(int i = 0; i < dims[0]; i++) {
            for (int j = 0; j < dims[1]; j++) {
                newData[i][j] = 0d;
            }
        }
        return new Matrix<Double>(newData);
    }

    public static Matrix<Double> ones(int[] dims) {
        Double[][] newData = new Double[dims[0]][dims[1]];
        for(int i = 0; i < dims[0]; i++) {
            for (int j = 0; j < dims[1]; j++) {
                if(i != j)
                    newData[i][j] = 0d;
                else
                    newData[i][j] = 1d;
            }
        }
        return new Matrix<Double>(newData);
    }

    public static Matrix<Double> range(int[] dims) {
        Double[][] newData = new Double[dims[0]][dims[1]];
        int counter = 0;
        for(int i = 0; i < dims[0]; i++) {
            for (int j = 0; j < dims[1]; j++) {
                newData[i][j] = (double)counter++;
            }
        }
        return new Matrix<Double>(newData);
    }

    public static Matrix<Double> random(int[] dims) {
        Double[][] newData = new Double[dims[0]][dims[1]];
        for(int i = 0; i < dims[0]; i++) {
            for (int j = 0; j < dims[1]; j++) {
                newData[i][j] = Math.random();
            }
        }
        return new Matrix<Double>(newData);
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
                output.append(String.format("%5s", String.valueOf(this.data[i][j])));
            }
            output.append('\n');
        }
        return output.toString();
    }

    public double sum() {
        double out = 0;
        for (int i = 0; i < this.dimension[0]; i++)
            for (int j = 0; j < this.dimension[1]; j++)
                out += (double)this.data[i][j];

        return out;
    }

    public double trace() {
        double out = 0;
        int minDim = Math.min(this.dimension[0], this.dimension[1]);
        for (int i = 0; i < minDim; i++) {
            out += (double)this.data[i][i];
        }
        return out;
    }

    public T getElement(int index) throws IndexOutOfBoundsException{
        if(index < this.dimension[0] * this.dimension[1]){
            throw new IndexOutOfBoundsException();
        }

        int x = index / this.dimension[0];
        int y = index % this.dimension[0];

        return this.data[x][y];
    }

    public T getElement(int indexX, int indexY) throws IndexOutOfBoundsException {
        if(indexY >= this.dimension[0] || indexX >= this.dimension[1]){
            throw new IndexOutOfBoundsException();
        }

        return this.data[indexY][indexX];
    }

    public int getN() {
        return this.dimension[0] * this.dimension[1];
        // Не знаю, что оно может ещё значить...
    }

    public Matrix<T>[] split(int count, int newN) {
        // Я не знаю, как разделять эти матрицы (по какому критерию)
        return null;
    }

    public int argmax() {
        int maxIndex = 0;
        T maxItem = this.data[0][0];
        for (int i = 0; i < this.dimension[0]; i++) {
            for (int j = 0; j < this.dimension[1]; j++) {
                if((double)this.data[i][j] > (double)maxItem) {
                    maxIndex = i * this.dimension[0] + j;
                    maxItem = this.data[i][j];
                }
            }
        }
        return maxIndex;
    }

    public int argmin() {
        int minIndex = 0;
        T minItem = this.data[0][0];
        for (int i = 0; i < this.dimension[0]; i++) {
            for (int j = 0; j < this.dimension[1]; j++) {
                if((double)this.data[i][j] < (double)minItem) {
                    minIndex = i * this.dimension[0] + j;
                    minItem = this.data[i][j];
                }
            }
        }
        return minIndex;
    }

    public T max() {
        T maxItem = this.data[0][0];
        for (int i = 0; i < this.dimension[0]; i++) {
            for (int j = 0; j < this.dimension[1]; j++) {
                if((double)this.data[i][j] > (double)maxItem) {
                    maxItem = this.data[i][j];
                }
            }
        }
        return maxItem;
    }

    public T min() {
        T minItem = this.data[0][0];
        for (int i = 0; i < this.dimension[0]; i++) {
            for (int j = 0; j < this.dimension[1]; j++) {
                if((double)this.data[i][j] < (double) minItem) {
                    minItem = this.data[i][j];
                }
            }
        }
        return minItem;
    }

    public void isDiagonal() {
        // Я не знаю, что эта функция должна делать :(
    }

    public int search(T value) {
        for (int i = 0; i < this.dimension[0]; i++) {
            for (int j = 0; j < this.dimension[1]; j++) {
                if((double)value - (double) this.data[i][j] < EPSILON) {
                    return i * this.dimension[0] + j;
                }
            }
        }
        return -1;
    }

    public double mean() {
        return ((double) this.sum()) / ((double) this.getN());
    }

    public Matrix<T> transpose() {
        T[][] newData = (T[][])new Double[this.dimension[1]][this.dimension[0]];
        for (int i = 0; i < this.dimension[0]; i++) {
            for (int j = 0; j < this.dimension[1]; j++) {
                newData[j][i] = this.data[i][j];
            }
        }
        return new Matrix<T>(newData);
    }
}
