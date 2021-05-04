package tk.sasetz.newsprites;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;

public class SurfaceThread extends Thread {
    protected volatile boolean running = true;
    private SurfaceHolder surfaceHolder;
    private SpriteView view;
    protected double tickRate;
    public SurfaceThread(SurfaceHolder holder, SpriteView view) {
        this.surfaceHolder = holder;
        this.view = view;
        tickRate = 100.f;
    }

    @Override
    public void run() {
        double last_time = System.currentTimeMillis();
        double current_time = last_time;
        double delta = current_time - last_time;
        Canvas canvas = null;
        while(running){
            current_time = System.currentTimeMillis();
            delta += current_time - last_time;
            if(delta >= tickRate) {
                canvas = surfaceHolder.lockCanvas();
                if(canvas != null)
                synchronized (surfaceHolder) {
                    canvas.drawColor(Color.WHITE);
                    view.sprite.draw(canvas, (float) delta);
                    delta = 0;
                }
                if(canvas != null) {
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            last_time = current_time;
        }
    }
}
