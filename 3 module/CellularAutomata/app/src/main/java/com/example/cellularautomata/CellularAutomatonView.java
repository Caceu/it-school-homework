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
    public  CellularAutomaton automaton;
    public Paint paint;
    private int ruleSet;

    public CellularAutomatonView(Context context) {
        super(context);
        getHolder().addCallback(this);
        this.ruleSet = 0;
        this.paint = new Paint();
        this.paint.setColor(Color.BLACK);
    }

    public CellularAutomatonView(Context context, int ruleSet, int height, int width, Paint paint) {
        super(context);
        getHolder().addCallback(this);
        this.paint = paint;
        automaton = new CellularAutomaton(height, width, ruleSet);
        this.ruleSet = ruleSet;
        
    }

    public CellularAutomatonView(Context context, int ruleSet, Paint paint) {
        super(context);
        getHolder().addCallback(this);
        this.paint = paint;
        this.ruleSet = ruleSet;
    }
    
    public void update(int ruleSet) {
        automaton.applyRules(ruleSet);
    }
    
    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        thread = new SurfaceThread(holder, this);
        thread.start();
        if(automaton == null)
            automaton = new CellularAutomaton(this.getHeight(), this.getWidth(), this.ruleSet);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        thread.running = false;
    }
}
