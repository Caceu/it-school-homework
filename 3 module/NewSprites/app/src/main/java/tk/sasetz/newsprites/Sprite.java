package tk.sasetz.newsprites;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Sprite {
    private float x, y;
    private float velocity_x, velocity_y;
    private float velocity;
    private final ImageSheet sheet;
    private final Animation down, left, right, up;
    private final Paint paint;

    public Sprite(float x, float y, ImageSheet sheet, Animation down, Animation left, Animation right, Animation up) {
        this.x = x;
        this.y = y;
        this.sheet = sheet;
        this.paint = new Paint();
        this.velocity_x = 0;
        this.velocity_y = 0;
        this.velocity = 0;
        this.down = down;
        this.left = left;
        this.right = right;
        this.up = up;
    }
    
    public void draw(Canvas canvas, float delta_time){
        float new_x = x + velocity_x * delta_time / 1000;
        float new_y = y + velocity_y * delta_time / 1000;
        if(new_x + sheet.getWidth() > canvas.getWidth()) {
            new_x = 2 * canvas.getWidth() - new_x - 2 * sheet.getWidth();
            this.velocity_x = -this.velocity_x;
            this.setDirection();
        }
        else if(new_x < 0) {
            new_x = -new_x;
            this.velocity_x = - this.velocity_x;
            this.setDirection();
        }
        if(new_y + sheet.getHeight() > canvas.getHeight()) {
            new_y = 2 * canvas.getHeight() - new_y - 2 * sheet.getHeight();
            this.velocity_y = - this.velocity_y;
            this.setDirection();
        }
        else if(new_y < 0) {
            new_y = -new_y;
            this.velocity_y = - this.velocity_y;
            this.setDirection();
        }
        sheet.draw(canvas, x, y, paint);
        x = new_x;
        y = new_y;
    }
    
    public void setTarget(float target_x, float target_y) {
        float length = (float) Math.sqrt((double) (target_x - x) * (target_x - x) + (target_y - y) * (target_y - y));
        this.velocity_x = (target_x - x) * velocity / length;
        this.velocity_y = (target_y - y) * velocity / length;
        this.setDirection();
    }
    
    protected void updateVelocity(){
        float length = (float) Math.sqrt((double) velocity_x * velocity_x + velocity_y * velocity_y);
        this.velocity_x = this.velocity_x / length * velocity;
        this.velocity_y = this.velocity_y / length * velocity;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
        updateVelocity();
    }
    
    private void setDirection() {
        // return 0-3 direction as in the sprite sheet order
        if(velocity_y >= 0 && Math.abs(velocity_x) <= Math.abs(velocity_y)){
            this.sheet.setAnimation(down);
        }
        else if (velocity_x < 0 && Math.abs(velocity_x) >= Math.abs(velocity_y)) {
            this.sheet.setAnimation(left);
        }
        else if (velocity_x >= 0 && Math.abs(velocity_x) >= Math.abs(velocity_y)) {
            this.sheet.setAnimation(right);
        } else {
            this.sheet.setAnimation(up);
        }
    }
    
    public void setAnimation(Animation new_anim) {
        this.sheet.setAnimation(new_anim);
    }
}
