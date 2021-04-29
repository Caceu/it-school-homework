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
    public float degree;
    public final float SPEED = .2f;
    public final float RADIUS = 150.f;
    public final float TINY_RADIUS = 6.f;
    public StellarObject comet;
    public StellarObject sun;
    public StellarObject earth;


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
}
