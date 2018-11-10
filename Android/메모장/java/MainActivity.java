package com.example.s.mp01_05_201402415;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    private SingleTouchView drawView;
    private ImageButton currPaint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drawView = (SingleTouchView)findViewById(R.id.drawing);
        LinearLayout paintLayout = (LinearLayout) findViewById(R.id.paint_colors);
        currPaint = (ImageButton)paintLayout.getChildAt(0);
    }
    public void clicked(View v){
        if(v != currPaint){
            String color = v.getTag().toString();
            drawView.setColor(color);
            currPaint = (ImageButton)v;
        }
    }
}
