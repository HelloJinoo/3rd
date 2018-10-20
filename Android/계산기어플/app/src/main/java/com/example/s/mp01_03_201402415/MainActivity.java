package com.example.s.mp01_03_201402415;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    private int[] numbericButton = {R.id.btn_0, R.id.btn_1 , R.id.btn_2, R.id.btn_3 , R.id.btn_4 , R.id.btn_5, R.id.btn_6,
            R.id.btn_7 , R.id.btn_8 , R.id.btn_9  }; //숫자버튼
    private int[] func= {R.id.btn_clear , R.id.btn_dot , R.id.btn_result} ; //그 외 버튼
    private int[] operatorButton = {  R.id.btn_sub, R.id.btn_mul , R.id.btn_div,R.id.btn_plus  }; //연산자 버튼
    private TextView txtscreen;   //결과화면 스크린
    private boolean lastNumeric = false;   //숫자를 눌렀는지에 대한 여부 확인 변수
    private boolean lastDot = false;   // 소수점을 한번 눌렀는지 여부 확인 변수
    private boolean equalclick = false;    // 결과버튼을 눌렀는지 여부 확인 변수
    String calculate = "";  // 입력한 연산들을 담는 변수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.txtscreen = (TextView)findViewById(R.id.txtscreen);
        Button[] numberbtn = new Button[10];

        /*숫자버튼 클릭 지정*/
        for(int i=0; i<numbericButton.length; i++) {
            numberbtn[i] = findViewById(numbericButton[i]);  //숫자 버튼 아이디 가져오기
            numberbtn[i].setOnClickListener(new Button.OnClickListener() {  //버튼 클릭 시
                @Override
                public void onClick(View view) {
                    if(equalclick == false) {
                        calculate += ((Button)view).getText();
                        lastNumeric = true;
                        txtscreen.setText(calculate);
                    }
                }
            });
        }


        Button[] operationbtn = new Button[4];
        /*연산자 버튼 클릭 지정*/
        for(int i=0; i<operatorButton.length; i++){
            operationbtn[i] = findViewById(operatorButton[i]);  //연잔자 버튼 아이디 가져오기
            operationbtn[i].setOnClickListener(new Button.OnClickListener() {  //버튼 클릭 시
                @Override
                public void onClick(View view) {
                    if( lastNumeric == true  ) {
                        calculate += ((Button)view).getText();  //view의 텍스트 저장
                        txtscreen.setText(calculate);
                        lastNumeric = false;
                        equalclick = false;
                        lastDot = false;
                    }
                }
            });
        }


        /*Dot버튼 클릭 지정 */
        Button btn_dot  = findViewById(R.id.btn_dot);
        btn_dot.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                if (lastNumeric && !lastDot) {
                    calculate += ".";
                    lastNumeric = false;
                    lastDot = true;
                }
            }
        });

        /*초기화*/
        Button btn_clear = findViewById(R.id.btn_clear);
        btn_clear.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                txtscreen.setText("");
                calculate = "";
                lastNumeric = false;
                lastDot = false;
                equalclick = false;


            }
        });

        /*결과 보기*/
        Button btn_result  = findViewById(R.id.btn_result);
        btn_result.setOnClickListener(new Button.OnClickListener(){
            @Override
            public void onClick(View view){
                    Expression e = new ExpressionBuilder(calculate).build();
                     equalclick = true;
                    lastNumeric = true;
                    double result = e.evaluate();
                     calculate =Double.toString(result);
                    txtscreen.setText(calculate);
                     lastDot = false;


            }
        });





    }
}
