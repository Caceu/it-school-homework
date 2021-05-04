package tk.sasetz.newsprites;

import java.util.ArrayList;

public class Animation {
    private final ArrayList<Integer> sequence;
    private int current_step;
    public Animation(ArrayList<Integer> input_sequence){
        this.sequence = input_sequence;
        this.current_step = 0;
    }

    public int step(){
        if(current_step + 1 > sequence.size()){
            current_step = 0;
        }
        return sequence.get(current_step++);
    }
}
