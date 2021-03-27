package com.example.halleyscomet;

import android.graphics.Canvas;
import android.graphics.Paint;

public class StellarObject {
    public final float size;
    private float vertical_radius;
    private float horizontal_radius;
    private float angular_speed;
    private final float x;
    private final float y;
    private float rotation;
    private final Paint paint;
    private final boolean isStatic;

    public StellarObject(float vertical_radius,
                         float horizontal_radius,
                         float angular_speed,
                         float x,
                         float y,
                         float rotation,
                         Paint paint) {
        this.vertical_radius = vertical_radius;
        this.horizontal_radius = horizontal_radius;
        this.angular_speed = angular_speed;
        this.x = x;
        this.y = y;
        this.paint = paint;
        this.rotation = rotation;
        this.size = 5.f;
        this.isStatic = false;
    }

    public StellarObject(float x, float y, float size, Paint paint) {
        this.x = x;
        this.y = y;
        this.paint = paint;
        this.size = size;
        this.isStatic = true;
    }

    public void draw(Canvas canvas, float angle) {
        if(!this.isStatic) {
            paint.setStyle(Paint.Style.STROKE);
            canvas.save();
            canvas.rotate(rotation, x, y);
            canvas.drawOval(x - horizontal_radius,
                    y - vertical_radius,
                    x + horizontal_radius,
                    y + vertical_radius,
                    paint);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            canvas.drawCircle(horizontal_radius * (float) Math.cos(Math.toRadians(angle)) + x,
                    vertical_radius * (float) Math.sin(Math.toRadians(angle)) + y, size, paint);
            canvas.restore();
        }
        else {
            canvas.drawCircle(x, y, size, paint);
        }
    }
}
