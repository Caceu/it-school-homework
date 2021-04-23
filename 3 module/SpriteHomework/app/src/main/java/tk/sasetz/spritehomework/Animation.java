package tk.sasetz.spritehomework;

import android.util.Log;

import java.util.ArrayList;

public class Animation {
    private final ArrayList<AnimNode> sequence;
    private int current_step;
    private final float x, y;
    private final static String TAG = "Sprites";
    public Animation(ArrayList<AnimNode> input_sequence, float init_x, float init_y){
        this.sequence = input_sequence;
        this.current_step = 0;
        this.x = init_x;
        this.y = init_y;
    }

    public AnimNode step(){
        if(current_step + 1 > sequence.size()){
            current_step = 0;
        }
        return sequence.get(current_step++).add(x, y);
    }

    static class AnimNode {
        private final int frame;
        private final float posX;
        private final float posY;
        private final boolean isTrigger;

        public AnimNode(int frame, float posX, float posY) {
            this.frame = frame;
            this.posX = posX;
            this.posY = posY;
            this.isTrigger = false;
        }

        public AnimNode(int frame, float posX, float posY, boolean isTrigger) {
            this.frame = frame;
            this.posX = posX;
            this.posY = posY;
            this.isTrigger = isTrigger;
            Log.v(TAG, "" + isTrigger + " " + this.isTrigger + " " + this.isTrigger());
        }

        public boolean isTrigger() {
            return isTrigger;
        }

        public int getFrame() {
            return frame;
        }

        public float getPosX() {
            return posX;
        }

        public float getPosY() {
            return posY;
        }

        public AnimNode add(float x, float y){
            return new AnimNode(this.getFrame(),
                    this.getPosX() + x,
                    this.getPosY() + y, this.isTrigger);
        }

        @Override
        public String toString() {
            return "AnimNode{" +
                    "frame=" + frame +
                    ", posX=" + posX +
                    ", posY=" + posY +
                    ", isTrigger=" + isTrigger +
                    '}';
        }
    }
}
