package com.example.magic8ball;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private SensorManager mSensorManager;
    private float mAccel; // acceleration apart from gravity
    private float mAccelCurrent; // current acceleration including gravity
    private float mAccelLast; // last acceleration including gravity

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Force portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);



        onTap();
    }

    //User taps the 8ball
    public void onTap()
    {
        ImageButton imageButton = findViewById(R.id.imageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onInput();
                System.out.println("\nScreen tapped\n");
            }
        });
    }





    //Change text with new answer
    public void onInput()
    {
        hideEight();
        final TextView answer = findViewById(R.id.answer);
        answer.setText(generateAnswer());
    }

    public void hideEight()
    {
        TextView eight = findViewById(R.id.eight);
        //Toggle
        if (eight.getVisibility() == View.VISIBLE)
            eight.setVisibility(View.INVISIBLE);
    }

    //Select random answer to return
    public String generateAnswer()
    {
        //Random int
        int random =(int) (Math.random()*3+1);

        //1 random answer out of 3 is picked
        switch (random)
        {
            case 1:
                return "1";
            case 2:
                return "2";
            case 3:
                return "3";
        }
        //else
        return "";
    }
}
