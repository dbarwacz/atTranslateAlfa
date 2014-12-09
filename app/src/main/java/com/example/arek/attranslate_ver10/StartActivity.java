package com.example.arek.attranslate_ver10;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Arek on 2014-11-22.
 */
public class StartActivity extends Activity {

    private Timer timer;
    private TimerTask timerTask;

    private static final int DELAY_TIME = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        if(timer !=null)
            timer.cancel();

        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                createNewActivity();
            }
        };
        timer.schedule(timerTask,DELAY_TIME);
    }

    void createNewActivity()
    {
        Intent intent = new Intent(this,MenuActivity.class);
        startActivity(intent);
        onStop();
    }
}
