package tk.sasetz.spritehomework;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class SpriteSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private final String TAG = "Sprites";
    Bitmap image;
    SurfaceThread thread;
    float x, y;
    private Sprite sprite;

    public SpriteSurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
        this.sprite = new Sprite(BitmapFactory.decodeResource(getResources(), R.drawable.squirtle_sprite),
                150,
                200,
                4,
                4,
                0,
                0,
                1000);
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

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_UP) {
            sprite.resume();
        }
        return true;
    }

    public void tick(long delta, Canvas canvas) {
        this.draw(canvas);
        if(canvas != null){
            canvas.drawColor(Color.rgb(88, 219, 162));
            sprite.draw(canvas);
            sprite.tick(delta, canvas);
        }
    }
}
