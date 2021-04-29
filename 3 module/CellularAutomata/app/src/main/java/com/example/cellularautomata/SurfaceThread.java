package com.example.cellularautomata;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

public class SurfaceThread extends Thread {
    private final String TAG = "CellularAutomaton";

    public volatile boolean running = true;
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
            if(canvas != null)
            synchronized (surfaceHolder) {
                canvas.drawColor(Color.WHITE);
                if(view.automaton != null) {
                    float offset = (canvas.getWidth() - (float) view.automaton.getWidth()) / 2;
                    for (int y = 0; y < view.automaton.getHeight(); y++) {
                        for (int x = 0; x < view.automaton.getWidth(); x++) {
                            if (view.automaton.getGrid()[y][x])
                                canvas.drawPoint(x + offset, y, view.paint);
                        }
                    }
                }
            }
            if (canvas != null) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }
}
