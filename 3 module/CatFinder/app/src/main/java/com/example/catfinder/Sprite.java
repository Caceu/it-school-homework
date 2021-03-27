package com.example.catfinder;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.util.Log;

public class Sprite {
    Bitmap flip_book;

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private final int width; // sprite width
    private final int height; // sprite height
    private final int columns; // total columns in flip book
    private final int rows; // total rows in flip book
    private float x, y; // current position
    private float next_x, next_y; // next position
    private int column, row; // current sprite in flip book

    private long time; // time after prev update
    private long rate; // time to update in ms

    private final String TAG = "CatFinder";
    private int next_column, next_row;

    public Sprite(Bitmap flip_book,
                  float x,
                  float y,
                  int columns,
                  int rows,
                  int column,
                  int row,
                  long rate) {
        this.flip_book = flip_book;
        this.columns = columns;
        this.rows = rows;
        this.x = x;
        this.y = y;
        this.next_x = x;
        this.next_y = y;
        this.column = column;
        this.row = row;
        this.next_column = column;
        this.next_row = row;
        this.rate = rate;

        this.time = 0;
        this.width = flip_book.getWidth() / this.columns;
        this.height = flip_book.getHeight() / this.rows;
    }

    public Sprite(Bitmap flip_book, float x, float y) {
        this.column = 0;
        this.row = 0;
        this.next_column = 0;
        this.next_row = 0;
        this.columns = 1;
        this.rows = 1;
        this.time = 0;
        this.rate = 100;
        
        this.flip_book = flip_book;
        this.x = x;
        this.y = y;
        this.next_x = x;
        this.next_y = y;
        this.width = flip_book.getWidth() / this.columns;
        this.height = flip_book.getHeight() / this.rows;
    }

    public void tick(long delta, Canvas canvas) {
        time += delta;
        if (time >= rate) {
            time = 0;
            this.x = this.next_x;
            this.y = this.next_y;
            this.row = this.next_row;
            this.column = this.next_column;
        }
        this.draw(canvas);
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        Rect src = new Rect(row * width,
                column * height,
                (row + 1) * width,
                (column + 1) * height);
        Rect dst = new Rect((int) x,
                (int) y,
                (int) x + width,
                (int) y + width);
        canvas.drawBitmap(flip_book, src, dst, new Paint());
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public void moveTo(float new_x, float new_y) {
        this.next_x = new_x;
        this.next_y = new_y;
    }

    public boolean flipTo(int new_column, int new_row) {
        if (new_column >= 0 && new_column < this.columns && new_row >= 0 && new_row < this.rows) {
            this.next_column = new_column;
            this.next_row = new_row;
            return true;
        } else {
            return false;
        }
    }

    public boolean changeRate(long new_rate) {
        if (new_rate > 0) {
            this.rate = new_rate;
            return true;
        } else
            return false;
    }
    
    public void update() {
        this.time = this.rate;
    }
}
