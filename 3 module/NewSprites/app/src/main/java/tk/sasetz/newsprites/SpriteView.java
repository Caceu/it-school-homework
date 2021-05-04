package tk.sasetz.newsprites;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class SpriteView extends SurfaceView implements SurfaceHolder.Callback {
    SurfaceThread thread;
    Sprite sprite;
    

    public SpriteView(Context context) {
        super(context);
        setAttrs();
    }

    public SpriteView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setAttrs();
    }
    
    private void setAttrs(){
        getHolder().addCallback(this);
        ArrayList<Integer> down = new ArrayList<>();
        down.add(0);
        down.add(1);
        down.add(2);
        ArrayList<Integer> left = new ArrayList<>();
        left.add(3);
        left.add(4);
        left.add(5);
        ArrayList<Integer> right = new ArrayList<>();
        right.add(6);
        right.add(7);
        right.add(8);
        ArrayList<Integer> up = new ArrayList<>();
        up.add(9);
        up.add(10);
        up.add(11);
        Animation first = new Animation(down);
        sprite = new Sprite(150, 150, new ImageSheet(BitmapFactory.decodeResource(getResources(), R.drawable.ivysaur), 3, 4, first),
                first, new Animation(left), new Animation(right), new Animation(up));
        sprite.setVelocity(getResources().getInteger(R.integer.default_speed));
        sprite.setTarget(0, 0);
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        thread = new SurfaceThread(holder, this);
        thread.start();
        thread.tickRate = getResources().getInteger(R.integer.default_tick);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        thread.running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
            sprite.setTarget(event.getX(), event.getY());
        }
        return true;
    }
    
    public void setVelocity(float new_velocity) {
        if(sprite != null)
            sprite.setVelocity(new_velocity);
    }
    
    public void setTickRate(double new_tick_rate) {
        if(this.thread != null)
            this.thread.tickRate = new_tick_rate;
    }
}
