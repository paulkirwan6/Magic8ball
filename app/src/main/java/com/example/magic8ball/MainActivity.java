package com.example.magic8ball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Force portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //ShakeDetector initialisation
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        assert mSensorManager != null;
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();

        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {

            //Shaking the device is a recognised input
            @Override
            public void onShake(int count) {
                onInput();
            }
        });

        //Tap input
        onTap();
    }

    //User tapping the 8ball is a recognised input
    public void onTap()
    {
        ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onInput();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }

    //Change text with new answer
    public void onInput()
    {
        //Hide original view first
        TextView eight = findViewById(R.id.eight);
        if (eight.getVisibility() == View.VISIBLE)
            eight.setVisibility(View.INVISIBLE);

        //Display new answer
        final TextView answer = findViewById(R.id.answer);
        answer.setText(generateAnswer());
    }

    //Select random answer to return
    public String generateAnswer()
    {
        //Random int
        int random =(int) (Math.random()*20+1);

        //1 random answer out of 20 is picked
        switch (random)
        {
            case 1:
                return "It is certain.";
            case 2:
                return "It is decidedly so.";
            case 3:
                return "Without a doubt.";
            case 4:
                return "Yes - definitely.";
            case 5:
                return "You may rely on it.";
            case 6:
                return "As I see it, yes.";
            case 7:
                return "Most likely.";
            case 8:
                return "Outlook good.";
            case 9:
                return "Yes.";
            case 10:
                return "Signs point to yes.";
            case 11:
                return "Reply hazy, try again.";
            case 12:
                return "Ask again later.";
            case 13:
                return "Better not tell you now.";
            case 14:
                return "Cannot predict now.";
            case 15:
                return "Concentrate and ask again.";
            case 16:
                return "Don't count on it.";
            case 17:
                return "My reply is no.";
            case 18:
                return "My sources say no.";
            case 19:
                return "Outlook not so good.";
            case 20:
                return "Very doubtful.";
        }
        //else
        return "";
    }
}