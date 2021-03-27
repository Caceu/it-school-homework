package com.example.cellularautomata;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class SurfaceThread extends Thread {
    private final String TAG = "CellularAutomaton";

    private volatile boolean running = true;
    private SurfaceHolder surfaceHolder;
    private CellularAutomatonView view;


    public SurfaceThread(SurfaceHolder surfaceHolder, CellularAutomatonView view) {
        this.view = view;
        this.surfaceHolder = surfaceHolder;
    }

    @Override
    public void run() {
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            synchronized (surfaceHolder) {
                view.tick(canvas);
            }
            if (canvas != null) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
