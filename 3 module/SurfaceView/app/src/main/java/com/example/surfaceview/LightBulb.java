package com.example.surfaceview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;

public class LightBulb {
    public Paint darkPaint;
    public Paint lightPaint;
    private Paint currentPaint;
    private final float x;
    private final float y;
    private final float radius;
    private boolean lit;

    private final int DEFAULT_DARK = 0xff3000b5;
    private final int DEFAULT_LIGHT = 0xfffcd303;


    private final String TAG = "LampBulb";

    public LightBulb(int darkColor, int lightColor, float x, float y, float radius) {
        this.darkPaint = new Paint();
        this.lightPaint = new Paint();
        this.darkPaint.setColor(darkColor);
        this.lightPaint.setColor(lightColor);
        this.currentPaint = darkPaint;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.lit = false;
    }

    public LightBulb(float x, float y, float radius) {
        this.darkPaint = new Paint();
        this.lightPaint = new Paint();
        this.darkPaint.setColor(DEFAULT_DARK);
        this.lightPaint.setColor(DEFAULT_LIGHT);
        this.currentPaint = darkPaint;
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.lit = false;
    }

    public void light() {
        lit = !lit;
        currentPaint = lit ? lightPaint : darkPaint;
    }

    public void draw(Canvas canvas) {
        canvas.drawCircle(x, y, radius, currentPaint);
    }

    public boolean isTouching(float px, float py) {
        return (this.y - py) * (this.y - py) + (this.x - px) * (this.x - px) <= radius * radius;
    }
}
