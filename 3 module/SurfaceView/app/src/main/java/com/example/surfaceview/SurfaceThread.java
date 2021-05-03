package com.example.surfaceview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.SurfaceHolder;

public class SurfaceThread extends Thread {
    private final float MARGIN_X = 15f;
    private final float MARGIN_Y = 15f;
    private final float PADDING = 20f;
    private final float RADIUS = 50f;
    private final String TAG = "LampBulb";

    private volatile boolean running = true;
    private SurfaceHolder surfaceHolder;
    private LightBulb[] lamps;

    public SurfaceThread(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
    }

    protected void setCoordinates(float x, float y) {
        // todo переделать алгоритм на более эффективный
        for (LightBulb lamp : lamps) {
            if (lamp.isTouching(x, y)) {
                lamp.light();
                return;
            }
        }
    }

    @Override
    public void run() {
        while (running) {
            Canvas canvas = surfaceHolder.lockCanvas();
            if (lamps == null) {
                int canvas_x = canvas.getWidth();
                int canvas_y = canvas.getHeight();

                Log.v(TAG, "width: " + canvas_x + "; height: " + canvas_y + ";");

                int count_x = (int) ((canvas_x - 2 * PADDING) / (2 * RADIUS + MARGIN_X));
                int count_y = (int) ((canvas_y - 2 * PADDING) / (2 * RADIUS + MARGIN_Y));
                Log.v(TAG, "count_x: " + count_x + "; count_y: " + count_y + ";");

                float delta_x = (2 * RADIUS + MARGIN_X + ((canvas_x - 2 * PADDING) %
                        (2 * RADIUS + MARGIN_X)) / count_x) / 2;
                float delta_y = (2 * RADIUS + MARGIN_Y + ((canvas_y - 2 * PADDING) %
                        (2 * RADIUS + MARGIN_Y)) / count_y) / 2;
                Log.v(TAG, "delta_x: " + delta_x + "; delta_y: " + delta_y + ";");

                float x = PADDING + delta_x;
                float y = PADDING + delta_y;

                lamps = new LightBulb[count_x * count_y];
                int index = 0;
                for (int i = 0; i < count_x; i++) {
                    for (int j = 0; j < count_y; j++) {
                        lamps[index] = new LightBulb(x, y, RADIUS);
                        y += delta_y * 2;
                        index++;
                    }
                    x += delta_x * 2;
                    y = PADDING + delta_y;
                }
            }
            if (canvas != null) {
                canvas.drawColor(Color.BLACK);
                if(lamps != null) {
                    for (LightBulb a : lamps) {
                        a.draw(canvas);
                    }
                }
                surfaceHolder.unlockCanvasAndPost(canvas);
            }
//            try {
//                Thread.sleep(100);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
    }
}
