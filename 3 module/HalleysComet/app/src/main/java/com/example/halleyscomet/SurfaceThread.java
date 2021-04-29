package com.example.halleyscomet;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
            if(canvas != null)
            synchronized (surfaceHolder) {
                canvas.drawColor(Color.BLACK);
                view.degree += .05f * delta;
                if (view.degree > 360.f)
                    view.degree -= 360.f;
                Paint paint = new Paint();
                paint.setColor(Color.WHITE);
                view.comet = new StellarObject(view.RADIUS,
                        view.RADIUS * 3,
                        .05f,
                        (float) canvas.getWidth() / 2 - view.RADIUS * 1.4f,
                        (float) canvas.getHeight() / 2 + view.RADIUS * 2.3f,
                        -60.f,
                        paint
                );

                paint = new Paint();
                paint.setColor(Color.argb(255, 0, 166, 255));
                view.earth = new StellarObject(view.RADIUS / 2,
                        view.RADIUS / 2,
                        1f,
                        (float) canvas.getWidth() / 2,
                        (float) canvas.getHeight() / 2,
                        0.f,
                        paint
                );

                paint = new Paint();
                paint.setColor(Color.YELLOW);
                view.sun = new StellarObject((float) canvas.getWidth() / 2,
                        (float) canvas.getHeight() / 2,
                        20.f,
                        paint);
                // передавать дельту в каждый из объектов почему-то не работает, вставляя абсолютно тот же код
                view.comet.draw(canvas, view.degree);
                view.earth.draw(canvas, view.degree);
                view.sun.draw(canvas, 0);
            }
            if (canvas != null) {
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
            time = currentTime;
        }
    }
}
