package tk.sasetz.spritehomework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

import java.util.ArrayList;

public class Sprite {
    private final float X;
    private final float Y;
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

    private final String TAG = "Sprites";
    private int next_column, next_row;
    
    private final Animation animation;
    private boolean isPaused;

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
        this.X = x;
        this.Y = y;
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
        
        ArrayList<Animation.AnimNode> anim = new ArrayList<>();
        anim.add(new Animation.AnimNode(0, 0, 0));
        anim.add(new Animation.AnimNode(1, 50, 0));
        anim.add(new Animation.AnimNode(2, 100, 0));
        anim.add(new Animation.AnimNode(3, 150, 0));
        anim.add(new Animation.AnimNode(4, 200, 0));
        anim.add(new Animation.AnimNode(5, 200, 0));
        anim.add(new Animation.AnimNode(6, 200, 0));
        anim.add(new Animation.AnimNode(7, 200, 0));
        anim.add(new Animation.AnimNode(7, 200, 0, true));
        anim.add(new Animation.AnimNode(8, 200, 0));
        anim.add(new Animation.AnimNode(9, 200, 0));
        anim.add(new Animation.AnimNode(10, 200, 0));
        anim.add(new Animation.AnimNode(11, 200, 0));
        anim.add(new Animation.AnimNode(12, 200, 0));
        anim.add(new Animation.AnimNode(13, 200, 0));
        anim.add(new Animation.AnimNode(14, 200, 0));
        anim.add(new Animation.AnimNode(15, 200, 0));
        
        this.animation = new Animation(anim, this.X, this.Y);
        this.isPaused = false;
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
        this.X = x;
        this.Y = y;
        this.x = x;
        this.y = y;
        this.next_x = x;
        this.next_y = y;
        this.width = flip_book.getWidth() / this.columns;
        this.height = flip_book.getHeight() / this.rows;

        ArrayList<Animation.AnimNode> anim = new ArrayList<>();
        anim.add(new Animation.AnimNode(0, 0, 0));
        anim.add(new Animation.AnimNode(1, 50, 0));
        anim.add(new Animation.AnimNode(2, 100, 0));
        anim.add(new Animation.AnimNode(3, 150, 0));
        anim.add(new Animation.AnimNode(4, 200, 0));
        anim.add(new Animation.AnimNode(5, 200, 0));
        anim.add(new Animation.AnimNode(6, 200, 0));
        anim.add(new Animation.AnimNode(7, 200, 0));
        anim.add(new Animation.AnimNode(7, 200, 0, true));
        anim.add(new Animation.AnimNode(8, 200, 0));
        anim.add(new Animation.AnimNode(9, 200, 0));
        anim.add(new Animation.AnimNode(10, 200, 0));
        anim.add(new Animation.AnimNode(11, 200, 0));
        anim.add(new Animation.AnimNode(12, 200, 0));
        anim.add(new Animation.AnimNode(13, 200, 0));
        anim.add(new Animation.AnimNode(14, 200, 0));
        anim.add(new Animation.AnimNode(15, 200, 0));

        this.animation = new Animation(anim, this.X, this.Y);
        this.isPaused = false;
    }

    public void tick(long delta, Canvas canvas) {
        if(!isPaused){
            time += delta;
            if (time >= rate) {
                time = 0;
                Animation.AnimNode next = animation.step();
                this.moveTo(next.getPosX(), next.getPosY());
                this.flipTo(next.getFrame());
                this.x = this.next_x;
                this.y = this.next_y;
                this.column = this.next_column;
                this.row = this.next_row;
                Log.v(TAG, next.toString());
                if (next.isTrigger()) {
                    this.isPaused = true;
                }
            }
        }
        this.draw(canvas);
    }

    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        Rect src = new Rect(column * width,
                row * height,
                (column + 1) * width,
                (row + 1) * height);
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

    public void flipTo(int new_sprite) {
        if (new_sprite >= 0 && new_sprite < this.columns * this.rows) {
            this.next_column = new_sprite % this.columns;
            this.next_row = new_sprite / this.columns;
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
    
    public void resume() {
        this.isPaused = false;
    }
}
