package com.example.cellularautomata;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        setContentView(new CellularAutomatonView(this, 58, paint));
    }
}