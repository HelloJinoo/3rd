package com.example.s.mp01_04_201402415;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.kunzisoft.switchdatetime.SwitchDateTimeDialogFragment;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private static final String TAG_DATETIME_FRAGMENT = "TAG_DATETIME_FRAGMENT";

    private TextView textView;  //Text저장을 위한 TextView
    private SwitchDateTimeDialogFragment dateTimeFragment;  //선택을 위한 Dialog창
    private Button btn; // Dialog를 띄우기 위한 버튼

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView); //XML파일의 날짜, 시간을 출력해줄 Textview

        dateTimeFragment = SwitchDateTimeDialogFragment.newInstance(
                getString(R.string.label_datetime_dialog),
                getString(android.R.string.ok),  //확인
                getString(android.R.string.cancel) //취소
        );

        dateTimeFragment.setTimeZone(TimeZone.getDefault());
        final SimpleDateFormat myDateFormat = new SimpleDateFormat("yyyy.MM.dd-HH:mm", java.util.Locale.getDefault()); //형식
        dateTimeFragment.set24HoursMode(false);   //24시간 모드 설정
        dateTimeFragment.setHighlightAMPMSelection(true); //AM , PM 설정

        dateTimeFragment.setOnButtonClickListener(new SwitchDateTimeDialogFragment.OnButtonWithNeutralClickListener() {
            //Dialog창에서 각 버튼들
            @Override
            public void onPositiveButtonClick(Date date) {  //확인 버튼을 누르면 TextView에 글
                textView.setText(myDateFormat.format(date));
            }

            @Override
            public void onNegativeButtonClick(Date date) {  //취소 버튼을 누르면 취소
            }

            @Override
            public void onNeutralButtonClick(Date date) {
                textView.setText("");
            }
        });

        btn = findViewById(R.id.button);
        btn.setOnClickListener(new View.OnClickListener() {  //버튼 클릭시 호출
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                dateTimeFragment.startAtCalendarView();
                /*Calender 객체의 현재 시간 정보를 받아서 Default로 사용*/
                dateTimeFragment.setDefaultDateTime(new GregorianCalendar(c.get(Calendar.YEAR),
                        c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH),
                        c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE)).getTime());
                dateTimeFragment.show(getSupportFragmentManager(), TAG_DATETIME_FRAGMENT);
            }
        });
    }
}
