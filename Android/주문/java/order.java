package com.example.s.mp01_06_201402415;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

public class order extends AppCompatActivity {

    TextView text1 ,text2;
    int pizzacount=0;
    int spageticount=0;
    CheckBox check1 , check2;
    Button pizzaplus , pizzaminus , spagetiplus , spagetiminus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        text1 = (TextView)findViewById(R.id.textview4);
        text2 = (TextView)findViewById(R.id.textview5);
        check1 = (CheckBox) findViewById(R.id.checkbox1);
        check2 = (CheckBox) findViewById(R.id.checkbox2);
        pizzaplus = (Button)findViewById(R.id.plusbutton1);
        pizzaminus = (Button)findViewById(R.id.minusbutton1);
        spagetiplus = (Button)findViewById(R.id.plusbutton2);
        spagetiminus = (Button)findViewById(R.id.minusbutton2);
    }
    public void reset(View view){
        if(((CheckBox)view).isChecked()){
            switch (view.getId()){
                case R.id.checkbox1:
                    pizzaplus.setEnabled(true);
                    pizzaminus.setEnabled(true);
                    break;
                case R.id.checkbox2:
                    spagetiplus.setEnabled(true);
                    spagetiminus.setEnabled(true);
                    break;
            }
        }
        else if(!((CheckBox)view).isChecked()){
            switch (view.getId()){
                case R.id.checkbox1:
                    text1.setText("피자 = 0");
                    pizzacount = 0;
                    pizzaplus.setEnabled(false);
                    pizzaminus.setEnabled(false);
                    break;
                case R.id.checkbox2:
                    text2.setText("스파게티 = 0");
                    spageticount = 0;
                    spagetiplus.setEnabled(false);
                    spagetiminus.setEnabled(false);
                    break;
            }
        }
    }

    public void pizzaplus(View view){
        if( check1.isChecked()){
            pizzacount++;
            text1.setText("피자 = "+ pizzacount);
        }
    }
    public void pizzaminus(View view){
        if( check1.isChecked() && pizzacount >0){
            pizzacount--;
            text1.setText("피자 = "+ pizzacount);
        }
    }
    public void spagetiplus(View view){
        if( check2.isChecked() ){
            spageticount++;
            text2.setText("스파게티 = "+ spageticount);
        }
    }
    public void spagetiminus(View view){
        if( check2.isChecked() && spageticount > 0 ){
            spageticount--;
            text2.setText("스파게티 = "+ spageticount);
        }
    }

    public void set(View view){
        Intent intent = new Intent();
        intent.putExtra("pizza_count", pizzacount);
        intent.putExtra("spageti_count", spageticount);
        setResult(RESULT_OK, intent);
        finish();
    }
}
