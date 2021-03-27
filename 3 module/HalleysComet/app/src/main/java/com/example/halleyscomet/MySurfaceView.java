package com.example.halleyscomet;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private static final long COOLDOWN = 3000;
    private static final int PADDING = 20;
    private final String TAG = "CatFinder";
    Bitmap image;
    SurfaceThread thread;
    private float x, y;
    private float degree;
    private final float SPEED = .2f;
    private final float RADIUS = 150.f;
    private final float TINY_RADIUS = 6.f;
    private StellarObject comet;
    private StellarObject sun;
    private StellarObject earth;


    public MySurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
        x = 0;
        y = 0;
        degree = 0;
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

    public void tick(long delta, Canvas canvas) {
        this.draw(canvas);
        if (canvas != null) {
            canvas.drawColor(Color.BLACK);
            degree += .05f * delta;
            if (degree > 360.f)
                degree -= 360.f;
            Paint paint = new Paint();
            paint.setColor(Color.WHITE);
            comet = new StellarObject(RADIUS,
                    RADIUS * 3,
                    .05f,
                    (float) canvas.getWidth() / 2 - RADIUS * 1.4f,
                    (float) canvas.getHeight() / 2 + RADIUS * 2.3f,
                    -60.f,
                    paint
            );

            paint = new Paint();
            paint.setColor(Color.argb(255, 0, 166, 255));
            earth = new StellarObject(RADIUS / 2,
                    RADIUS / 2,
                    1f,
                    (float) canvas.getWidth() / 2,
                    (float) canvas.getHeight() / 2,
                    0.f,
                    paint
            );

            paint = new Paint();
            paint.setColor(Color.YELLOW);
            sun = new StellarObject((float) canvas.getWidth() / 2,
                    (float) canvas.getHeight() / 2,
                    20.f,
                    paint);
            // передавать дельту в каждый из объектов почему-то не работает, вставляя абсолютно тот же код
            comet.draw(canvas, degree);
            earth.draw(canvas, degree);
            sun.draw(canvas, 0);
        }
    }
}
