package com.example.catfinder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import java.util.Random;

public class SpriteSurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private static final long COOLDOWN = 3000;
    private static final int PADDING = 20;
    private final String TAG = "CatFinder";
    Bitmap image;
    SurfaceThread thread;
    float x, y;
    private final float RADIUS = 150.f;
    private Sprite cat;
    private long time_up, cooldown;
    private boolean isFound;

    public SpriteSurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
        this.cat = new Sprite(BitmapFactory.decodeResource(getResources(), R.drawable.cat),
                150,
                200);
        x = -RADIUS;
        y = -RADIUS;
        this.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        this.isFound = true;
        this.time_up = 0;
        this.cooldown = 0;
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
        if (event.getAction() == MotionEvent.ACTION_DOWN ||
                event.getAction() == MotionEvent.ACTION_MOVE) {
            x = event.getX();
            y = event.getY();
            if(!this.isFound && this.time_up <= 0) {
                this.isFound = this.isOverlapping(x, y);
                if(this.isFound)
                    Toast.makeText(getContext(), "You found the cat!", Toast.LENGTH_LONG).show();
            }
        }
        else if(event.getAction() == MotionEvent.ACTION_UP) {
            x = -RADIUS;
            y = -RADIUS;
            if(!this.isFound && this.time_up <= 0) {
                this.isFound = this.isOverlapping(event.getX(), event.getY());
                if(this.isFound)
                    Toast.makeText(getContext(), R.string.toast_found, Toast.LENGTH_LONG).show();
            }
        }
        return true;
    }
    
    public void tick(long delta, Canvas canvas) {
        this.draw(canvas);
        this.time_up -= delta;
        if(this.isFound)
            cooldown -= delta;
        if(cooldown <= 0) {
            cooldown = COOLDOWN;
            this.time_up = 300;
            this.generate(canvas.getWidth(), canvas.getHeight());
            this.isFound = false;
        }
        if(canvas != null){
            Paint paint = new Paint();
            paint.setColor(Color.YELLOW);
            paint.setAlpha(127);
            
            canvas.drawColor(Color.BLACK);
            canvas.drawCircle(x, y, RADIUS, paint);
            cat.draw(canvas);
            cat.tick(delta, canvas);
        }
    }
    
    private void generate(int width, int height) {
        float new_x = 0;
        float new_y = 0;
        Random rnd = new Random();
        
        new_x = rnd.nextFloat() * (width - PADDING * 2 - this.cat.getWidth()) + PADDING;
        new_y = rnd.nextFloat() * (height - PADDING * 2 - this.cat.getHeight()) + PADDING;
        
        this.cat.moveTo(new_x, new_y);
    }
    
    private boolean isOverlapping(float x, float y) {
        return (x - cat.getX() - cat.getWidth() / 2) * (x - cat.getX() - cat.getWidth() / 2) + 
                (y - cat.getY() - cat.getHeight() / 2) * (y - cat.getY() - cat.getHeight() / 2) <=
                RADIUS * RADIUS;
    }
}
