package com.example.s.mp01_10_1_201402415;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeechService;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements SensorEventListener, TextToSpeech.OnInitListener {

    SensorManager mSensorManager;
    Sensor mAccelerometer;
    TextView t;
    private static int SHAKE_THRESHOLD = 3;
    private TextToSpeech mTts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("MP01_10_201402415");
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        t = findViewById(R.id.text);
        mTts = new TextToSpeech(this, this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(this,mAccelerometer,SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(this);
    }

    private void generateRandomNumber() {
        Random randomGenerator = new Random();
        int randomNum = randomGenerator.nextInt(6) + 1;
        t.setText(Integer.toString(randomNum));
        mTts.speak(Integer.toString(randomNum), TextToSpeech.QUEUE_FLUSH, null);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];
        float z = event.values[2];

        float acceleration = (float) Math.sqrt(x*x + y*y + z*z) - SensorManager.GRAVITY_EARTH;

        if (acceleration > SHAKE_THRESHOLD) {
            generateRandomNumber();
        }
    }

        @Override
        protected void onDestroy() {
            if (mTts != null) {
                mTts.stop();
                mTts.shutdown();
            }
            super.onDestroy();
        }

    @Override
    public void onInit(int status) {
        if(status != TextToSpeech.ERROR) {
            mTts.setLanguage(getResources().getConfiguration().locale);
        }

    }
}
