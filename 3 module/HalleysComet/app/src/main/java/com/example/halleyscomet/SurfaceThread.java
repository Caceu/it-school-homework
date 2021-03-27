package com.example.halleyscomet;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class SurfaceThread extends Thread {
    private final String TAG = "Halley'sComet";

    private volatile boolean running = true;
    private SurfaceHolder surfaceHolder;
    private long time;
    private MySurfaceView view;


    public SurfaceThread(SurfaceHolder surfaceHolder, MySurfaceView view) {
        this.time = System.currentTimeMillis();
        this.view = view;
        this.surfaceHolder = surfaceHolder;
    }

    @Override
    public void run() {
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            long currentTime = System.currentTimeMillis();
            long delta = currentTime - time;
            synchronized (surfaceHolder) {
                view.tick(delta, canvas);
            }
            if (canvas != null) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
            time = currentTime;
        }
    }
}
