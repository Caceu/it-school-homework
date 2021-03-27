package com.example.catfinder;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

public class SurfaceThread extends Thread {
    private final String TAG = "CatFinder";

    private volatile boolean running = true;
    private SurfaceHolder surfaceHolder;
    private long time;
    private SpriteSurfaceView view;


    public SurfaceThread(SurfaceHolder surfaceHolder, SpriteSurfaceView view) {
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
