package tk.sasetz.newsprites;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class ImageSheet {
    private final Bitmap image;
    private final int width, height;
    private final int rows, columns;
    private int row, column;
    private int size;
    private Animation animation;

    public ImageSheet(Bitmap image, int columns, int rows, Animation animation) {
        this.image = image;
        this.rows = rows;
        this.columns = columns;
        this.row = 0;
        this.column = 0;
        this.width = image.getWidth() / columns;
        this.height = image.getHeight() / rows;
        this.animation = animation;
        this.size = 2;
    }
    
    protected void setFrame(int frame) {
        int row = frame / this.columns;
        int column = frame % this.columns;
        if(column >= 0 && column < this.rows)
            this.row = row;
        if(column >= 0 && column < this.columns)
            this.column = column;
    }
    
    public Bitmap getImage() {
        return image;
    }
    
    public void draw(Canvas canvas, float x, float y, Paint paint) {
        Rect src = new Rect(column * width,
                row * height,
                (column + 1) * width,
                (row + 1) * height);
        Rect dst = new Rect((int)x,
                (int)y,
                (int)x + width * size,
                (int)y + height * size);
        canvas.drawBitmap(image, src, dst, paint);
        this.setFrame(animation.step());
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public int getWidth() {
        return width * size;
    }

    public int getHeight() {
        return height * size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
