package com.example.cellularautomata;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class CellularAutomatonView extends SurfaceView implements SurfaceHolder.Callback {
    private final String TAG = "CellularAutomaton";
    private SurfaceThread thread;
    private boolean[] rules; // набор правил инвертирован для удобства
    private boolean[][] grid;
    private Paint paint;
    private int height, width;
    boolean asfd = false;

    public CellularAutomatonView(Context context, int ruleSet, int height, int width, Paint paint) {
        super(context);
        getHolder().addCallback(this);
        this.paint = paint;
        this.height = height;
        this.width = width;
        this.grid = new boolean[height][width];
        this.insertRules(ruleSet);
    }

    public CellularAutomatonView(Context context, int ruleSet, Paint paint) {
        super(context);
        getHolder().addCallback(this);
        this.paint = paint;
        this.height = this.getHeight();
        this.width = this.getWidth();
        this.grid = new boolean[this.height][this.width];
        this.insertRules(ruleSet);
    }

    public void tick(Canvas canvas) {
        super.draw(canvas);
        this.generate(canvas.getHeight(), canvas.getWidth());
        float offset = (canvas.getWidth() - (float) this.width) / 2;
        canvas.drawColor(Color.WHITE);
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (grid[y][x])
                    canvas.drawPoint(x, y, paint);
            }
        }
    }

    public boolean generate(int ruleSet, int height, int width) {
        if (this.insertRules(ruleSet)) {
            this.height = height;
            this.width = width;
            grid = new boolean[height][width];
            grid[0] = new boolean[width];
            for (int i = 0; i < width; i++) {
                grid[0][i] = false;
            }
            grid[0][width / 2] = true;
            for (int i = 1; i < height; i++) {
                grid[i] = this.step(grid[i - 1]);
            }
            return true;
        } else return false;
    }

    public boolean generate(int height, int width) {
        this.height = height;
        this.width = width;
        grid = new boolean[height][width];
        grid[0] = new boolean[width];
        for (int i = 0; i < width; i++) {
            grid[0][i] = false;
        }
        grid[0][width / 2] = true;
        for (int i = 1; i < height; i++) {
            grid[i] = this.step(grid[i - 1]);
        }
        return true;
    }

    private boolean[] step(boolean[] input) {
        boolean[] output = new boolean[width];
        for (int i = 0; i < width; i++) {
            boolean[] slice = new boolean[3];
            if (i == 0) slice[0] = false;
            else slice[0] = input[i - 1];
            if (i == width - 1) slice[2] = false;
            else slice[2] = input[i + 1];
            slice[2] = input[i];
            output[i] = this.apply(slice);
        }
        asfd = true;
        return output;
    }

    private boolean apply(boolean[] input) throws IndexOutOfBoundsException {
        if (input.length != 3) {
            throw new IndexOutOfBoundsException();
        }
        int n = 0;
        if (input[2])
            n += 1;
        if (input[1])
            n += 2;
        if (input[0])
            n += 4;
        if(!asfd){
            Log.d(TAG, "" + n);
        }
        return this.rules[n];
    }

    private boolean insertRules(int ruleSet) {
        if (ruleSet >= 0 && ruleSet < 256) {
            rules = new boolean[8];
            for (int i = 0; i < 8; i++) {
                rules[i] = ruleSet % 2 == 1;
                ruleSet = ruleSet / 2;
            }
            Log.d(TAG, "" + rules[7] + rules[6] + rules[5] + rules[4] + rules[3] + rules[2] + rules[1] + rules[0]);
            return true;
        } else return false;
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        thread = new SurfaceThread(holder, this);
        thread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }
}

    
