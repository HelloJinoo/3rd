package com.example.s.mp01_06_201402415;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.GetChars;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static final int GET_COUNT=1;

    TextView textView;
    RadioButton radio1 , radio2 , radio3;
    Button call_order, internet_order;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       call_order = (Button)findViewById(R.id.Button2);
       internet_order = (Button)findViewById(R.id.Button3);
        radio1 = findViewById(R.id.radioButton1);
        radio2 = findViewById(R.id.radioButton2);
        radio3 = findViewById(R.id.radioButton3);

    }

    public void click(View view){
            if( ((RadioButton)view).isChecked()){
                call_order.setEnabled(true);
                internet_order.setEnabled(true);
            }
    }
    public void call(View view){
        Intent in;
        if( radio1.isChecked()){
             in = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:(+82)15773082"));
        }
        else if( radio2.isChecked()){
             in = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:(+82)15770077"));
        }
        else {
             in = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:(+82)15885588"));
        }
        startActivity(in);

    }
    public void internet(View view){
        Intent in;
        if( radio1.isChecked()){
             in = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.dominos.co.kr"));
        }
        else if( radio2.isChecked()){
             in = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.mrpizza.co.kr"));
        }
        else {
             in = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.pizzahut.co.kr"));
        }
        startActivity(in);
    }
    public void ordermenu(View view){
        Intent intent = new Intent(getApplicationContext() , order.class);
        startActivityForResult(intent , GET_COUNT);
    }

    @Override
    protected void onActivityResult(int requestCode , int resultCode , Intent data){
        if(requestCode == GET_COUNT){
            if( resultCode == RESULT_OK){
                textView = (TextView)findViewById(R.id.textview2);
                textView.setText("피자 = " + data.getExtras().getInt("pizza_count",0) +
                        ", 스파게티 = " + data.getExtras().getInt("spageti_count",0));
            }

        }
    }

}
